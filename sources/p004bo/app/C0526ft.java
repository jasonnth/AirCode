package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import p004bo.app.C0519fs.C0522a;
import p004bo.app.C0597hm.C0598a;

/* renamed from: bo.app.ft */
public class C0526ft implements C0516fp {

    /* renamed from: a */
    public static final CompressFormat f551a = CompressFormat.PNG;

    /* renamed from: b */
    protected C0519fs f552b;

    /* renamed from: c */
    protected final C0530fw f553c;

    /* renamed from: d */
    protected int f554d = 32768;

    /* renamed from: e */
    protected CompressFormat f555e = f551a;

    /* renamed from: f */
    protected int f556f = 100;

    /* renamed from: g */
    private File f557g;

    public C0526ft(File file, File file2, C0530fw fwVar, long j, int i) {
        long j2;
        int i2;
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (j < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (i < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (fwVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            if (j == 0) {
                j2 = Long.MAX_VALUE;
            } else {
                j2 = j;
            }
            if (i == 0) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = i;
            }
            this.f557g = file2;
            this.f553c = fwVar;
            m749a(file, file2, j2, i2);
        }
    }

    /* renamed from: a */
    private void m749a(File file, File file2, long j, int i) {
        try {
            this.f552b = C0519fs.m701a(file, 1, 1, j, i);
        } catch (IOException e) {
            C0599hn.m1061a((Throwable) e);
            if (file2 != null) {
                m749a(file2, null, j, i);
            }
            if (this.f552b == null) {
                throw e;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File mo7073a(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            bo.app.fs r1 = r3.f552b     // Catch:{ IOException -> 0x0019, all -> 0x0024 }
            java.lang.String r2 = r3.m750b(r4)     // Catch:{ IOException -> 0x0019, all -> 0x0024 }
            bo.app.fs$c r2 = r1.mo7077a(r2)     // Catch:{ IOException -> 0x0019, all -> 0x0024 }
            if (r2 != 0) goto L_0x0013
        L_0x000d:
            if (r2 == 0) goto L_0x0012
            r2.close()
        L_0x0012:
            return r0
        L_0x0013:
            r1 = 0
            java.io.File r0 = r2.mo7095a(r1)     // Catch:{ IOException -> 0x002f }
            goto L_0x000d
        L_0x0019:
            r1 = move-exception
            r2 = r0
        L_0x001b:
            p004bo.app.C0599hn.m1061a(r1)     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x0012
            r2.close()
            goto L_0x0012
        L_0x0024:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()
        L_0x002c:
            throw r0
        L_0x002d:
            r0 = move-exception
            goto L_0x0027
        L_0x002f:
            r1 = move-exception
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0526ft.mo7073a(java.lang.String):java.io.File");
    }

    /* renamed from: a */
    public boolean mo7075a(String str, InputStream inputStream, C0598a aVar) {
        boolean z = false;
        C0522a b = this.f552b.mo7079b(m750b(str));
        if (b != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.mo7085a(0), this.f554d);
            try {
                z = C0597hm.m1057a(inputStream, bufferedOutputStream, aVar, this.f554d);
                C0597hm.m1054a((Closeable) bufferedOutputStream);
                if (z) {
                    b.mo7086a();
                } else {
                    b.mo7087b();
                }
            } catch (Throwable th) {
                C0597hm.m1054a((Closeable) bufferedOutputStream);
                b.mo7087b();
                throw th;
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean mo7074a(String str, Bitmap bitmap) {
        boolean z = false;
        C0522a b = this.f552b.mo7079b(m750b(str));
        if (b != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(b.mo7085a(0), this.f554d);
            try {
                z = bitmap.compress(this.f555e, this.f556f, bufferedOutputStream);
                if (z) {
                    b.mo7086a();
                } else {
                    b.mo7087b();
                }
            } finally {
                C0597hm.m1054a((Closeable) bufferedOutputStream);
            }
        }
        return z;
    }

    /* renamed from: b */
    private String m750b(String str) {
        return this.f553c.mo7100a(str);
    }
}
