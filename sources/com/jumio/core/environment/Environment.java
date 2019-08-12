package com.jumio.core.environment;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.File;

public class Environment {
    private static boolean ALE_LIB_IS_LOADED = false;
    public static final String ALE_VERSION = "1.2.0";
    public static final String BUILD_VERSION = "JMSDK 2.4.0 (0-55)";
    private static boolean CPU_INFO_LIB_IS_LOADED = false;
    protected static String DATA_DIRECTORY = "/jumio/";
    private static boolean JNI_IMAGE_QUALITY_LIB_IS_LOADED = false;
    private static boolean JNI_INTERFACE_LIB_IS_LOADED = false;
    public static final String OCR_VERSION = "1.99.0";
    public static final String PHOTOPAY_VERSION = "5.4.0";

    public static File getDataDirectory(Context context) {
        File directory = new File(context.getFilesDir(), DATA_DIRECTORY);
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        return directory;
    }

    protected static void deleteDirectory(File path) {
        if (path != null && path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                        file.delete();
                    } else {
                        file.delete();
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049 A[Catch:{ Exception -> 0x004d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String readFile(java.io.File r7) {
        /*
            r3 = 0
            r0 = 0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            java.lang.String r6 = ""
            r5.<init>(r6)
            if (r7 == 0) goto L_0x0012
            boolean r6 = r7.isFile()
            if (r6 != 0) goto L_0x0016
        L_0x0012:
            java.lang.String r6 = ""
        L_0x0015:
            return r6
        L_0x0016:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0041 }
            r4.<init>(r7)     // Catch:{ all -> 0x0041 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0056 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x0056 }
            r6.<init>(r4)     // Catch:{ all -> 0x0056 }
            r1.<init>(r6)     // Catch:{ all -> 0x0056 }
            java.lang.String r6 = r1.readLine()     // Catch:{ all -> 0x0059 }
            r5.append(r6)     // Catch:{ all -> 0x0059 }
            if (r4 == 0) goto L_0x0031
            r4.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0031:
            if (r1 == 0) goto L_0x0036
            r1.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0036:
            r0 = r1
            r3 = r4
        L_0x0038:
            java.lang.String r6 = r5.toString()
            java.lang.String r6 = r6.trim()
            goto L_0x0015
        L_0x0041:
            r6 = move-exception
        L_0x0042:
            if (r3 == 0) goto L_0x0047
            r3.close()     // Catch:{ Exception -> 0x004d }
        L_0x0047:
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ Exception -> 0x004d }
        L_0x004c:
            throw r6     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            r2 = move-exception
        L_0x004e:
            com.jumio.commons.log.Log.printStackTrace(r2)
            goto L_0x0038
        L_0x0052:
            r2 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x004e
        L_0x0056:
            r6 = move-exception
            r3 = r4
            goto L_0x0042
        L_0x0059:
            r6 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.core.environment.Environment.readFile(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a A[SYNTHETIC, Splitter:B:10:0x001a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void writeFile(java.lang.String r5, java.io.File r6) {
        /*
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0017 }
            r1.<init>(r6)     // Catch:{ all -> 0x0017 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x0017 }
            r3.<init>(r1)     // Catch:{ all -> 0x0017 }
            r3.write(r5)     // Catch:{ all -> 0x0029 }
            if (r3 == 0) goto L_0x0016
            r3.flush()     // Catch:{ Exception -> 0x0026 }
            r3.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0016:
            return
        L_0x0017:
            r4 = move-exception
        L_0x0018:
            if (r2 == 0) goto L_0x0020
            r2.flush()     // Catch:{ Exception -> 0x0021 }
            r2.close()     // Catch:{ Exception -> 0x0021 }
        L_0x0020:
            throw r4     // Catch:{ Exception -> 0x0021 }
        L_0x0021:
            r0 = move-exception
        L_0x0022:
            com.jumio.commons.log.Log.printStackTrace(r0)
            goto L_0x0016
        L_0x0026:
            r0 = move-exception
            r2 = r3
            goto L_0x0022
        L_0x0029:
            r4 = move-exception
            r2 = r3
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.core.environment.Environment.writeFile(java.lang.String, java.io.File):void");
    }

    public static void checkOcrVersion(Context context) {
        File dataDirectory = getDataDirectory(context);
        File version = new File(dataDirectory, "cv");
        if (!"1.99.0".equals(readFile(version))) {
            deleteDirectory(dataDirectory);
            writeFile("1.99.0", version);
        }
    }

    @Deprecated
    public static String extractFile(Context context, String filename, String hash, String extension) {
        return extractFile(context, Environment.class, filename, hash, extension);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0075 A[SYNTHETIC, Splitter:B:20:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a A[SYNTHETIC, Splitter:B:23:0x007a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String extractFile(android.content.Context r13, java.lang.Class<?> r14, java.lang.String r15, java.lang.String r16, java.lang.String r17) {
        /*
            java.io.File r3 = new java.io.File
            java.io.File r11 = getDataDirectory(r13)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r12 = r12.append(r15)
            r0 = r17
            java.lang.StringBuilder r12 = r12.append(r0)
            java.lang.String r12 = r12.toString()
            r3.<init>(r11, r12)
            java.lang.String r11 = r3.getName()
            java.lang.String r12 = "_"
            boolean r11 = r11.startsWith(r12)
            if (r11 == 0) goto L_0x003c
            java.lang.String r11 = r3.getName()
            r12 = 1
            java.lang.String r10 = r11.substring(r12)
            java.io.File r4 = new java.io.File
            java.io.File r11 = r3.getParentFile()
            r4.<init>(r11, r10)
            r3 = r4
        L_0x003c:
            java.lang.String r9 = calculateHash(r3)
            boolean r11 = r3.isFile()
            if (r11 == 0) goto L_0x004e
            r0 = r16
            boolean r11 = r0.equals(r9)
            if (r11 != 0) goto L_0x0096
        L_0x004e:
            r11 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r11]
            boolean r11 = r3.isFile()
            if (r11 == 0) goto L_0x0084
            r3.delete()
        L_0x005b:
            r7 = 0
            r5 = 0
            java.io.InputStream r7 = r14.getResourceAsStream(r15)     // Catch:{ all -> 0x00b2 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x00b2 }
            r6.<init>(r3)     // Catch:{ all -> 0x00b2 }
        L_0x0066:
            int r8 = r7.read(r1)     // Catch:{ all -> 0x0071 }
            if (r8 <= 0) goto L_0x008c
            r11 = 0
            r6.write(r1, r11, r8)     // Catch:{ all -> 0x0071 }
            goto L_0x0066
        L_0x0071:
            r11 = move-exception
            r5 = r6
        L_0x0073:
            if (r7 == 0) goto L_0x0078
            r7.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x0078:
            if (r5 == 0) goto L_0x007d
            r5.close()     // Catch:{ IOException -> 0x00ad }
        L_0x007d:
            throw r11     // Catch:{ Exception -> 0x007e }
        L_0x007e:
            r2 = move-exception
        L_0x007f:
            com.jumio.commons.log.Log.printStackTrace(r2)
            r11 = 0
        L_0x0083:
            return r11
        L_0x0084:
            java.io.File r11 = r3.getParentFile()
            r11.mkdirs()
            goto L_0x005b
        L_0x008c:
            if (r7 == 0) goto L_0x0091
            r7.close()     // Catch:{ IOException -> 0x009b }
        L_0x0091:
            if (r6 == 0) goto L_0x0096
            r6.close()     // Catch:{ IOException -> 0x00a3 }
        L_0x0096:
            java.lang.String r11 = r3.getAbsolutePath()
            goto L_0x0083
        L_0x009b:
            r2 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r2)     // Catch:{ Exception -> 0x00a0 }
            goto L_0x0091
        L_0x00a0:
            r2 = move-exception
            r5 = r6
            goto L_0x007f
        L_0x00a3:
            r2 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r2)     // Catch:{ Exception -> 0x00a0 }
            goto L_0x0096
        L_0x00a8:
            r2 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r2)     // Catch:{ Exception -> 0x007e }
            goto L_0x0078
        L_0x00ad:
            r2 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r2)     // Catch:{ Exception -> 0x007e }
            goto L_0x007d
        L_0x00b2:
            r11 = move-exception
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.core.environment.Environment.extractFile(android.content.Context, java.lang.Class, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static synchronized boolean loadCpuInfoLib() {
        synchronized (Environment.class) {
            if (!CPU_INFO_LIB_IS_LOADED) {
                System.loadLibrary("cpuinfo");
                CPU_INFO_LIB_IS_LOADED = true;
            }
        }
        return true;
    }

    public static synchronized boolean loadAleLib() {
        synchronized (Environment.class) {
            if (!ALE_LIB_IS_LOADED) {
                System.loadLibrary("aleInterface");
                ALE_LIB_IS_LOADED = true;
            }
        }
        return true;
    }

    public static synchronized boolean loadJniInterfaceLib() {
        synchronized (Environment.class) {
            if (!JNI_INTERFACE_LIB_IS_LOADED) {
                System.loadLibrary("jniInterface");
                JNI_INTERFACE_LIB_IS_LOADED = true;
            }
        }
        return true;
    }

    public static synchronized boolean loadJniImageQualityLib() {
        synchronized (Environment.class) {
            if (!JNI_IMAGE_QUALITY_LIB_IS_LOADED) {
                System.loadLibrary("jniImageQualityAcquisition");
                JNI_IMAGE_QUALITY_LIB_IS_LOADED = true;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[SYNTHETIC, Splitter:B:17:0x0039] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String calculateHash(java.io.File r13) {
        /*
            r9 = 0
            r5 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            boolean r10 = r13.isFile()
            if (r10 != 0) goto L_0x000f
            r9 = 0
        L_0x000e:
            return r9
        L_0x000f:
            java.lang.String r10 = "SHA-256"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r10)     // Catch:{ Exception -> 0x003d }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x007b }
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ all -> 0x007b }
            java.lang.String r11 = r13.getPath()     // Catch:{ all -> 0x007b }
            r10.<init>(r11)     // Catch:{ all -> 0x007b }
            r6.<init>(r10)     // Catch:{ all -> 0x007b }
            r10 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r10]     // Catch:{ all -> 0x0035 }
            r8 = -1
        L_0x0029:
            int r8 = r6.read(r0)     // Catch:{ all -> 0x0035 }
            r10 = -1
            if (r8 == r10) goto L_0x0043
            r10 = 0
            r4.update(r0, r10, r8)     // Catch:{ all -> 0x0035 }
            goto L_0x0029
        L_0x0035:
            r9 = move-exception
            r5 = r6
        L_0x0037:
            if (r5 == 0) goto L_0x003c
            r5.close()     // Catch:{ IOException -> 0x0074 }
        L_0x003c:
            throw r9     // Catch:{ Exception -> 0x003d }
        L_0x003d:
            r9 = move-exception
        L_0x003e:
            java.lang.String r9 = r3.toString()
            goto L_0x000e
        L_0x0043:
            if (r6 == 0) goto L_0x0048
            r6.close()     // Catch:{ IOException -> 0x006c }
        L_0x0048:
            byte[] r7 = r4.digest()     // Catch:{ Exception -> 0x0071 }
            int r10 = r7.length     // Catch:{ Exception -> 0x0071 }
        L_0x004d:
            if (r9 >= r10) goto L_0x0079
            byte r2 = r7[r9]     // Catch:{ Exception -> 0x0071 }
            r11 = r2 & 240(0xf0, float:3.36E-43)
            int r11 = r11 >> 4
            r12 = 16
            char r11 = java.lang.Character.forDigit(r11, r12)     // Catch:{ Exception -> 0x0071 }
            r3.append(r11)     // Catch:{ Exception -> 0x0071 }
            r11 = r2 & 15
            r12 = 16
            char r11 = java.lang.Character.forDigit(r11, r12)     // Catch:{ Exception -> 0x0071 }
            r3.append(r11)     // Catch:{ Exception -> 0x0071 }
            int r9 = r9 + 1
            goto L_0x004d
        L_0x006c:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x0071 }
            goto L_0x0048
        L_0x0071:
            r9 = move-exception
            r5 = r6
            goto L_0x003e
        L_0x0074:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x003d }
            goto L_0x003c
        L_0x0079:
            r5 = r6
            goto L_0x003e
        L_0x007b:
            r9 = move-exception
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.core.environment.Environment.calculateHash(java.io.File):java.lang.String");
    }

    public static boolean isLollipop() {
        return VERSION.SDK_INT == 21;
    }
}
