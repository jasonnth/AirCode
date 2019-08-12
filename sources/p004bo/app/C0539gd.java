package p004bo.app;

import android.graphics.Bitmap;

/* renamed from: bo.app.gd */
final class C0539gd implements Runnable {

    /* renamed from: a */
    private final Bitmap f578a;

    /* renamed from: b */
    private final String f579b;

    /* renamed from: c */
    private final C0588he f580c;

    /* renamed from: d */
    private final String f581d;

    /* renamed from: e */
    private final C0582ha f582e;

    /* renamed from: f */
    private final C0591hh f583f;

    /* renamed from: g */
    private final C0549gh f584g;

    /* renamed from: h */
    private final C0564gq f585h;

    public C0539gd(Bitmap bitmap, C0551gi giVar, C0549gh ghVar, C0564gq gqVar) {
        this.f578a = bitmap;
        this.f579b = giVar.f689a;
        this.f580c = giVar.f691c;
        this.f581d = giVar.f690b;
        this.f582e = giVar.f693e.mo7127q();
        this.f583f = giVar.f694f;
        this.f584g = ghVar;
        this.f585h = gqVar;
    }

    public void run() {
        if (this.f580c.mo7265e()) {
            C0599hn.m1060a("ImageAware was collected by GC. Task is cancelled. [%s]", this.f581d);
            this.f583f.mo7273b(this.f579b, this.f580c.mo7264d());
        } else if (m792a()) {
            C0599hn.m1060a("ImageAware is reused for another image. Task is cancelled. [%s]", this.f581d);
            this.f583f.mo7273b(this.f579b, this.f580c.mo7264d());
        } else {
            C0599hn.m1060a("Display image in ImageAware (loaded from %1$s) [%2$s]", this.f585h, this.f581d);
            this.f582e.mo7247a(this.f578a, this.f580c, this.f585h);
            this.f584g.mo7162b(this.f580c);
            this.f583f.mo7271a(this.f579b, this.f580c.mo7264d(), this.f578a);
        }
    }

    /* renamed from: a */
    private boolean m792a() {
        return !this.f581d.equals(this.f584g.mo7153a(this.f580c));
    }
}
