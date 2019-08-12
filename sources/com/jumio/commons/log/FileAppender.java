package com.jumio.commons.log;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAppender {
    public static final String NEW_LINE = "\r\n";
    public static final String TAG = "JumioMobileSDK";
    private StringBuilder fileLog = null;
    private Context mContext;

    public FileAppender(Context context) {
        this.mContext = context;
    }

    public void destroy() {
        clearFileLog();
    }

    public void logString(String string, File folder, String fileName) {
        if (this.fileLog == null) {
            this.fileLog = new StringBuilder();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        if (string.equals("\r\n")) {
            this.fileLog.append(string);
        } else {
            this.fileLog.append(dateFormat.format(new Date())).append("\r\n").append(string).append("\r\n");
        }
        dumpRawBuffer(this.fileLog.toString().getBytes(), folder, fileName, true);
        clearFileLog();
    }

    public void dumpStringToFile(String string, File folder, String file) {
        dumpRawBuffer(string.getBytes(), folder, file, true);
    }

    private void clearFileLog() {
        if (this.fileLog != null) {
            this.fileLog.delete(0, this.fileLog.length());
        }
    }

    public void dumpBitmap(Bitmap bitmap, File folder, String file, CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, bos);
        dumpRawBuffer(bos.toByteArray(), folder, file, false);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dumpRawBuffer(byte[] r5, java.io.File r6, java.lang.String r7, boolean r8) {
        /*
            r4 = this;
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r1.<init>(r6, r7)     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            boolean r3 = r1.exists()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            if (r3 != 0) goto L_0x000e
            r1.createNewFile()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x000e:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r2.<init>(r1, r8)     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r2.write(r5)     // Catch:{ all -> 0x001a }
            r2.close()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x0019:
            return
        L_0x001a:
            r3 = move-exception
            r2.close()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            throw r3     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x001f:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)
            goto L_0x0019
        L_0x0024:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.log.FileAppender.dumpRawBuffer(byte[], java.io.File, java.lang.String, boolean):void");
    }
}
