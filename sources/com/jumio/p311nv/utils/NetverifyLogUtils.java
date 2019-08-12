package com.jumio.p311nv.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.LogUtils;
import com.jumio.p311nv.extraction.JumioRect;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.jumio.nv.utils.NetverifyLogUtils */
public class NetverifyLogUtils extends LogUtils {
    private static final String FILE_UPLOAD_IMAGE = "scaled";
    private static final String FILE_UPLOAD_IMAGE_WITH_COORDS = "scaled_with_coords.png";

    /* renamed from: com.jumio.nv.utils.NetverifyLogUtils$a */
    public static class C4475a {

        /* renamed from: a */
        private static ArrayList<Integer> f3469a = null;

        /* renamed from: a */
        public static void m2008a(int i) {
            if (f3469a == null) {
                f3469a = new ArrayList<>();
            }
            f3469a.add(Integer.valueOf(i));
        }

        /* renamed from: a */
        public static void m2007a() {
            if (f3469a == null) {
                f3469a = new ArrayList<>();
            } else {
                f3469a.clear();
            }
        }

        /* renamed from: a */
        public static void m2009a(StringBuilder sb) {
            if (sb != null && f3469a != null && f3469a.size() != 0) {
                sb.append("Line count of the last frames:").append("\r\n");
                sb.append(f3469a.toString().replace("[", "").replace("]", "")).append("\r\n");
            }
        }
    }

    public static void init() {
        LogUtils.init();
    }

    public static void appendCoordinatesLog(StringBuilder sb, ArrayList<ArrayList<JumioRect>> arrayList) {
        if (arrayList != null) {
            sb.append("Coordinates").append("\r\n");
            sb.append("left,top,width,height").append("\r\n");
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append("Line ").append(i + 1).append("\r\n");
                ArrayList arrayList2 = (ArrayList) arrayList.get(i);
                if (arrayList2 != null) {
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        JumioRect jumioRect = (JumioRect) arrayList2.get(i2);
                        sb.append(jumioRect.left).append(",").append(jumioRect.top).append(",").append(jumioRect.width()).append(",").append(jumioRect.height()).append("\r\n");
                    }
                }
            }
        }
    }

    public static void logInfoInSubfolder(String str, String str2, String str3) {
        File subFolder = getSubFolder(str2);
        if (subFolder != null) {
            if (str3 == null) {
                str3 = "OCRImageData.txt";
            }
            Log.m1918i(str, subFolder, str3);
        }
    }

    public static void dumpImageInSubfolder(Bitmap bitmap, String str, CompressFormat compressFormat, int i, String str2) {
        File subFolder = getSubFolder(str);
        String str3 = (str2 != null ? str2 + "_" + FILE_UPLOAD_IMAGE : FILE_UPLOAD_IMAGE) + "." + compressFormat.name().toLowerCase();
        if (subFolder != null) {
            Log.image(bitmap, subFolder, str3, compressFormat, i);
        }
    }

    public static void dumpDataInSubfolder(byte[] bArr, String str, String str2) {
        File subFolder = getSubFolder(str);
        if (subFolder != null) {
            Log.data(bArr, subFolder, str2);
        }
    }

    public static void dumpImageInSubfolder(Bitmap bitmap, String str, CompressFormat compressFormat, int i) {
        dumpImageInSubfolder(bitmap, str, compressFormat, i, null);
    }

    public static void dumpPictureWithCoordinates(ArrayList<ArrayList<JumioRect>> arrayList, Bitmap bitmap, String str) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0.0f);
        paint.setColor(-16724941);
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList arrayList2 = (ArrayList) arrayList.get(i);
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                JumioRect jumioRect = (JumioRect) arrayList2.get(i2);
                canvas.drawRect(new Rect(jumioRect.left, jumioRect.top, jumioRect.right, jumioRect.bottom), paint);
            }
        }
        File subFolder = getSubFolder(str);
        if (subFolder != null) {
            Log.image(bitmap, subFolder, FILE_UPLOAD_IMAGE_WITH_COORDS, CompressFormat.PNG, 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c A[SYNTHETIC, Splitter:B:16:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0031 A[Catch:{ Exception -> 0x0038 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r2 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0038 }
            java.io.File r1 = getSubFolder(r6)     // Catch:{ Exception -> 0x0038 }
            r0.<init>(r1, r7)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x0038 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x004b }
            r3.<init>(r5)     // Catch:{ all -> 0x004b }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x004e }
            r1.<init>(r0)     // Catch:{ all -> 0x004e }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0028 }
        L_0x001c:
            int r2 = r3.read(r0)     // Catch:{ all -> 0x0028 }
            r4 = -1
            if (r2 == r4) goto L_0x003d
            r4 = 0
            r1.write(r0, r4, r2)     // Catch:{ all -> 0x0028 }
            goto L_0x001c
        L_0x0028:
            r0 = move-exception
            r2 = r3
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ Exception -> 0x0038 }
        L_0x002f:
            if (r1 == 0) goto L_0x0037
            r1.flush()     // Catch:{ Exception -> 0x0038 }
            r1.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0037:
            throw r0     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)
        L_0x003c:
            return
        L_0x003d:
            if (r3 == 0) goto L_0x0042
            r3.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0042:
            if (r1 == 0) goto L_0x003c
            r1.flush()     // Catch:{ Exception -> 0x0038 }
            r1.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x003c
        L_0x004b:
            r0 = move-exception
            r1 = r2
            goto L_0x002a
        L_0x004e:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.p311nv.utils.NetverifyLogUtils.copyFile(java.lang.String, java.lang.String, java.lang.String):void");
    }
}
