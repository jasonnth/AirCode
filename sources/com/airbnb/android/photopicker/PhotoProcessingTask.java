package com.airbnb.android.photopicker;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoProcessingTask extends AsyncTask<Uri, Void, File> {
    private static final int COMPRESS_QUALITY = 80;
    private static final String TAG = PhotoProcessingTask.class.getCanonicalName();
    private Context context;
    private int desiredHeight;
    private int desiredWidth;
    private File outputFile;

    public PhotoProcessingTask(Context context2, File outputFile2, int desiredWidth2, int desiredHeight2) {
        this.context = context2;
        this.outputFile = outputFile2;
        this.desiredWidth = desiredWidth2;
        this.desiredHeight = desiredHeight2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a0 A[SYNTHETIC, Splitter:B:26:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f1 A[SYNTHETIC, Splitter:B:53:0x00f1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File doInBackground(android.net.Uri... r20) {
        /*
            r19 = this;
            r17 = 0
            r6 = r20[r17]
            r11 = 0
            java.io.File r5 = new java.io.File     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            java.lang.String r17 = r6.getPath()     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r0 = r17
            r5.<init>(r0)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r16 = 0
            boolean r17 = r5.exists()     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            if (r17 != 0) goto L_0x0028
            r0 = r19
            android.content.Context r0 = r0.context     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = r0
            r0 = r19
            r1 = r17
            java.io.File r16 = r0.getFileFromUri(r1, r6)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r5 = r16
        L_0x0028:
            java.lang.String r4 = r5.getAbsolutePath()     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            android.graphics.BitmapFactory$Options r8 = new android.graphics.BitmapFactory$Options     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r8.<init>()     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = 1
            r0 = r17
            r8.inJustDecodeBounds = r0     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            android.graphics.BitmapFactory.decodeFile(r4, r8)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            int r9 = com.airbnb.android.photopicker.ImageUtils.getExifOrientation(r4)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            int r14 = r8.outWidth     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            int r13 = r8.outHeight     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = 90
            r0 = r17
            if (r9 == r0) goto L_0x004e
            r17 = 180(0xb4, float:2.52E-43)
            r0 = r17
            if (r9 != r0) goto L_0x0051
        L_0x004e:
            r15 = r13
            r13 = r14
            r14 = r15
        L_0x0051:
            r0 = r19
            int r0 = r0.desiredWidth     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = r0
            if (r17 <= 0) goto L_0x00a6
            r0 = r19
            int r0 = r0.desiredHeight     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = r0
            if (r17 <= 0) goto L_0x00a6
            r0 = r19
            int r0 = r0.desiredWidth     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = r0
            r0 = r19
            int r0 = r0.desiredHeight     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r18 = r0
            r0 = r17
            r1 = r18
            int r17 = com.airbnb.android.photopicker.ImageUtils.getInSampleSize(r14, r13, r0, r1)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
        L_0x0075:
            r0 = r17
            r8.inSampleSize = r0     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = 0
            r0 = r17
            r8.inJustDecodeBounds = r0     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeFile(r4, r8)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            if (r2 != 0) goto L_0x00a9
            java.io.IOException r17 = new java.io.IOException     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            java.lang.String r18 = "could not decode input file to a bitmap"
            r17.<init>(r18)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            throw r17     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
        L_0x008e:
            r17 = move-exception
        L_0x008f:
            r3 = r17
        L_0x0091:
            java.lang.String r7 = r3.getLocalizedMessage()     // Catch:{ all -> 0x00ee }
            java.lang.String r17 = TAG     // Catch:{ all -> 0x00ee }
            if (r7 == 0) goto L_0x00e5
        L_0x0099:
            r0 = r17
            android.util.Log.d(r0, r7)     // Catch:{ all -> 0x00ee }
            if (r11 == 0) goto L_0x00a3
            r11.close()     // Catch:{ IOException -> 0x00e9 }
        L_0x00a3:
            r17 = 0
        L_0x00a5:
            return r17
        L_0x00a6:
            r17 = 1
            goto L_0x0075
        L_0x00a9:
            r10 = r2
            if (r9 == 0) goto L_0x00b0
            android.graphics.Bitmap r10 = com.airbnb.android.photopicker.ImageUtils.rotateIfNeeded(r2, r9)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
        L_0x00b0:
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r0 = r19
            java.io.File r0 = r0.outputFile     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            r17 = r0
            r0 = r17
            r12.<init>(r0)     // Catch:{ OutOfMemoryError -> 0x008e, IOException -> 0x00fa }
            android.graphics.Bitmap$CompressFormat r17 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ OutOfMemoryError -> 0x0101, IOException -> 0x0104, all -> 0x00fe }
            r18 = 80
            r0 = r17
            r1 = r18
            r10.compress(r0, r1, r12)     // Catch:{ OutOfMemoryError -> 0x0101, IOException -> 0x0104, all -> 0x00fe }
            if (r16 == 0) goto L_0x00d3
            boolean r17 = r16.exists()     // Catch:{ OutOfMemoryError -> 0x0101, IOException -> 0x0104, all -> 0x00fe }
            if (r17 == 0) goto L_0x00d3
            r16.delete()     // Catch:{ OutOfMemoryError -> 0x0101, IOException -> 0x0104, all -> 0x00fe }
        L_0x00d3:
            r0 = r19
            java.io.File r0 = r0.outputFile     // Catch:{ OutOfMemoryError -> 0x0101, IOException -> 0x0104, all -> 0x00fe }
            r17 = r0
            if (r12 == 0) goto L_0x00de
            r12.close()     // Catch:{ IOException -> 0x00e0 }
        L_0x00de:
            r11 = r12
            goto L_0x00a5
        L_0x00e0:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00de
        L_0x00e5:
            java.lang.String r7 = "unable to process file"
            goto L_0x0099
        L_0x00e9:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00a3
        L_0x00ee:
            r17 = move-exception
        L_0x00ef:
            if (r11 == 0) goto L_0x00f4
            r11.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x00f4:
            throw r17
        L_0x00f5:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00f4
        L_0x00fa:
            r17 = move-exception
        L_0x00fb:
            r3 = r17
            goto L_0x0091
        L_0x00fe:
            r17 = move-exception
            r11 = r12
            goto L_0x00ef
        L_0x0101:
            r17 = move-exception
            r11 = r12
            goto L_0x008f
        L_0x0104:
            r17 = move-exception
            r11 = r12
            goto L_0x00fb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.photopicker.PhotoProcessingTask.doInBackground(android.net.Uri[]):java.io.File");
    }

    private File getFileFromUri(Context context2, Uri imageUri) throws IOException {
        File imageFile = FileUtils.createTemporaryFile(context2);
        FileOutputStream output = new FileOutputStream(imageFile);
        byte[] buffer = new byte[4096];
        InputStream inputStream = context2.getContentResolver().openInputStream(imageUri);
        if (inputStream == null) {
            throw new IOException("unable to open input stream");
        }
        while (true) {
            int len = inputStream.read(buffer);
            if (len != -1) {
                output.write(buffer, 0, len);
            } else {
                output.flush();
                output.close();
                return imageFile;
            }
        }
    }
}
