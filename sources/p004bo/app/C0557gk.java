package p004bo.app;

import android.graphics.Bitmap;
import android.os.Handler;

/* renamed from: bo.app.gk */
final class C0557gk implements Runnable {

    /* renamed from: a */
    private final C0549gh f722a;

    /* renamed from: b */
    private final Bitmap f723b;

    /* renamed from: c */
    private final C0551gi f724c;

    /* renamed from: d */
    private final Handler f725d;

    public C0557gk(C0549gh ghVar, Bitmap bitmap, C0551gi giVar, Handler handler) {
        this.f722a = ghVar;
        this.f723b = bitmap;
        this.f724c = giVar;
        this.f725d = handler;
    }

    public void run() {
        C0599hn.m1060a("PostProcess image before displaying [%s]", this.f724c.f690b);
        C0552gj.m921a(new C0539gd(this.f724c.f693e.mo7126p().mo7275a(this.f723b), this.f724c, this.f722a, C0564gq.MEMORY_CACHE), this.f724c.f693e.mo7129s(), this.f725d, this.f722a);
    }
}
