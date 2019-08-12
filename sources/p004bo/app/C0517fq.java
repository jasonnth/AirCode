package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;

/* renamed from: bo.app.fq */
public abstract class C0517fq implements C0516fp {

    /* renamed from: a */
    public static final CompressFormat f507a = CompressFormat.PNG;

    /* renamed from: b */
    protected final File f508b;

    /* renamed from: c */
    protected final File f509c;

    /* renamed from: d */
    protected final C0530fw f510d;

    /* renamed from: e */
    protected int f511e = 32768;

    /* renamed from: f */
    protected CompressFormat f512f = f507a;

    /* renamed from: g */
    protected int f513g = 100;

    public C0517fq(File file, File file2, C0530fw fwVar) {
        if (file == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (fwVar == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.f508b = file;
            this.f509c = file2;
            this.f510d = fwVar;
        }
    }

    /* renamed from: a */
    public File mo7073a(String str) {
        return mo7076b(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo7075a(java.lang.String r7, java.io.InputStream r8, p004bo.app.C0597hm.C0598a r9) {
        /*
            r6 = this;
            r2 = 0
            java.io.File r3 = r6.mo7076b(r7)
            java.io.File r4 = new java.io.File
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.getAbsolutePath()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ".tmp"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x004b }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x004b }
            r0.<init>(r4)     // Catch:{ all -> 0x004b }
            int r1 = r6.f511e     // Catch:{ all -> 0x004b }
            r5.<init>(r0, r1)     // Catch:{ all -> 0x004b }
            int r0 = r6.f511e     // Catch:{ all -> 0x0046 }
            boolean r1 = p004bo.app.C0597hm.m1057a(r8, r5, r9, r0)     // Catch:{ all -> 0x0046 }
            p004bo.app.C0597hm.m1054a(r5)     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0040
            boolean r0 = r4.renameTo(r3)
            if (r0 != 0) goto L_0x0040
            r1 = r2
        L_0x0040:
            if (r1 != 0) goto L_0x0045
            r4.delete()
        L_0x0045:
            return r1
        L_0x0046:
            r0 = move-exception
            p004bo.app.C0597hm.m1054a(r5)     // Catch:{ all -> 0x004b }
            throw r0     // Catch:{ all -> 0x004b }
        L_0x004b:
            r0 = move-exception
            r1 = r2
        L_0x004d:
            if (r1 == 0) goto L_0x0056
            boolean r3 = r4.renameTo(r3)
            if (r3 != 0) goto L_0x0056
            r1 = r2
        L_0x0056:
            if (r1 != 0) goto L_0x005b
            r4.delete()
        L_0x005b:
            throw r0
        L_0x005c:
            r0 = move-exception
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0517fq.mo7075a(java.lang.String, java.io.InputStream, bo.app.hm$a):boolean");
    }

    /* renamed from: a */
    public boolean mo7074a(String str, Bitmap bitmap) {
        File b = mo7076b(str);
        File file = new File(b.getAbsolutePath() + ".tmp");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), this.f511e);
        try {
            boolean compress = bitmap.compress(this.f512f, this.f513g, bufferedOutputStream);
            C0597hm.m1054a((Closeable) bufferedOutputStream);
            if (compress && !file.renameTo(b)) {
                compress = false;
            }
            if (!compress) {
                file.delete();
            }
            bitmap.recycle();
            return compress;
        } catch (Throwable th) {
            C0597hm.m1054a((Closeable) bufferedOutputStream);
            file.delete();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public File mo7076b(String str) {
        String a = this.f510d.mo7100a(str);
        File file = this.f508b;
        if (!this.f508b.exists() && !this.f508b.mkdirs() && this.f509c != null && (this.f509c.exists() || this.f509c.mkdirs())) {
            file = this.f509c;
        }
        return new File(file, a);
    }
}
