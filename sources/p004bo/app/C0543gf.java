package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

/* renamed from: bo.app.gf */
public class C0543gf {

    /* renamed from: a */
    public static final String f624a = C0543gf.class.getSimpleName();

    /* renamed from: e */
    private static volatile C0543gf f625e;

    /* renamed from: b */
    private C0544gg f626b;

    /* renamed from: c */
    private C0549gh f627c;

    /* renamed from: d */
    private C0591hh f628d = new C0593hj();

    /* renamed from: a */
    public static C0543gf m861a() {
        if (f625e == null) {
            synchronized (C0543gf.class) {
                if (f625e == null) {
                    f625e = new C0543gf();
                }
            }
        }
        return f625e;
    }

    protected C0543gf() {
    }

    /* renamed from: a */
    public synchronized void mo7136a(C0544gg ggVar) {
        if (ggVar == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.f626b == null) {
            C0599hn.m1060a("Initialize ImageLoader with configuration", new Object[0]);
            this.f627c = new C0549gh(ggVar);
            this.f626b = ggVar;
        } else {
            C0599hn.m1064c("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    /* renamed from: a */
    public void mo7139a(String str, C0588he heVar, C0540ge geVar, C0591hh hhVar, C0592hi hiVar) {
        mo7138a(str, heVar, geVar, null, hhVar, hiVar);
    }

    /* renamed from: a */
    public void mo7138a(String str, C0588he heVar, C0540ge geVar, C0563gp gpVar, C0591hh hhVar, C0592hi hiVar) {
        C0591hh hhVar2;
        C0540ge geVar2;
        C0563gp gpVar2;
        m862b();
        if (heVar == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        if (hhVar == null) {
            hhVar2 = this.f628d;
        } else {
            hhVar2 = hhVar;
        }
        if (geVar == null) {
            geVar2 = this.f626b.f646r;
        } else {
            geVar2 = geVar;
        }
        if (TextUtils.isEmpty(str)) {
            this.f627c.mo7162b(heVar);
            hhVar2.mo7270a(str, heVar.mo7264d());
            if (geVar2.mo7111b()) {
                heVar.mo7261a(geVar2.mo7110b(this.f626b.f629a));
            } else {
                heVar.mo7261a((Drawable) null);
            }
            hhVar2.mo7271a(str, heVar.mo7264d(), (Bitmap) null);
            return;
        }
        if (gpVar == null) {
            gpVar2 = C0595hl.m1052a(heVar, this.f626b.mo7141a());
        } else {
            gpVar2 = gpVar;
        }
        String a = C0600ho.m1066a(str, gpVar2);
        this.f627c.mo7158a(heVar, a);
        hhVar2.mo7270a(str, heVar.mo7264d());
        Bitmap a2 = this.f626b.f642n.mo7101a(a);
        if (a2 == null || a2.isRecycled()) {
            if (geVar2.mo7109a()) {
                heVar.mo7261a(geVar2.mo7108a(this.f626b.f629a));
            } else if (geVar2.mo7117g()) {
                heVar.mo7261a((Drawable) null);
            }
            C0552gj gjVar = new C0552gj(this.f627c, new C0551gi(str, heVar, gpVar2, a, geVar2, hhVar2, hiVar, this.f627c.mo7155a(str)), m860a(geVar2));
            if (geVar2.mo7129s()) {
                gjVar.run();
            } else {
                this.f627c.mo7156a(gjVar);
            }
        } else {
            C0599hn.m1060a("Load image from memory cache [%s]", a);
            if (geVar2.mo7115e()) {
                C0557gk gkVar = new C0557gk(this.f627c, a2, new C0551gi(str, heVar, gpVar2, a, geVar2, hhVar2, hiVar, this.f627c.mo7155a(str)), m860a(geVar2));
                if (geVar2.mo7129s()) {
                    gkVar.run();
                } else {
                    this.f627c.mo7157a(gkVar);
                }
            } else {
                geVar2.mo7127q().mo7247a(a2, heVar, C0564gq.MEMORY_CACHE);
                hhVar2.mo7271a(str, heVar.mo7264d(), a2);
            }
        }
    }

    /* renamed from: a */
    public void mo7137a(String str, ImageView imageView, C0591hh hhVar) {
        mo7139a(str, new C0589hf(imageView), null, hhVar, null);
    }

    /* renamed from: b */
    private void m862b() {
        if (this.f626b == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    /* renamed from: a */
    public void mo7140a(boolean z) {
        this.f627c.mo7160a(z);
    }

    /* renamed from: a */
    private static Handler m860a(C0540ge geVar) {
        Handler r = geVar.mo7128r();
        if (geVar.mo7129s()) {
            return null;
        }
        if (r == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return r;
    }
}
