package p004bo.app;

import android.graphics.Bitmap;
import android.os.Handler;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import p004bo.app.C0540ge.C0542a;
import p004bo.app.C0559gm.C0560a;
import p004bo.app.C0586hd.C0587a;
import p004bo.app.C0597hm.C0598a;

/* renamed from: bo.app.gj */
final class C0552gj implements C0598a, Runnable {

    /* renamed from: a */
    final String f697a;

    /* renamed from: b */
    final C0588he f698b;

    /* renamed from: c */
    final C0540ge f699c;

    /* renamed from: d */
    final C0591hh f700d;

    /* renamed from: e */
    final C0592hi f701e;

    /* renamed from: f */
    private final C0549gh f702f;

    /* renamed from: g */
    private final C0551gi f703g;

    /* renamed from: h */
    private final Handler f704h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final C0544gg f705i;

    /* renamed from: j */
    private final C0586hd f706j;

    /* renamed from: k */
    private final C0586hd f707k;

    /* renamed from: l */
    private final C0586hd f708l;

    /* renamed from: m */
    private final C0579gy f709m;

    /* renamed from: n */
    private final String f710n;

    /* renamed from: o */
    private final C0563gp f711o;

    /* renamed from: p */
    private final boolean f712p;

    /* renamed from: q */
    private C0564gq f713q = C0564gq.NETWORK;

    /* renamed from: bo.app.gj$a */
    class C0556a extends Exception {
        C0556a() {
        }
    }

    public C0552gj(C0549gh ghVar, C0551gi giVar, Handler handler) {
        this.f702f = ghVar;
        this.f703g = giVar;
        this.f704h = handler;
        this.f705i = ghVar.f677a;
        this.f706j = this.f705i.f644p;
        this.f707k = this.f705i.f647s;
        this.f708l = this.f705i.f648t;
        this.f709m = this.f705i.f645q;
        this.f697a = giVar.f689a;
        this.f710n = giVar.f690b;
        this.f698b = giVar.f691c;
        this.f711o = giVar.f692d;
        this.f699c = giVar.f693e;
        this.f700d = giVar.f694f;
        this.f701e = giVar.f695g;
        this.f712p = this.f699c.mo7129s();
    }

    public void run() {
        if (!m922b() && !m924c()) {
            ReentrantLock reentrantLock = this.f703g.f696h;
            C0599hn.m1060a("Start display image task [%s]", this.f710n);
            if (reentrantLock.isLocked()) {
                C0599hn.m1060a("Image already is loading. Waiting... [%s]", this.f710n);
            }
            reentrantLock.lock();
            try {
                m931i();
                Bitmap a = this.f705i.f642n.mo7101a(this.f710n);
                if (a == null || a.isRecycled()) {
                    a = m926d();
                    if (a != null) {
                        m931i();
                        m937o();
                        if (this.f699c.mo7114d()) {
                            C0599hn.m1060a("PreProcess image before caching in memory [%s]", this.f710n);
                            a = this.f699c.mo7125o().mo7275a(a);
                            if (a == null) {
                                C0599hn.m1065d("Pre-processor returned null [%s]", this.f710n);
                            }
                        }
                        if (a != null && this.f699c.mo7118h()) {
                            C0599hn.m1060a("Cache image in memory [%s]", this.f710n);
                            this.f705i.f642n.mo7103a(this.f710n, a);
                        }
                    } else {
                        return;
                    }
                } else {
                    this.f713q = C0564gq.MEMORY_CACHE;
                    C0599hn.m1060a("...Get cached bitmap from memory after waiting. [%s]", this.f710n);
                }
                if (a != null && this.f699c.mo7115e()) {
                    C0599hn.m1060a("PostProcess image before displaying [%s]", this.f710n);
                    a = this.f699c.mo7126p().mo7275a(a);
                    if (a == null) {
                        C0599hn.m1065d("Post-processor returned null [%s]", this.f710n);
                    }
                }
                m931i();
                m937o();
                reentrantLock.unlock();
                m921a(new C0539gd(a, this.f703g, this.f702f, this.f713q), this.f712p, this.f704h, this.f702f);
            } catch (C0556a e) {
                m929g();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    /* renamed from: b */
    private boolean m922b() {
        AtomicBoolean a = this.f702f.mo7154a();
        if (a.get()) {
            synchronized (this.f702f.mo7161b()) {
                if (a.get()) {
                    C0599hn.m1060a("ImageLoader is paused. Waiting...  [%s]", this.f710n);
                    try {
                        this.f702f.mo7161b().wait();
                        C0599hn.m1060a(".. Resume loading [%s]", this.f710n);
                    } catch (InterruptedException e) {
                        C0599hn.m1065d("Task was interrupted [%s]", this.f710n);
                        return true;
                    }
                }
            }
        }
        return m932j();
    }

    /* renamed from: c */
    private boolean m924c() {
        if (!this.f699c.mo7116f()) {
            return false;
        }
        C0599hn.m1060a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.f699c.mo7122l()), this.f710n);
        try {
            Thread.sleep((long) this.f699c.mo7122l());
            return m932j();
        } catch (InterruptedException e) {
            C0599hn.m1065d("Task was interrupted [%s]", this.f710n);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        if (r0.getHeight() > 0) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ab, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ad, code lost:
        r6 = r0;
        r0 = null;
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ba, code lost:
        r6 = r0;
        r0 = null;
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c7, code lost:
        r6 = r0;
        r0 = null;
        r1 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa A[ExcHandler: a (r0v9 'e' bo.app.gj$a A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap m926d() {
        /*
            r7 = this;
            r1 = 0
            bo.app.gg r0 = r7.f705i     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            bo.app.fp r0 = r0.f643o     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            java.lang.String r2 = r7.f697a     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            java.io.File r0 = r0.mo7073a(r2)     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            if (r0 == 0) goto L_0x00db
            boolean r2 = r0.exists()     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            if (r2 == 0) goto L_0x00db
            long r2 = r0.length()     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x00db
            java.lang.String r2 = "Load image from disk cache [%s]"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            r4 = 0
            java.lang.String r5 = r7.f710n     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            r3[r4] = r5     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            p004bo.app.C0599hn.m1060a(r2, r3)     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            bo.app.gq r2 = p004bo.app.C0564gq.DISC_CACHE     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            r7.f713q = r2     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            r7.m931i()     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            bo.app.hd$a r2 = p004bo.app.C0586hd.C0587a.FILE     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            java.lang.String r0 = r2.mo7257b(r0)     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
            android.graphics.Bitmap r0 = r7.m918a(r0)     // Catch:{ IllegalStateException -> 0x00a2, a -> 0x00aa, IOException -> 0x00ac, OutOfMemoryError -> 0x00b9, Throwable -> 0x00c6 }
        L_0x0040:
            if (r0 == 0) goto L_0x004e
            int r2 = r0.getWidth()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r2 <= 0) goto L_0x004e
            int r2 = r0.getHeight()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r2 > 0) goto L_0x00a1
        L_0x004e:
            java.lang.String r2 = "Load image from network [%s]"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            r4 = 0
            java.lang.String r5 = r7.f710n     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            r3[r4] = r5     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            p004bo.app.C0599hn.m1060a(r2, r3)     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            bo.app.gq r2 = p004bo.app.C0564gq.NETWORK     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            r7.f713q = r2     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            java.lang.String r2 = r7.f697a     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            bo.app.ge r3 = r7.f699c     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            boolean r3 = r3.mo7119i()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r3 == 0) goto L_0x0086
            boolean r3 = r7.m927e()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r3 == 0) goto L_0x0086
            bo.app.gg r3 = r7.f705i     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            bo.app.fp r3 = r3.f643o     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            java.lang.String r4 = r7.f697a     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            java.io.File r3 = r3.mo7073a(r4)     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r3 == 0) goto L_0x0086
            bo.app.hd$a r2 = p004bo.app.C0586hd.C0587a.FILE     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            java.lang.String r2 = r2.mo7257b(r3)     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
        L_0x0086:
            r7.m931i()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            android.graphics.Bitmap r0 = r7.m918a(r2)     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r0 == 0) goto L_0x009b
            int r2 = r0.getWidth()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r2 <= 0) goto L_0x009b
            int r2 = r0.getHeight()     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            if (r2 > 0) goto L_0x00a1
        L_0x009b:
            bo.app.gm$a r2 = p004bo.app.C0559gm.C0560a.DECODING_ERROR     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
            r3 = 0
            r7.m920a(r2, r3)     // Catch:{ IllegalStateException -> 0x00d9, a -> 0x00aa, IOException -> 0x00d7, OutOfMemoryError -> 0x00d5, Throwable -> 0x00d3 }
        L_0x00a1:
            return r0
        L_0x00a2:
            r0 = move-exception
            r0 = r1
        L_0x00a4:
            bo.app.gm$a r2 = p004bo.app.C0559gm.C0560a.NETWORK_DENIED
            r7.m920a(r2, r1)
            goto L_0x00a1
        L_0x00aa:
            r0 = move-exception
            throw r0
        L_0x00ac:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x00b0:
            p004bo.app.C0599hn.m1061a(r1)
            bo.app.gm$a r2 = p004bo.app.C0559gm.C0560a.IO_ERROR
            r7.m920a(r2, r1)
            goto L_0x00a1
        L_0x00b9:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x00bd:
            p004bo.app.C0599hn.m1061a(r1)
            bo.app.gm$a r2 = p004bo.app.C0559gm.C0560a.OUT_OF_MEMORY
            r7.m920a(r2, r1)
            goto L_0x00a1
        L_0x00c6:
            r0 = move-exception
            r6 = r0
            r0 = r1
            r1 = r6
        L_0x00ca:
            p004bo.app.C0599hn.m1061a(r1)
            bo.app.gm$a r2 = p004bo.app.C0559gm.C0560a.UNKNOWN
            r7.m920a(r2, r1)
            goto L_0x00a1
        L_0x00d3:
            r1 = move-exception
            goto L_0x00ca
        L_0x00d5:
            r1 = move-exception
            goto L_0x00bd
        L_0x00d7:
            r1 = move-exception
            goto L_0x00b0
        L_0x00d9:
            r2 = move-exception
            goto L_0x00a4
        L_0x00db:
            r0 = r1
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0552gj.m926d():android.graphics.Bitmap");
    }

    /* renamed from: a */
    private Bitmap m918a(String str) {
        String str2 = str;
        return this.f709m.mo7229a(new C0580gz(this.f710n, str2, this.f697a, this.f711o, this.f698b.mo7263c(), m930h(), this.f699c));
    }

    /* renamed from: e */
    private boolean m927e() {
        C0599hn.m1060a("Cache image on disk [%s]", this.f710n);
        try {
            boolean f = m928f();
            if (!f) {
                return f;
            }
            int i = this.f705i.f632d;
            int i2 = this.f705i.f633e;
            if (i <= 0 && i2 <= 0) {
                return f;
            }
            C0599hn.m1060a("Resize image in disk cache [%s]", this.f710n);
            m923b(i, i2);
            return f;
        } catch (IOException e) {
            C0599hn.m1061a((Throwable) e);
            return false;
        }
    }

    /* renamed from: f */
    private boolean m928f() {
        boolean z = false;
        InputStream a = m930h().mo7152a(this.f697a, this.f699c.mo7124n());
        if (a == null) {
            C0599hn.m1065d("No stream for image [%s]", this.f710n);
        } else {
            try {
                z = this.f705i.f643o.mo7075a(this.f697a, a, this);
            } finally {
                C0597hm.m1054a((Closeable) a);
            }
        }
        return z;
    }

    /* renamed from: b */
    private boolean m923b(int i, int i2) {
        File a = this.f705i.f643o.mo7073a(this.f697a);
        if (a != null && a.exists()) {
            Bitmap a2 = this.f709m.mo7229a(new C0580gz(this.f710n, C0587a.FILE.mo7257b(a.getAbsolutePath()), this.f697a, new C0563gp(i, i2), C0566gs.FIT_INSIDE, m930h(), new C0542a().mo7130a(this.f699c).mo7131a(C0562go.IN_SAMPLE_INT).mo7133a()));
            if (!(a2 == null || this.f705i.f634f == null)) {
                C0599hn.m1060a("Process image before cache on disk [%s]", this.f710n);
                a2 = this.f705i.f634f.mo7275a(a2);
                if (a2 == null) {
                    C0599hn.m1065d("Bitmap processor for disk cache returned null [%s]", this.f710n);
                }
            }
            Bitmap bitmap = a2;
            if (bitmap != null) {
                boolean a3 = this.f705i.f643o.mo7074a(this.f697a, bitmap);
                bitmap.recycle();
                return a3;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo7167a(int i, int i2) {
        return this.f712p || m925c(i, i2);
    }

    /* renamed from: c */
    private boolean m925c(final int i, final int i2) {
        if (m938p() || m932j()) {
            return false;
        }
        if (this.f701e != null) {
            m921a(new Runnable() {
                public void run() {
                    C0552gj.this.f701e.mo7274a(C0552gj.this.f697a, C0552gj.this.f698b.mo7264d(), i, i2);
                }
            }, false, this.f704h, this.f702f);
        }
        return true;
    }

    /* renamed from: a */
    private void m920a(final C0560a aVar, final Throwable th) {
        if (!this.f712p && !m938p() && !m932j()) {
            m921a(new Runnable() {
                public void run() {
                    if (C0552gj.this.f699c.mo7113c()) {
                        C0552gj.this.f698b.mo7261a(C0552gj.this.f699c.mo7112c(C0552gj.this.f705i.f629a));
                    }
                    C0552gj.this.f700d.mo7272a(C0552gj.this.f697a, C0552gj.this.f698b.mo7264d(), new C0559gm(aVar, th));
                }
            }, false, this.f704h, this.f702f);
        }
    }

    /* renamed from: g */
    private void m929g() {
        if (!this.f712p && !m938p()) {
            m921a(new Runnable() {
                public void run() {
                    C0552gj.this.f700d.mo7273b(C0552gj.this.f697a, C0552gj.this.f698b.mo7264d());
                }
            }, false, this.f704h, this.f702f);
        }
    }

    /* renamed from: h */
    private C0586hd m930h() {
        if (this.f702f.mo7163c()) {
            return this.f707k;
        }
        if (this.f702f.mo7164d()) {
            return this.f708l;
        }
        return this.f706j;
    }

    /* renamed from: i */
    private void m931i() {
        m933k();
        m935m();
    }

    /* renamed from: j */
    private boolean m932j() {
        return m934l() || m936n();
    }

    /* renamed from: k */
    private void m933k() {
        if (m934l()) {
            throw new C0556a();
        }
    }

    /* renamed from: l */
    private boolean m934l() {
        if (!this.f698b.mo7265e()) {
            return false;
        }
        C0599hn.m1060a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f710n);
        return true;
    }

    /* renamed from: m */
    private void m935m() {
        if (m936n()) {
            throw new C0556a();
        }
    }

    /* renamed from: n */
    private boolean m936n() {
        if (!(!this.f710n.equals(this.f702f.mo7153a(this.f698b)))) {
            return false;
        }
        C0599hn.m1060a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f710n);
        return true;
    }

    /* renamed from: o */
    private void m937o() {
        if (m938p()) {
            throw new C0556a();
        }
    }

    /* renamed from: p */
    private boolean m938p() {
        if (!Thread.interrupted()) {
            return false;
        }
        C0599hn.m1060a("Task was interrupted [%s]", this.f710n);
        return true;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public String mo7166a() {
        return this.f697a;
    }

    /* renamed from: a */
    static void m921a(Runnable runnable, boolean z, Handler handler, C0549gh ghVar) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            ghVar.mo7159a(runnable);
        } else {
            handler.post(runnable);
        }
    }
}
