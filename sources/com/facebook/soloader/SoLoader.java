package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    private static String SO_STORE_NAME_MAIN = "lib-main";
    static final boolean SYSTRACE_LIBRARY_LOADING = false;
    static final String TAG = "SoLoader";
    private static int sFlags;
    private static final Set<String> sLoadedLibraries = new HashSet();
    private static ThreadPolicy sOldPolicy = null;
    private static SoSource[] sSoSources = null;

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable cause) {
            super("APK was built for a different platform");
            initCause(cause);
        }
    }

    public static void init(Context context, int flags) throws IOException {
        ThreadPolicy oldPolicy = StrictMode.allowThreadDiskWrites();
        try {
            initImpl(context, flags);
        } finally {
            StrictMode.setThreadPolicy(oldPolicy);
        }
    }

    public static void init(Context context, boolean nativeExopackage) {
        try {
            init(context, nativeExopackage ? 1 : 0);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static synchronized void initImpl(Context context, int flags) throws IOException {
        int apkSoSourceFlags;
        synchronized (SoLoader.class) {
            if (sSoSources == null) {
                sFlags = flags;
                ArrayList<SoSource> soSources = new ArrayList<>();
                String LD_LIBRARY_PATH = System.getenv("LD_LIBRARY_PATH");
                if (LD_LIBRARY_PATH == null) {
                    LD_LIBRARY_PATH = "/vendor/lib:/system/lib";
                }
                String[] systemLibraryDirectories = LD_LIBRARY_PATH.split(":");
                for (String file : systemLibraryDirectories) {
                    DirectorySoSource directorySoSource = new DirectorySoSource(new File(file), 2);
                    soSources.add(directorySoSource);
                }
                if (context != null) {
                    if ((flags & 1) != 0) {
                        ExoSoSource exoSoSource = new ExoSoSource(context, SO_STORE_NAME_MAIN);
                        soSources.add(0, exoSoSource);
                    } else {
                        ApplicationInfo applicationInfo = context.getApplicationInfo();
                        if ((applicationInfo.flags & 1) != 0 && (applicationInfo.flags & 128) == 0) {
                            apkSoSourceFlags = 0;
                        } else {
                            apkSoSourceFlags = 1;
                            int ourSoSourceFlags = 0;
                            if (VERSION.SDK_INT <= 17) {
                                ourSoSourceFlags = 0 | 1;
                            }
                            File file2 = new File(applicationInfo.nativeLibraryDir);
                            soSources.add(0, new DirectorySoSource(file2, ourSoSourceFlags));
                        }
                        ApkSoSource apkSoSource = new ApkSoSource(context, SO_STORE_NAME_MAIN, apkSoSourceFlags);
                        soSources.add(0, apkSoSource);
                    }
                }
                SoSource[] finalSoSources = (SoSource[]) soSources.toArray(new SoSource[soSources.size()]);
                int prepareFlags = makePrepareFlags();
                int i = finalSoSources.length;
                while (true) {
                    int i2 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    finalSoSources[i2].prepare(prepareFlags);
                    i = i2;
                }
                sSoSources = finalSoSources;
            }
        }
    }

    private static int makePrepareFlags() {
        if ((sFlags & 2) != 0) {
            return 0 | 1;
        }
        return 0;
    }

    public static void setInTestMode() {
        sSoSources = new SoSource[]{new NoopSoSource()};
    }

    public static synchronized void loadLibrary(String shortName) throws UnsatisfiedLinkError {
        synchronized (SoLoader.class) {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    System.loadLibrary(shortName);
                }
            }
            try {
                loadLibraryBySoName(System.mapLibraryName(shortName), 0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (UnsatisfiedLinkError ex2) {
                String message = ex2.getMessage();
                if (message == null || !message.contains("unexpected e_machine:")) {
                    throw ex2;
                }
                throw new WrongAbiError(ex2);
            }
        }
    }

    public static File unpackLibraryAndDependencies(String shortName) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(shortName));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void loadLibraryBySoName(String soName, int loadFlags) throws IOException {
        int result = sLoadedLibraries.contains(soName) ? 1 : 0;
        if (result == 0) {
            boolean restoreOldPolicy = false;
            if (sOldPolicy == null) {
                sOldPolicy = StrictMode.allowThreadDiskReads();
                restoreOldPolicy = true;
            }
            int i = 0;
            while (result == 0) {
                try {
                    if (i >= sSoSources.length) {
                        break;
                    }
                    result = sSoSources[i].loadLibrary(soName, loadFlags);
                    i++;
                } catch (Throwable th) {
                    if (restoreOldPolicy) {
                        StrictMode.setThreadPolicy(sOldPolicy);
                        sOldPolicy = null;
                    }
                    throw th;
                }
            }
            if (restoreOldPolicy) {
                StrictMode.setThreadPolicy(sOldPolicy);
                sOldPolicy = null;
            }
        }
        if (result == 0) {
            throw new UnsatisfiedLinkError("couldn't find DSO to load: " + soName);
        } else if (result == 1) {
            sLoadedLibraries.add(soName);
        }
    }

    static File unpackLibraryBySoName(String soName) throws IOException {
        for (SoSource unpackLibrary : sSoSources) {
            File unpacked = unpackLibrary.unpackLibrary(soName);
            if (unpacked != null) {
                return unpacked;
            }
        }
        throw new FileNotFoundException(soName);
    }

    private static void assertInitialized() {
        if (sSoSources == null) {
            throw new RuntimeException("SoLoader.init() not yet called");
        }
    }

    public static synchronized void prependSoSource(SoSource extraSoSource) throws IOException {
        synchronized (SoLoader.class) {
            assertInitialized();
            extraSoSource.prepare(makePrepareFlags());
            SoSource[] newSoSources = new SoSource[(sSoSources.length + 1)];
            newSoSources[0] = extraSoSource;
            System.arraycopy(sSoSources, 0, newSoSources, 1, sSoSources.length);
            sSoSources = newSoSources;
        }
    }

    public static synchronized String makeLdLibraryPath() {
        String join;
        synchronized (SoLoader.class) {
            assertInitialized();
            ArrayList<String> pathElements = new ArrayList<>();
            SoSource[] soSources = sSoSources;
            for (SoSource addToLdLibraryPath : soSources) {
                addToLdLibraryPath.addToLdLibraryPath(pathElements);
            }
            join = TextUtils.join(":", pathElements);
        }
        return join;
    }
}
