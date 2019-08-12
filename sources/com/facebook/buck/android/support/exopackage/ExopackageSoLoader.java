package com.facebook.buck.android.support.exopackage;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExopackageSoLoader {
    private static final String TAG = "ExopackageSoLoader";
    private static Map<String, String> abi1Libraries = new HashMap();
    private static Map<String, String> abi2Libraries = new HashMap();
    private static boolean initialized = false;
    private static String nativeLibsDir = null;
    private static File privateNativeLibsDir = null;

    private ExopackageSoLoader() {
    }

    public static void init(Context context) {
        if (initialized) {
            Log.d(TAG, "init() already called, so nothing to do.");
            return;
        }
        nativeLibsDir = "/data/local/tmp/exopackage/" + context.getPackageName() + "/native-libs/";
        verifyMetadataFile();
        preparePrivateDirectory(context);
        parseMetadata();
        initialized = true;
    }

    private static void verifyMetadataFile() {
        if (!getAbi1Metadata().exists()) {
            File abiMetadata = getAbi2Metadata();
            if (abiMetadata != null && !abiMetadata.exists()) {
                throw new RuntimeException("Either 'native' exopackage is not turned on for this build, or the installation did not complete successfully.");
            }
        }
    }

    private static void preparePrivateDirectory(Context context) {
        privateNativeLibsDir = context.getDir("exo-libs", 0);
        for (File file : privateNativeLibsDir.listFiles()) {
            file.delete();
        }
    }

    private static void parseMetadata() {
        doParseMetadata(getAbi1Metadata(), abi1Libraries);
        doParseMetadata(getAbi2Metadata(), abi2Libraries);
    }

    private static void doParseMetadata(File metadata, Map<String, String> libraries) {
        if (metadata != null && metadata.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(metadata));
                while (true) {
                    try {
                        String line = br.readLine();
                        if (line != null) {
                            String line2 = line.trim();
                            if (!line2.isEmpty()) {
                                int spaceIndex = line2.indexOf(32);
                                if (spaceIndex == -1) {
                                    throw new RuntimeException("Error parsing metadata.txt; invalid line: " + line2);
                                }
                                libraries.put(line2.substring(0, spaceIndex), line2.substring(spaceIndex + 1));
                            }
                        } else {
                            br.close();
                            return;
                        }
                    } catch (IOException e) {
                        e = e;
                        BufferedReader bufferedReader = br;
                    } catch (Throwable th) {
                        br.close();
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                throw new RuntimeException(e);
            }
        }
    }

    public static void loadLibrary(String shortName) throws UnsatisfiedLinkError {
        if (!initialized) {
            Log.d(TAG, "ExopackageSoLoader not initialized, falling back to System.loadLibrary()");
            System.loadLibrary(shortName);
            return;
        }
        File libraryFile = copySoFileIfRequired(shortName.startsWith("lib") ? shortName : "lib" + shortName);
        if (libraryFile == null) {
            throw new UnsatisfiedLinkError("Could not find library file for either ABIs.");
        }
        String path = libraryFile.getAbsolutePath();
        Log.d(TAG, "Attempting to load library: " + path);
        System.load(path);
        Log.d(TAG, "Successfully loaded library: " + path);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x008e A[SYNTHETIC, Splitter:B:24:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0093 A[Catch:{ IOException -> 0x0097 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File copySoFileIfRequired(java.lang.String r14) {
        /*
            java.io.File r6 = new java.io.File
            java.io.File r11 = privateNativeLibsDir
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.String r13 = ".so"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r6.<init>(r11, r12)
            boolean r11 = r6.exists()
            if (r11 == 0) goto L_0x0022
        L_0x0021:
            return r6
        L_0x0022:
            java.util.Map<java.lang.String, java.lang.String> r11 = abi1Libraries
            boolean r11 = r11.containsKey(r14)
            if (r11 != 0) goto L_0x0034
            java.util.Map<java.lang.String, java.lang.String> r11 = abi2Libraries
            boolean r11 = r11.containsKey(r14)
            if (r11 != 0) goto L_0x0034
            r6 = 0
            goto L_0x0021
        L_0x0034:
            java.util.Map<java.lang.String, java.lang.String> r11 = abi1Libraries
            boolean r11 = r11.containsKey(r14)
            if (r11 == 0) goto L_0x009e
            java.util.Map<java.lang.String, java.lang.String> r11 = abi1Libraries
            java.lang.Object r9 = r11.get(r14)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r0 = android.os.Build.CPU_ABI
        L_0x0046:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = nativeLibsDir
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r12 = "/"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r9)
            java.lang.String r10 = r11.toString()
            r3 = 0
            r7 = 0
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00b9 }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ all -> 0x00b9 }
            r11.<init>(r10)     // Catch:{ all -> 0x00b9 }
            r4.<init>(r11)     // Catch:{ all -> 0x00b9 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00bb }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ all -> 0x00bb }
            r11.<init>(r6)     // Catch:{ all -> 0x00bb }
            r8.<init>(r11)     // Catch:{ all -> 0x00bb }
            r11 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r11]     // Catch:{ all -> 0x0089 }
        L_0x007e:
            int r5 = r4.read(r1)     // Catch:{ all -> 0x0089 }
            if (r5 <= 0) goto L_0x00a9
            r11 = 0
            r8.write(r1, r11, r5)     // Catch:{ all -> 0x0089 }
            goto L_0x007e
        L_0x0089:
            r11 = move-exception
            r7 = r8
            r3 = r4
        L_0x008c:
            if (r3 == 0) goto L_0x0091
            r3.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0091:
            if (r7 == 0) goto L_0x0096
            r7.close()     // Catch:{ IOException -> 0x0097 }
        L_0x0096:
            throw r11     // Catch:{ IOException -> 0x0097 }
        L_0x0097:
            r2 = move-exception
        L_0x0098:
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            r11.<init>(r2)
            throw r11
        L_0x009e:
            java.util.Map<java.lang.String, java.lang.String> r11 = abi2Libraries
            java.lang.Object r9 = r11.get(r14)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r0 = android.os.Build.CPU_ABI2
            goto L_0x0046
        L_0x00a9:
            if (r4 == 0) goto L_0x00ae
            r4.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00ae:
            if (r8 == 0) goto L_0x0021
            r8.close()     // Catch:{ IOException -> 0x00b5 }
            goto L_0x0021
        L_0x00b5:
            r2 = move-exception
            r7 = r8
            r3 = r4
            goto L_0x0098
        L_0x00b9:
            r11 = move-exception
            goto L_0x008c
        L_0x00bb:
            r11 = move-exception
            r3 = r4
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.buck.android.support.exopackage.ExopackageSoLoader.copySoFileIfRequired(java.lang.String):java.io.File");
    }

    private static File getAbi1Metadata() {
        return new File(nativeLibsDir + Build.CPU_ABI + "/metadata.txt");
    }

    private static File getAbi2Metadata() {
        if (Build.CPU_ABI2.equals("unknown")) {
            return null;
        }
        return new File(nativeLibsDir + Build.CPU_ABI2 + "/metadata.txt");
    }
}
