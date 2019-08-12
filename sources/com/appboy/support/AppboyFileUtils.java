package com.appboy.support;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import com.facebook.common.util.UriUtil;
import com.facebook.places.model.PlaceFields;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppboyFileUtils {
    public static final List<String> REMOTE_SCHEMES = Collections.unmodifiableList(Arrays.asList(new String[]{UriUtil.HTTP_SCHEME, UriUtil.HTTPS_SCHEME, "ftp", "ftps", PlaceFields.ABOUT, "javascript"}));

    /* renamed from: a */
    private static final String f2910a = AppboyLogger.getAppboyLogTag(AppboyFileUtils.class);

    public static boolean canStoreAssetsLocally(Context context) {
        return VERSION.SDK_INT >= 19 || PermissionUtils.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static File getApplicationCacheDir(Context context) {
        return context.getCacheDir();
    }

    public static File getExternalStorage(String folderName) {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals("mounted")) {
            return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folderName);
        }
        AppboyLogger.m1735e(f2910a, "External storage state not mounted. State:" + externalStorageState);
        return null;
    }

    public static void deleteFileOrDirectory(File fileOrDirectory) {
        if (fileOrDirectory != null) {
            try {
                if (fileOrDirectory.exists()) {
                    if (fileOrDirectory.isDirectory()) {
                        for (String file : fileOrDirectory.list()) {
                            deleteFileOrDirectory(new File(fileOrDirectory, file));
                        }
                    }
                    fileOrDirectory.delete();
                }
            } catch (Exception e) {
                AppboyLogger.m1736e(f2910a, "Caught exception while trying to delete file or directory " + fileOrDirectory.getName(), e);
            }
        }
    }

    public static boolean isRemoteUri(Uri uri) {
        if (uri == null) {
            AppboyLogger.m1737i(f2910a, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (!StringUtils.isNullOrBlank(scheme)) {
            return REMOTE_SCHEMES.contains(scheme);
        }
        AppboyLogger.m1737i(f2910a, "Null or blank Uri scheme.");
        return false;
    }

    public static boolean isLocalUri(Uri uri) {
        if (uri == null) {
            AppboyLogger.m1737i(f2910a, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (StringUtils.isNullOrBlank(scheme) || scheme.equals(UriUtil.LOCAL_FILE_SCHEME)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0195 A[SYNTHETIC, Splitter:B:102:0x0195] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x019a A[Catch:{ IOException -> 0x01a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01b4 A[SYNTHETIC, Splitter:B:114:0x01b4] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01b9 A[Catch:{ IOException -> 0x01bd }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f8 A[SYNTHETIC, Splitter:B:48:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00fd A[Catch:{ IOException -> 0x0128 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0145 A[SYNTHETIC, Splitter:B:74:0x0145] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014a A[Catch:{ IOException -> 0x0150 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x016d A[SYNTHETIC, Splitter:B:88:0x016d] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0172 A[Catch:{ IOException -> 0x0178 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:69:0x0136=Splitter:B:69:0x0136, B:97:0x0186=Splitter:B:97:0x0186, B:83:0x015e=Splitter:B:83:0x015e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File downloadFileToPath(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            r1 = 0
            boolean r0 = com.appboy.Appboy.getOutboundNetworkRequestsOffline()
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = f2910a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SDK is offline. File not downloaded for url: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r2 = r2.toString()
            com.appboy.support.AppboyLogger.m1737i(r0, r2)
            r0 = r1
        L_0x0021:
            return r0
        L_0x0022:
            boolean r0 = com.appboy.support.StringUtils.isNullOrBlank(r10)
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = f2910a
            java.lang.String r2 = "Download directory null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.m1737i(r0, r2)
            r0 = r1
            goto L_0x0021
        L_0x0032:
            boolean r0 = com.appboy.support.StringUtils.isNullOrBlank(r11)
            if (r0 == 0) goto L_0x0042
            java.lang.String r0 = f2910a
            java.lang.String r2 = "Zip file url null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.m1737i(r0, r2)
            r0 = r1
            goto L_0x0021
        L_0x0042:
            boolean r0 = com.appboy.support.StringUtils.isNullOrBlank(r12)
            if (r0 == 0) goto L_0x0052
            java.lang.String r0 = f2910a
            java.lang.String r2 = "Output filename null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.m1737i(r0, r2)
            r0 = r1
            goto L_0x0021
        L_0x0052:
            java.io.File r0 = new java.io.File
            r0.<init>(r10)
            r0.mkdirs()
            boolean r0 = com.appboy.support.StringUtils.isNullOrBlank(r13)
            if (r0 != 0) goto L_0x0071
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.StringBuilder r0 = r0.append(r13)
            java.lang.String r12 = r0.toString()
        L_0x0071:
            java.io.File r2 = new java.io.File
            r2.<init>(r10, r12)
            r3 = 0
            r4 = 0
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x021b, IOException -> 0x0132, Exception -> 0x015a, Throwable -> 0x0182, all -> 0x01aa }
            r5.<init>(r11)     // Catch:{ MalformedURLException -> 0x021b, IOException -> 0x0132, Exception -> 0x015a, Throwable -> 0x0182, all -> 0x01aa }
            java.net.URLConnection r0 = r5.openConnection()     // Catch:{ MalformedURLException -> 0x021b, IOException -> 0x0132, Exception -> 0x015a, Throwable -> 0x0182, all -> 0x01aa }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ MalformedURLException -> 0x021b, IOException -> 0x0132, Exception -> 0x015a, Throwable -> 0x0182, all -> 0x01aa }
            int r6 = r0.getResponseCode()     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 == r7) goto L_0x00c0
            java.lang.String r2 = f2910a     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            java.lang.String r5 = "HTTP response code was %s. File with url %s could not be downloaded."
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            r8 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            r7[r8] = r6     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            r6 = 1
            r7[r6] = r11     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            java.lang.String r5 = java.lang.String.format(r5, r7)     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            com.appboy.support.AppboyLogger.m1733d(r2, r5)     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            if (r0 == 0) goto L_0x00a9
            r0.disconnect()
        L_0x00a9:
            if (r1 == 0) goto L_0x00ae
            r3.close()     // Catch:{ IOException -> 0x00b6 }
        L_0x00ae:
            if (r1 == 0) goto L_0x00b3
            r4.close()     // Catch:{ IOException -> 0x00b6 }
        L_0x00b3:
            r0 = r1
            goto L_0x0021
        L_0x00b6:
            r0 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00b3
        L_0x00c0:
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r3]     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            java.io.InputStream r3 = r5.openStream()     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            r4.<init>(r3)     // Catch:{ MalformedURLException -> 0x0221, IOException -> 0x0206, Exception -> 0x01f1, Throwable -> 0x01df, all -> 0x01c7 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ MalformedURLException -> 0x0228, IOException -> 0x020e, Exception -> 0x01f9, Throwable -> 0x01e6, all -> 0x01cd }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ MalformedURLException -> 0x0228, IOException -> 0x020e, Exception -> 0x01f9, Throwable -> 0x01e6, all -> 0x01cd }
            r5.<init>(r2)     // Catch:{ MalformedURLException -> 0x0228, IOException -> 0x020e, Exception -> 0x01f9, Throwable -> 0x01e6, all -> 0x01cd }
            r3.<init>(r5)     // Catch:{ MalformedURLException -> 0x0228, IOException -> 0x020e, Exception -> 0x01f9, Throwable -> 0x01e6, all -> 0x01cd }
        L_0x00d7:
            int r5 = r4.read(r6)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0215, Exception -> 0x0200, Throwable -> 0x01ec, all -> 0x01d2 }
            r7 = -1
            if (r5 == r7) goto L_0x0103
            r7 = 0
            r3.write(r6, r7, r5)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0215, Exception -> 0x0200, Throwable -> 0x01ec, all -> 0x01d2 }
            goto L_0x00d7
        L_0x00e3:
            r2 = move-exception
            r9 = r2
            r2 = r3
            r3 = r4
            r4 = r0
            r0 = r9
        L_0x00e9:
            java.lang.String r5 = f2910a     // Catch:{ all -> 0x01d7 }
            java.lang.String r6 = "MalformedURLException during download of file from url."
            com.appboy.support.AppboyLogger.m1736e(r5, r6, r0)     // Catch:{ all -> 0x01d7 }
            if (r4 == 0) goto L_0x00f6
            r4.disconnect()
        L_0x00f6:
            if (r3 == 0) goto L_0x00fb
            r3.close()     // Catch:{ IOException -> 0x0128 }
        L_0x00fb:
            if (r2 == 0) goto L_0x0100
            r2.close()     // Catch:{ IOException -> 0x0128 }
        L_0x0100:
            r0 = r1
            goto L_0x0021
        L_0x0103:
            r4.close()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0215, Exception -> 0x0200, Throwable -> 0x01ec, all -> 0x01d2 }
            r0.disconnect()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0215, Exception -> 0x0200, Throwable -> 0x01ec, all -> 0x01d2 }
            r3.close()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0215, Exception -> 0x0200, Throwable -> 0x01ec, all -> 0x01d2 }
            if (r0 == 0) goto L_0x0111
            r0.disconnect()
        L_0x0111:
            if (r4 == 0) goto L_0x0116
            r4.close()     // Catch:{ IOException -> 0x011e }
        L_0x0116:
            if (r3 == 0) goto L_0x011b
            r3.close()     // Catch:{ IOException -> 0x011e }
        L_0x011b:
            r0 = r2
            goto L_0x0021
        L_0x011e:
            r0 = move-exception
            java.lang.String r1 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r1, r3, r0)
            goto L_0x011b
        L_0x0128:
            r0 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x0100
        L_0x0132:
            r0 = move-exception
            r3 = r1
            r4 = r1
            r2 = r1
        L_0x0136:
            java.lang.String r5 = f2910a     // Catch:{ all -> 0x01dc }
            java.lang.String r6 = "IOException during download of file from url."
            com.appboy.support.AppboyLogger.m1736e(r5, r6, r0)     // Catch:{ all -> 0x01dc }
            if (r2 == 0) goto L_0x0143
            r2.disconnect()
        L_0x0143:
            if (r4 == 0) goto L_0x0148
            r4.close()     // Catch:{ IOException -> 0x0150 }
        L_0x0148:
            if (r3 == 0) goto L_0x014d
            r3.close()     // Catch:{ IOException -> 0x0150 }
        L_0x014d:
            r0 = r1
            goto L_0x0021
        L_0x0150:
            r0 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x014d
        L_0x015a:
            r0 = move-exception
            r3 = r1
            r4 = r1
            r2 = r1
        L_0x015e:
            java.lang.String r5 = f2910a     // Catch:{ all -> 0x01dc }
            java.lang.String r6 = "Exception during download of file from url."
            com.appboy.support.AppboyLogger.m1736e(r5, r6, r0)     // Catch:{ all -> 0x01dc }
            if (r2 == 0) goto L_0x016b
            r2.disconnect()
        L_0x016b:
            if (r4 == 0) goto L_0x0170
            r4.close()     // Catch:{ IOException -> 0x0178 }
        L_0x0170:
            if (r3 == 0) goto L_0x0175
            r3.close()     // Catch:{ IOException -> 0x0178 }
        L_0x0175:
            r0 = r1
            goto L_0x0021
        L_0x0178:
            r0 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x0175
        L_0x0182:
            r0 = move-exception
            r3 = r1
            r4 = r1
            r2 = r1
        L_0x0186:
            java.lang.String r5 = f2910a     // Catch:{ all -> 0x01dc }
            java.lang.String r6 = "Throwable during download of file from url."
            com.appboy.support.AppboyLogger.m1736e(r5, r6, r0)     // Catch:{ all -> 0x01dc }
            if (r2 == 0) goto L_0x0193
            r2.disconnect()
        L_0x0193:
            if (r4 == 0) goto L_0x0198
            r4.close()     // Catch:{ IOException -> 0x01a0 }
        L_0x0198:
            if (r3 == 0) goto L_0x019d
            r3.close()     // Catch:{ IOException -> 0x01a0 }
        L_0x019d:
            r0 = r1
            goto L_0x0021
        L_0x01a0:
            r0 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x019d
        L_0x01aa:
            r0 = move-exception
            r4 = r1
            r2 = r1
        L_0x01ad:
            if (r2 == 0) goto L_0x01b2
            r2.disconnect()
        L_0x01b2:
            if (r4 == 0) goto L_0x01b7
            r4.close()     // Catch:{ IOException -> 0x01bd }
        L_0x01b7:
            if (r1 == 0) goto L_0x01bc
            r1.close()     // Catch:{ IOException -> 0x01bd }
        L_0x01bc:
            throw r0
        L_0x01bd:
            r1 = move-exception
            java.lang.String r2 = f2910a
            java.lang.String r3 = "IOException during closing of file download streams."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x01bc
        L_0x01c7:
            r2 = move-exception
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x01ad
        L_0x01cd:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x01ad
        L_0x01d2:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r3
            goto L_0x01ad
        L_0x01d7:
            r0 = move-exception
            r1 = r2
            r2 = r4
            r4 = r3
            goto L_0x01ad
        L_0x01dc:
            r0 = move-exception
            r1 = r3
            goto L_0x01ad
        L_0x01df:
            r2 = move-exception
            r3 = r1
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0186
        L_0x01e6:
            r2 = move-exception
            r3 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0186
        L_0x01ec:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0186
        L_0x01f1:
            r2 = move-exception
            r3 = r1
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x015e
        L_0x01f9:
            r2 = move-exception
            r3 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x015e
        L_0x0200:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x015e
        L_0x0206:
            r2 = move-exception
            r3 = r1
            r4 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0136
        L_0x020e:
            r2 = move-exception
            r3 = r1
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0136
        L_0x0215:
            r2 = move-exception
            r9 = r2
            r2 = r0
            r0 = r9
            goto L_0x0136
        L_0x021b:
            r0 = move-exception
            r2 = r1
            r3 = r1
            r4 = r1
            goto L_0x00e9
        L_0x0221:
            r2 = move-exception
            r3 = r1
            r4 = r0
            r0 = r2
            r2 = r1
            goto L_0x00e9
        L_0x0228:
            r2 = move-exception
            r3 = r4
            r4 = r0
            r0 = r2
            r2 = r1
            goto L_0x00e9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.AppboyFileUtils.downloadFileToPath(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.io.File");
    }
}
