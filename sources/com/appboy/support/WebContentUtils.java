package com.appboy.support;

import android.content.Context;
import java.io.File;

public class WebContentUtils {

    /* renamed from: a */
    private static final String f2920a = AppboyLogger.getAppboyLogTag(WebContentUtils.class);

    public static String getLocalHtmlUrlFromRemoteUrl(File localDirectory, String remoteZipUrl) {
        if (localDirectory == null) {
            AppboyLogger.m1739w(f2920a, "Internal cache directory is null. No local URL will be created.");
            return null;
        } else if (StringUtils.isNullOrBlank(remoteZipUrl)) {
            AppboyLogger.m1739w(f2920a, "Remote zip url is null or empty. No local URL will be created.");
            return null;
        } else {
            String absolutePath = localDirectory.getAbsolutePath();
            String valueOf = String.valueOf(IntentUtils.getRequestCode());
            String str = absolutePath + "/" + valueOf;
            AppboyLogger.m1733d(f2920a, "Starting download of url: " + remoteZipUrl);
            File downloadFileToPath = AppboyFileUtils.downloadFileToPath(str, remoteZipUrl, valueOf, ".zip");
            if (downloadFileToPath == null) {
                AppboyLogger.m1733d(f2920a, "Could not download zip file to local storage.");
                AppboyFileUtils.deleteFileOrDirectory(new File(str));
                return null;
            }
            AppboyLogger.m1733d(f2920a, "Html content zip downloaded.");
            if (!m1742a(str, downloadFileToPath)) {
                AppboyLogger.m1739w(f2920a, "Error during the zip unpack.");
                AppboyFileUtils.deleteFileOrDirectory(new File(str));
                return null;
            }
            AppboyLogger.m1733d(f2920a, "Html content zip unpacked.");
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0081 A[SYNTHETIC, Splitter:B:24:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086 A[Catch:{ IOException -> 0x008a }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00de A[SYNTHETIC, Splitter:B:55:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e3 A[Catch:{ IOException -> 0x00e8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ff A[SYNTHETIC, Splitter:B:66:0x00ff] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0104 A[Catch:{ IOException -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0118 A[SYNTHETIC, Splitter:B:75:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011d A[Catch:{ IOException -> 0x0121 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00d4=Splitter:B:52:0x00d4, B:63:0x00f5=Splitter:B:63:0x00f5} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m1742a(java.lang.String r8, java.io.File r9) {
        /*
            r2 = 0
            r0 = 0
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r8)
            if (r1 == 0) goto L_0x0011
            java.lang.String r1 = f2920a
            java.lang.String r2 = "Unpack directory null or blank. Zip file not unpacked."
            com.appboy.support.AppboyLogger.m1737i(r1, r2)
        L_0x0010:
            return r0
        L_0x0011:
            if (r9 != 0) goto L_0x001c
            java.lang.String r1 = f2920a
            java.lang.String r2 = "Zip file is null. Zip file not unpacked."
            com.appboy.support.AppboyLogger.m1737i(r1, r2)
            goto L_0x0010
        L_0x001c:
            java.io.File r1 = new java.io.File
            r1.<init>(r8)
            r1.mkdirs()
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            r1.<init>(r9)     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            java.util.zip.ZipInputStream r4 = new java.util.zip.ZipInputStream     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            r4.<init>(r3)     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x00d2, Exception -> 0x00f3, all -> 0x0114 }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
        L_0x0037:
            java.util.zip.ZipEntry r3 = r4.getNextEntry()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            if (r3 == 0) goto L_0x00b7
            java.lang.String r5 = r3.getName()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.String r6 = r5.toLowerCase()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.String r7 = "__macosx"
            boolean r6 = r6.startsWith(r7)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            if (r6 != 0) goto L_0x0037
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r6.<init>()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.String r7 = "/"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.StringBuilder r5 = r6.append(r5)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.lang.String r5 = r5.toString()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            boolean r3 = r3.isDirectory()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            if (r3 == 0) goto L_0x0095
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r3.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r3.mkdirs()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            goto L_0x0037
        L_0x0075:
            r1 = move-exception
            r3 = r4
        L_0x0077:
            java.lang.String r4 = f2920a     // Catch:{ all -> 0x0130 }
            java.lang.String r5 = "FileNotFoundException during unpack of zip file."
            com.appboy.support.AppboyLogger.m1736e(r4, r5, r1)     // Catch:{ all -> 0x0130 }
            if (r3 == 0) goto L_0x0084
            r3.close()     // Catch:{ IOException -> 0x008a }
        L_0x0084:
            if (r2 == 0) goto L_0x0010
            r2.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x0010
        L_0x008a:
            r1 = move-exception
            java.lang.String r2 = f2920a
            java.lang.String r3 = "IOException during closing of zip file unpacking streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x0010
        L_0x0095:
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r6.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r3.<init>(r6)     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
        L_0x009f:
            int r2 = r4.read(r1)     // Catch:{ FileNotFoundException -> 0x00ab, IOException -> 0x013a, Exception -> 0x0135, all -> 0x012d }
            r5 = -1
            if (r2 == r5) goto L_0x00af
            r5 = 0
            r3.write(r1, r5, r2)     // Catch:{ FileNotFoundException -> 0x00ab, IOException -> 0x013a, Exception -> 0x0135, all -> 0x012d }
            goto L_0x009f
        L_0x00ab:
            r1 = move-exception
            r2 = r3
            r3 = r4
            goto L_0x0077
        L_0x00af:
            r3.close()     // Catch:{ FileNotFoundException -> 0x00ab, IOException -> 0x013a, Exception -> 0x0135, all -> 0x012d }
            r4.closeEntry()     // Catch:{ FileNotFoundException -> 0x00ab, IOException -> 0x013a, Exception -> 0x0135, all -> 0x012d }
            r2 = r3
            goto L_0x0037
        L_0x00b7:
            r4.close()     // Catch:{ FileNotFoundException -> 0x0075, IOException -> 0x0138, Exception -> 0x0133 }
            r0 = 1
            if (r4 == 0) goto L_0x00c0
            r4.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00c0:
            if (r2 == 0) goto L_0x0010
            r2.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x0010
        L_0x00c7:
            r1 = move-exception
            java.lang.String r2 = f2920a
            java.lang.String r3 = "IOException during closing of zip file unpacking streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x0010
        L_0x00d2:
            r1 = move-exception
            r4 = r2
        L_0x00d4:
            java.lang.String r3 = f2920a     // Catch:{ all -> 0x012b }
            java.lang.String r5 = "IOException during unpack of zip file."
            com.appboy.support.AppboyLogger.m1736e(r3, r5, r1)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x00e1
            r4.close()     // Catch:{ IOException -> 0x00e8 }
        L_0x00e1:
            if (r2 == 0) goto L_0x0010
            r2.close()     // Catch:{ IOException -> 0x00e8 }
            goto L_0x0010
        L_0x00e8:
            r1 = move-exception
            java.lang.String r2 = f2920a
            java.lang.String r3 = "IOException during closing of zip file unpacking streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x0010
        L_0x00f3:
            r1 = move-exception
            r4 = r2
        L_0x00f5:
            java.lang.String r3 = f2920a     // Catch:{ all -> 0x012b }
            java.lang.String r5 = "Exception during unpack of zip file."
            com.appboy.support.AppboyLogger.m1736e(r3, r5, r1)     // Catch:{ all -> 0x012b }
            if (r4 == 0) goto L_0x0102
            r4.close()     // Catch:{ IOException -> 0x0109 }
        L_0x0102:
            if (r2 == 0) goto L_0x0010
            r2.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x0010
        L_0x0109:
            r1 = move-exception
            java.lang.String r2 = f2920a
            java.lang.String r3 = "IOException during closing of zip file unpacking streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x0010
        L_0x0114:
            r0 = move-exception
            r4 = r2
        L_0x0116:
            if (r4 == 0) goto L_0x011b
            r4.close()     // Catch:{ IOException -> 0x0121 }
        L_0x011b:
            if (r2 == 0) goto L_0x0120
            r2.close()     // Catch:{ IOException -> 0x0121 }
        L_0x0120:
            throw r0
        L_0x0121:
            r1 = move-exception
            java.lang.String r2 = f2920a
            java.lang.String r3 = "IOException during closing of zip file unpacking streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x0120
        L_0x012b:
            r0 = move-exception
            goto L_0x0116
        L_0x012d:
            r0 = move-exception
            r2 = r3
            goto L_0x0116
        L_0x0130:
            r0 = move-exception
            r4 = r3
            goto L_0x0116
        L_0x0133:
            r1 = move-exception
            goto L_0x00f5
        L_0x0135:
            r1 = move-exception
            r2 = r3
            goto L_0x00f5
        L_0x0138:
            r1 = move-exception
            goto L_0x00d4
        L_0x013a:
            r1 = move-exception
            r2 = r3
            goto L_0x00d4
        L_0x013d:
            r1 = move-exception
            r3 = r2
            goto L_0x0077
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.WebContentUtils.m1742a(java.lang.String, java.io.File):boolean");
    }

    public static File getHtmlInAppMessageAssetCacheDirectory(Context context) {
        return new File(context.getCacheDir().getPath() + "/" + "appboy-html-inapp-messages");
    }
}
