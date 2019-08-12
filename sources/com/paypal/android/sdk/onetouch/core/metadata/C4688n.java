package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.n */
public final class C4688n {
    /* renamed from: a */
    public static String m2473a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr, JPushConstants.ENCODING_UTF_8);
    }

    /* renamed from: a */
    public static void m2474a(File file, String str) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes(JPushConstants.ENCODING_UTF_8));
                C4674af.m2376a(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                C4674af.m2376a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            C4674af.m2376a(fileOutputStream);
            throw th;
        }
    }
}
