package com.appboy.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.io.File;
import java.io.FileOutputStream;

public class AppboyImageUtils {

    /* renamed from: a */
    private static final String f2911a = AppboyLogger.getAppboyLogTag(AppboyImageUtils.class);

    public static Bitmap getBitmap(Uri uri) {
        if (uri == null) {
            AppboyLogger.m1737i(f2911a, "Null Uri received. Not getting image.");
            return null;
        } else if (AppboyFileUtils.isLocalUri(uri)) {
            return m1732b(uri);
        } else {
            if (AppboyFileUtils.isRemoteUri(uri)) {
                return m1731a(uri);
            }
            AppboyLogger.m1739w(f2911a, "Uri with unknown scheme received. Not getting image.");
            return null;
        }
    }

    public static Uri storePushBitmapInExternalStorage(Context context, Bitmap imageBitmap, String imageFilenameBase, String folderName) {
        if (context == null) {
            AppboyLogger.m1739w(f2911a, "Received null context. Doing nothing.");
            return null;
        } else if (imageBitmap == null) {
            AppboyLogger.m1739w(f2911a, "Received null bitmap. Doing nothing.");
            return null;
        } else if (StringUtils.isNullOrBlank(imageFilenameBase)) {
            AppboyLogger.m1739w(f2911a, "Received null or blank image filename base. Doing nothing.");
            return null;
        } else if (StringUtils.isNullOrBlank(folderName)) {
            AppboyLogger.m1739w(f2911a, "Received null or blank image folder name for externally stored image. Doing nothing.");
            return null;
        } else {
            try {
                File externalStorage = AppboyFileUtils.getExternalStorage(folderName);
                if (externalStorage == null) {
                    AppboyLogger.m1739w(f2911a, "Image storage directory was null. Doing nothing.");
                    return null;
                }
                if (!externalStorage.exists()) {
                    externalStorage.mkdirs();
                }
                File file = new File(externalStorage, imageFilenameBase + ".png");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                imageBitmap.compress(CompressFormat.PNG, 0, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
                AppboyLogger.m1737i(f2911a, "Stored image locally at " + file.getAbsolutePath());
                return Uri.fromFile(file);
            } catch (Exception e) {
                AppboyLogger.m1736e(f2911a, "Exception occurred when attempting to store image locally.", e);
                return null;
            }
        }
    }

    public static int getPixelsFromDensityAndDp(int dpi, int dp) {
        return Math.abs((dpi * dp) / 160);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v8, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r3v10, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v11, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v14, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r0v16, types: [android.graphics.Bitmap] */
    /* JADX WARNING: type inference failed for: r3v16, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v17, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r0v21, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r2v21 */
    /* JADX WARNING: type inference failed for: r3v21 */
    /* JADX WARNING: type inference failed for: r10v1 */
    /* JADX WARNING: type inference failed for: r2v23 */
    /* JADX WARNING: type inference failed for: r3v22 */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: type inference failed for: r2v25 */
    /* JADX WARNING: type inference failed for: r3v23 */
    /* JADX WARNING: type inference failed for: r10v3 */
    /* JADX WARNING: type inference failed for: r2v27 */
    /* JADX WARNING: type inference failed for: r3v24 */
    /* JADX WARNING: type inference failed for: r10v4 */
    /* JADX WARNING: type inference failed for: r2v29 */
    /* JADX WARNING: type inference failed for: r3v25 */
    /* JADX WARNING: type inference failed for: r10v5 */
    /* JADX WARNING: type inference failed for: r2v31 */
    /* JADX WARNING: type inference failed for: r3v26, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v33 */
    /* JADX WARNING: type inference failed for: r2v35 */
    /* JADX WARNING: type inference failed for: r2v37 */
    /* JADX WARNING: type inference failed for: r2v39 */
    /* JADX WARNING: type inference failed for: r2v41 */
    /* JADX WARNING: type inference failed for: r2v42 */
    /* JADX WARNING: type inference failed for: r0v36, types: [android.graphics.Bitmap] */
    /* JADX WARNING: type inference failed for: r0v39, types: [android.graphics.Bitmap] */
    /* JADX WARNING: type inference failed for: r3v31 */
    /* JADX WARNING: type inference failed for: r2v49 */
    /* JADX WARNING: type inference failed for: r3v32 */
    /* JADX WARNING: type inference failed for: r3v33 */
    /* JADX WARNING: type inference failed for: r2v50 */
    /* JADX WARNING: type inference failed for: r2v51 */
    /* JADX WARNING: type inference failed for: r3v34 */
    /* JADX WARNING: type inference failed for: r3v35 */
    /* JADX WARNING: type inference failed for: r2v52 */
    /* JADX WARNING: type inference failed for: r2v53 */
    /* JADX WARNING: type inference failed for: r3v36 */
    /* JADX WARNING: type inference failed for: r3v37 */
    /* JADX WARNING: type inference failed for: r2v54 */
    /* JADX WARNING: type inference failed for: r2v55 */
    /* JADX WARNING: type inference failed for: r3v38 */
    /* JADX WARNING: type inference failed for: r3v39 */
    /* JADX WARNING: type inference failed for: r2v56 */
    /* JADX WARNING: type inference failed for: r2v57 */
    /* JADX WARNING: type inference failed for: r3v40 */
    /* JADX WARNING: type inference failed for: r3v41 */
    /* JADX WARNING: type inference failed for: r2v58 */
    /* JADX WARNING: type inference failed for: r2v59 */
    /* JADX WARNING: type inference failed for: r3v42 */
    /* JADX WARNING: type inference failed for: r3v43 */
    /* JADX WARNING: type inference failed for: r3v44 */
    /* JADX WARNING: type inference failed for: r3v45 */
    /* JADX WARNING: type inference failed for: r3v46 */
    /* JADX WARNING: type inference failed for: r3v47 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v0
      assigns: []
      uses: []
      mth insns count: 230
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cf A[SYNTHETIC, Splitter:B:49:0x00cf] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f9 A[SYNTHETIC, Splitter:B:60:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0123 A[SYNTHETIC, Splitter:B:71:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x014e A[SYNTHETIC, Splitter:B:82:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0168 A[SYNTHETIC, Splitter:B:91:0x0168] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:55:0x00e0=Splitter:B:55:0x00e0, B:77:0x0135=Splitter:B:77:0x0135, B:44:0x00b6=Splitter:B:44:0x00b6, B:32:0x008a=Splitter:B:32:0x008a, B:66:0x010a=Splitter:B:66:0x010a} */
    /* JADX WARNING: Unknown variable types count: 31 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap m1731a(android.net.Uri r11) {
        /*
            r1 = 0
            java.lang.String r4 = r11.toString()
            boolean r0 = com.appboy.Appboy.getOutboundNetworkRequestsOffline()
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = f2911a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SDK is in offline mode, not downloading remote bitmap with uri: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.appboy.support.AppboyLogger.m1737i(r0, r2)
            r0 = r1
        L_0x0025:
            return r0
        L_0x0026:
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ OutOfMemoryError -> 0x0087, UnknownHostException -> 0x00b3, MalformedURLException -> 0x00dd, Exception -> 0x0107, Throwable -> 0x0132, all -> 0x015e }
            r3.<init>(r4)     // Catch:{ OutOfMemoryError -> 0x0087, UnknownHostException -> 0x00b3, MalformedURLException -> 0x00dd, Exception -> 0x0107, Throwable -> 0x0132, all -> 0x015e }
            java.net.URLConnection r0 = r3.openConnection()     // Catch:{ OutOfMemoryError -> 0x0087, UnknownHostException -> 0x00b3, MalformedURLException -> 0x00dd, Exception -> 0x0107, Throwable -> 0x0132, all -> 0x015e }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ OutOfMemoryError -> 0x0087, UnknownHostException -> 0x00b3, MalformedURLException -> 0x00dd, Exception -> 0x0107, Throwable -> 0x0132, all -> 0x015e }
            int r5 = r0.getResponseCode()     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x0069
            java.lang.String r6 = f2911a     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            java.lang.String r7 = "HTTP response code was %s. Bitmap with url %s could not be downloaded."
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            r9 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            r8[r9] = r5     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            r5 = 1
            r8[r5] = r3     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            java.lang.String r3 = java.lang.String.format(r7, r8)     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            com.appboy.support.AppboyLogger.m1739w(r6, r3)     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            if (r0 == 0) goto L_0x0058
            r0.disconnect()
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r2.close()     // Catch:{ IOException -> 0x005f }
        L_0x005d:
            r0 = r1
            goto L_0x0025
        L_0x005f:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x005d
        L_0x0069:
            java.io.InputStream r3 = r3.openStream()     // Catch:{ OutOfMemoryError -> 0x01b4, UnknownHostException -> 0x01a7, MalformedURLException -> 0x019a, Exception -> 0x018d, Throwable -> 0x0182, all -> 0x0176 }
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r3)     // Catch:{ OutOfMemoryError -> 0x01bb, UnknownHostException -> 0x01ae, MalformedURLException -> 0x01a1, Exception -> 0x0194, Throwable -> 0x0188, all -> 0x017c }
            if (r0 == 0) goto L_0x0076
            r0.disconnect()
        L_0x0076:
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x007d }
        L_0x007b:
            r0 = r1
            goto L_0x0025
        L_0x007d:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x007b
        L_0x0087:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x008a:
            java.lang.String r5 = f2911a     // Catch:{ all -> 0x0180 }
            java.lang.String r6 = "Out of Memory Error in image bitmap download for Uri: %s."
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0180 }
            r8 = 0
            r7[r8] = r4     // Catch:{ all -> 0x0180 }
            java.lang.String r4 = java.lang.String.format(r6, r7)     // Catch:{ all -> 0x0180 }
            com.appboy.support.AppboyLogger.m1736e(r5, r4, r0)     // Catch:{ all -> 0x0180 }
            if (r2 == 0) goto L_0x00a1
            r2.disconnect()
        L_0x00a1:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x00a9 }
        L_0x00a6:
            r0 = r1
            goto L_0x0025
        L_0x00a9:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00a6
        L_0x00b3:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x00b6:
            java.lang.String r5 = f2911a     // Catch:{ all -> 0x0180 }
            java.lang.String r6 = "Unknown Host Exception in image bitmap download for Uri: %s. Device may be offline."
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0180 }
            r8 = 0
            r7[r8] = r4     // Catch:{ all -> 0x0180 }
            java.lang.String r4 = java.lang.String.format(r6, r7)     // Catch:{ all -> 0x0180 }
            com.appboy.support.AppboyLogger.m1736e(r5, r4, r0)     // Catch:{ all -> 0x0180 }
            if (r2 == 0) goto L_0x00cd
            r2.disconnect()
        L_0x00cd:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x00d3 }
            goto L_0x00a6
        L_0x00d3:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00a6
        L_0x00dd:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x00e0:
            java.lang.String r5 = f2911a     // Catch:{ all -> 0x0180 }
            java.lang.String r6 = "Malformed URL Exception in image bitmap download for Uri: %s. Image Uri may be corrupted."
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0180 }
            r8 = 0
            r7[r8] = r4     // Catch:{ all -> 0x0180 }
            java.lang.String r4 = java.lang.String.format(r6, r7)     // Catch:{ all -> 0x0180 }
            com.appboy.support.AppboyLogger.m1736e(r5, r4, r0)     // Catch:{ all -> 0x0180 }
            if (r2 == 0) goto L_0x00f7
            r2.disconnect()
        L_0x00f7:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x00fd }
            goto L_0x00a6
        L_0x00fd:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00a6
        L_0x0107:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x010a:
            java.lang.String r5 = f2911a     // Catch:{ all -> 0x0180 }
            java.lang.String r6 = "Exception in image bitmap download for Uri: %s"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0180 }
            r8 = 0
            r7[r8] = r4     // Catch:{ all -> 0x0180 }
            java.lang.String r4 = java.lang.String.format(r6, r7)     // Catch:{ all -> 0x0180 }
            com.appboy.support.AppboyLogger.m1736e(r5, r4, r0)     // Catch:{ all -> 0x0180 }
            if (r2 == 0) goto L_0x0121
            r2.disconnect()
        L_0x0121:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x0127 }
            goto L_0x00a6
        L_0x0127:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00a6
        L_0x0132:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0135:
            java.lang.String r5 = f2911a     // Catch:{ all -> 0x0180 }
            java.lang.String r6 = "Throwable caught in image bitmap download for Uri: %s"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0180 }
            r8 = 0
            r7[r8] = r4     // Catch:{ all -> 0x0180 }
            java.lang.String r4 = java.lang.String.format(r6, r7)     // Catch:{ all -> 0x0180 }
            com.appboy.support.AppboyLogger.m1736e(r5, r4, r0)     // Catch:{ all -> 0x0180 }
            if (r2 == 0) goto L_0x014c
            r2.disconnect()
        L_0x014c:
            if (r3 == 0) goto L_0x00a6
            r3.close()     // Catch:{ IOException -> 0x0153 }
            goto L_0x00a6
        L_0x0153:
            r0 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r0)
            goto L_0x00a6
        L_0x015e:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0161:
            if (r2 == 0) goto L_0x0166
            r2.disconnect()
        L_0x0166:
            if (r3 == 0) goto L_0x016b
            r3.close()     // Catch:{ IOException -> 0x016c }
        L_0x016b:
            throw r0
        L_0x016c:
            r1 = move-exception
            java.lang.String r2 = f2911a
            java.lang.String r3 = "IOException during closing of bitmap download stream."
            com.appboy.support.AppboyLogger.m1736e(r2, r3, r1)
            goto L_0x016b
        L_0x0176:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x0161
        L_0x017c:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x0161
        L_0x0180:
            r0 = move-exception
            goto L_0x0161
        L_0x0182:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x0135
        L_0x0188:
            r2 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x0135
        L_0x018d:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x010a
        L_0x0194:
            r2 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x010a
        L_0x019a:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x00e0
        L_0x01a1:
            r2 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x00e0
        L_0x01a7:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x00b6
        L_0x01ae:
            r2 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x00b6
        L_0x01b4:
            r2 = move-exception
            r3 = r1
            r10 = r0
            r0 = r2
            r2 = r10
            goto L_0x008a
        L_0x01bb:
            r2 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x008a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.AppboyImageUtils.m1731a(android.net.Uri):android.graphics.Bitmap");
    }

    /* renamed from: b */
    private static Bitmap m1732b(Uri uri) {
        try {
            File file = new File(uri.getPath());
            if (file.exists()) {
                AppboyLogger.m1737i(f2911a, "Retrieving image from path: " + file.getAbsolutePath());
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        } catch (OutOfMemoryError e) {
            AppboyLogger.m1736e(f2911a, String.format("Out of Memory Error in local bitmap file retrieval for Uri: %s.", new Object[]{uri.toString()}), e);
        } catch (Exception e2) {
            AppboyLogger.m1736e(f2911a, "Exception occurred when attempting to retrieve local bitmap.", e2);
        } catch (Throwable th) {
            AppboyLogger.m1736e(f2911a, String.format("Throwable caught in local bitmap file retrieval for Uri: %s", new Object[]{uri.toString()}), th);
        }
        return null;
    }

    public static int getImageLoaderCacheSize() {
        return Math.max(1024, Math.min((int) Math.min(Runtime.getRuntime().maxMemory() / 32, 2147483647L), 1048576));
    }
}
