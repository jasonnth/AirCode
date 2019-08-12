package com.jumio.commons.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {
    public static byte[] readFile(String file) throws IOException {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        try {
            long longlength = f.length();
            int length = (int) longlength;
            if (((long) length) != longlength) {
                throw new IOException("File size >= 2 GB");
            }
            byte[] data = new byte[length];
            f.readFully(data);
            return data;
        } finally {
            f.close();
        }
    }

    public static byte[] mergeBitmaps(String file1, String file2, CompressFormat format, int quality) {
        Bitmap b1 = BitmapFactory.decodeFile(file1);
        Bitmap b2 = BitmapFactory.decodeFile(file2);
        int width = b1.getWidth() + b2.getWidth();
        int height = b1.getHeight();
        if (b2.getHeight() > height) {
            height = b2.getHeight();
        }
        Bitmap comboBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas comboImage = new Canvas(comboBitmap);
        comboImage.drawColor(-1);
        comboImage.drawBitmap(b1, 0.0f, 0.0f, null);
        comboImage.drawBitmap(b2, (float) b1.getWidth(), 0.0f, null);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        comboBitmap.compress(format, quality, stream);
        return stream.toByteArray();
    }

    public static boolean deleteFile(String filename) {
        File f = new File(filename);
        if (f.exists()) {
            return f.delete();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean saveToFile(byte[] r5, java.io.File r6) {
        /*
            r1 = 0
            r3 = 1
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0012, all -> 0x001a }
            r4 = 0
            r2.<init>(r6, r4)     // Catch:{ IOException -> 0x0012, all -> 0x001a }
            r2.write(r5)     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            if (r2 == 0) goto L_0x0027
            com.jumio.commons.utils.IOUtils.closeQuietly(r2)
            r1 = r2
        L_0x0011:
            return r3
        L_0x0012:
            r0 = move-exception
        L_0x0013:
            r3 = 0
            if (r1 == 0) goto L_0x0011
            com.jumio.commons.utils.IOUtils.closeQuietly(r1)
            goto L_0x0011
        L_0x001a:
            r4 = move-exception
        L_0x001b:
            if (r1 == 0) goto L_0x0020
            com.jumio.commons.utils.IOUtils.closeQuietly(r1)
        L_0x0020:
            throw r4
        L_0x0021:
            r4 = move-exception
            r1 = r2
            goto L_0x001b
        L_0x0024:
            r0 = move-exception
            r1 = r2
            goto L_0x0013
        L_0x0027:
            r1 = r2
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.utils.FileUtil.saveToFile(byte[], java.io.File):boolean");
    }
}
