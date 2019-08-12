package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import p005cn.jpush.android.JPushConstants;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.d */
public final class C4679d {

    /* renamed from: a */
    private boolean f3925a = false;

    /* renamed from: b */
    private boolean f3926b = false;

    /* renamed from: c */
    private File f3927c;

    public C4679d() {
        String externalStorageState = Environment.getExternalStorageState();
        char c = 65535;
        switch (externalStorageState.hashCode()) {
            case 1242932856:
                if (externalStorageState.equals("mounted")) {
                    c = 0;
                    break;
                }
                break;
            case 1299749220:
                if (externalStorageState.equals("mounted_ro")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.f3926b = true;
                this.f3925a = true;
                break;
            case 1:
                this.f3925a = true;
                this.f3926b = false;
                break;
            default:
                this.f3926b = false;
                this.f3925a = false;
                break;
        }
        this.f3927c = Environment.getExternalStorageDirectory();
    }

    /* renamed from: a */
    public final void mo45414a(String str) {
        this.f3927c = new File(str);
    }

    /* renamed from: a */
    public final void mo45415a(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Throwable th;
        if (this.f3925a && this.f3926b) {
            FileOutputStream fileOutputStream2 = null;
            try {
                if (this.f3927c.mkdirs() || this.f3927c.isDirectory()) {
                    fileOutputStream = new FileOutputStream(new File(this.f3927c, str));
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream2 = fileOutputStream;
                    } catch (Throwable th2) {
                        th = th2;
                        C4674af.m2376a(fileOutputStream);
                        throw th;
                    }
                }
                C4674af.m2376a(fileOutputStream2);
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream = null;
                th = th4;
                C4674af.m2376a(fileOutputStream);
                throw th;
            }
        }
    }

    /* renamed from: b */
    public final String mo45416b(String str) {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        if (this.f3926b) {
            try {
                fileInputStream = new FileInputStream(new File(this.f3927c, str));
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    for (int read = fileInputStream.read(bArr, 0, 1024); read != -1; read = fileInputStream.read(bArr, 0, 1024)) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    C4674af.m2376a(fileInputStream);
                } catch (IOException e) {
                    String str2 = "";
                    C4674af.m2376a(fileInputStream);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    C4674af.m2376a(fileInputStream);
                    throw th;
                }
            } catch (IOException e2) {
                fileInputStream = null;
                String str22 = "";
                C4674af.m2376a(fileInputStream);
                return str22;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                C4674af.m2376a(fileInputStream);
                throw th;
            }
        }
        return new String(bArr, JPushConstants.ENCODING_UTF_8);
    }
}
