package p031pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@TargetApi(9)
/* renamed from: pl.droidsonroids.gif.ReLinker */
class ReLinker {
    private ReLinker() {
    }

    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    static void loadLibrary(Context context, String library) {
        String libName = System.mapLibraryName(library);
        synchronized (ReLinker.class) {
            System.load(unpackLibrary(context, libName).getAbsolutePath());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        closeSilently(r8);
        closeSilently(r6);
        setFilePermissions(r10);
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File unpackLibrary(android.content.Context r17, java.lang.String r18) {
        /*
            java.io.File r10 = new java.io.File
            java.lang.String r14 = "lib"
            r15 = 0
            r0 = r17
            java.io.File r14 = r0.getDir(r14, r15)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r0 = r18
            java.lang.StringBuilder r15 = r15.append(r0)
            java.lang.String r16 = "1.1.14"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            r10.<init>(r14, r15)
            boolean r14 = r10.isFile()
            if (r14 == 0) goto L_0x002d
            r3 = r10
        L_0x002c:
            return r3
        L_0x002d:
            java.io.File r3 = new java.io.File
            java.io.File r14 = r17.getCacheDir()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r0 = r18
            java.lang.StringBuilder r15 = r15.append(r0)
            java.lang.String r16 = "1.1.14"
            java.lang.StringBuilder r15 = r15.append(r16)
            java.lang.String r15 = r15.toString()
            r3.<init>(r14, r15)
            boolean r14 = r3.isFile()
            if (r14 != 0) goto L_0x002c
            pl.droidsonroids.gif.ReLinker$1 r7 = new pl.droidsonroids.gif.ReLinker$1
            r0 = r18
            r7.<init>(r0)
            clearOldLibraryFiles(r10, r7)
            clearOldLibraryFiles(r3, r7)
            android.content.pm.ApplicationInfo r2 = r17.getApplicationInfo()
            java.io.File r1 = new java.io.File
            java.lang.String r14 = r2.sourceDir
            r1.<init>(r14)
            r13 = 0
            java.util.zip.ZipFile r13 = openZipFile(r1)     // Catch:{ all -> 0x00a1 }
            r11 = 0
            r12 = r11
        L_0x0071:
            int r11 = r12 + 1
            r14 = 5
            if (r12 >= r14) goto L_0x00bf
            r0 = r18
            java.util.zip.ZipEntry r9 = findLibraryEntry(r0, r13)     // Catch:{ all -> 0x00a1 }
            if (r9 != 0) goto L_0x00a8
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r15.<init>()     // Catch:{ all -> 0x00a1 }
            java.lang.String r16 = "Library "
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ all -> 0x00a1 }
            r0 = r18
            java.lang.StringBuilder r15 = r15.append(r0)     // Catch:{ all -> 0x00a1 }
            java.lang.String r16 = " for supported ABIs not found in APK file"
            java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ all -> 0x00a1 }
            java.lang.String r15 = r15.toString()     // Catch:{ all -> 0x00a1 }
            r14.<init>(r15)     // Catch:{ all -> 0x00a1 }
            throw r14     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r14 = move-exception
            if (r13 == 0) goto L_0x00a7
            r13.close()     // Catch:{ IOException -> 0x00de }
        L_0x00a7:
            throw r14
        L_0x00a8:
            r8 = 0
            r5 = 0
            java.io.InputStream r8 = r13.getInputStream(r9)     // Catch:{ IOException -> 0x00c7, all -> 0x00d4 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00c7, all -> 0x00d4 }
            r6.<init>(r10)     // Catch:{ IOException -> 0x00c7, all -> 0x00d4 }
            copy(r8, r6)     // Catch:{ IOException -> 0x00e3, all -> 0x00e0 }
            closeSilently(r8)     // Catch:{ all -> 0x00a1 }
            closeSilently(r6)     // Catch:{ all -> 0x00a1 }
            setFilePermissions(r10)     // Catch:{ all -> 0x00a1 }
        L_0x00bf:
            if (r13 == 0) goto L_0x00c4
            r13.close()     // Catch:{ IOException -> 0x00dc }
        L_0x00c4:
            r3 = r10
            goto L_0x002c
        L_0x00c7:
            r4 = move-exception
        L_0x00c8:
            r14 = 2
            if (r11 <= r14) goto L_0x00cc
            r10 = r3
        L_0x00cc:
            closeSilently(r8)     // Catch:{ all -> 0x00a1 }
            closeSilently(r5)     // Catch:{ all -> 0x00a1 }
            r12 = r11
            goto L_0x0071
        L_0x00d4:
            r14 = move-exception
        L_0x00d5:
            closeSilently(r8)     // Catch:{ all -> 0x00a1 }
            closeSilently(r5)     // Catch:{ all -> 0x00a1 }
            throw r14     // Catch:{ all -> 0x00a1 }
        L_0x00dc:
            r14 = move-exception
            goto L_0x00c4
        L_0x00de:
            r15 = move-exception
            goto L_0x00a7
        L_0x00e0:
            r14 = move-exception
            r5 = r6
            goto L_0x00d5
        L_0x00e3:
            r4 = move-exception
            r5 = r6
            goto L_0x00c8
        */
        throw new UnsupportedOperationException("Method not decompiled: p031pl.droidsonroids.gif.ReLinker.unpackLibrary(android.content.Context, java.lang.String):java.io.File");
    }

    @TargetApi(21)
    private static ZipEntry findLibraryEntry(String libName, ZipFile zipFile) {
        if (VERSION.SDK_INT >= 21) {
            for (String abi : Build.SUPPORTED_ABIS) {
                ZipEntry libraryEntry = getEntry(libName, zipFile, abi);
                if (libraryEntry != null) {
                    return libraryEntry;
                }
            }
        }
        return getEntry(libName, zipFile, Build.CPU_ABI);
    }

    private static ZipEntry getEntry(String libName, ZipFile zipFile, String abi) {
        return zipFile.getEntry("lib/" + abi + "/" + libName);
    }

    private static ZipFile openZipFile(File apkFile) {
        int tries = 0;
        ZipFile zipFile = null;
        while (true) {
            int tries2 = tries;
            tries = tries2 + 1;
            if (tries2 >= 5) {
                break;
            }
            try {
                zipFile = new ZipFile(apkFile, 1);
                break;
            } catch (IOException e) {
            }
        }
        if (zipFile != null) {
            return zipFile;
        }
        throw new RuntimeException("Could not open APK file: " + apkFile.getAbsolutePath());
    }

    private static void clearOldLibraryFiles(File outputFile, FilenameFilter filter) {
        File[] fileList = outputFile.getParentFile().listFiles(filter);
        if (fileList != null) {
            for (File file : fileList) {
                file.delete();
            }
        }
    }

    @SuppressLint({"SetWorldReadable"})
    private static void setFilePermissions(File outputFile) {
        outputFile.setReadable(true, false);
        outputFile.setExecutable(true, false);
        outputFile.setWritable(true);
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[8192];
        while (true) {
            int bytesRead = in.read(buf);
            if (bytesRead != -1) {
                out.write(buf, 0, bytesRead);
            } else {
                return;
            }
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
