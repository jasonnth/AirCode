package p004bo.app;

import android.content.res.Resources;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/* renamed from: bo.app.ge */
public final class C0540ge {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final int f586a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final int f587b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final int f588c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Drawable f589d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Drawable f590e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public final Drawable f591f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final boolean f592g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public final boolean f593h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final boolean f594i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public final C0562go f595j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public final Options f596k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public final int f597l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public final boolean f598m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public final Object f599n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public final C0594hk f600o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public final C0594hk f601p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public final C0582ha f602q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public final Handler f603r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public final boolean f604s;

    /* renamed from: bo.app.ge$a */
    public static class C0542a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f605a = 0;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public int f606b = 0;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f607c = 0;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public Drawable f608d = null;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public Drawable f609e = null;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public Drawable f610f = null;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public boolean f611g = false;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public boolean f612h = false;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public boolean f613i = false;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public C0562go f614j = C0562go.IN_SAMPLE_POWER_OF_2;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public Options f615k = new Options();
        /* access modifiers changed from: private */

        /* renamed from: l */
        public int f616l = 0;
        /* access modifiers changed from: private */

        /* renamed from: m */
        public boolean f617m = false;
        /* access modifiers changed from: private */

        /* renamed from: n */
        public Object f618n = null;
        /* access modifiers changed from: private */

        /* renamed from: o */
        public C0594hk f619o = null;
        /* access modifiers changed from: private */

        /* renamed from: p */
        public C0594hk f620p = null;
        /* access modifiers changed from: private */

        /* renamed from: q */
        public C0582ha f621q = C0537gc.m789c();
        /* access modifiers changed from: private */

        /* renamed from: r */
        public Handler f622r = null;
        /* access modifiers changed from: private */

        /* renamed from: s */
        public boolean f623s = false;

        /* renamed from: a */
        public C0542a mo7132a(boolean z) {
            this.f612h = z;
            return this;
        }

        @Deprecated
        /* renamed from: b */
        public C0542a mo7134b(boolean z) {
            return mo7135c(z);
        }

        /* renamed from: c */
        public C0542a mo7135c(boolean z) {
            this.f613i = z;
            return this;
        }

        /* renamed from: a */
        public C0542a mo7131a(C0562go goVar) {
            this.f614j = goVar;
            return this;
        }

        /* renamed from: a */
        public C0542a mo7130a(C0540ge geVar) {
            this.f605a = geVar.f586a;
            this.f606b = geVar.f587b;
            this.f607c = geVar.f588c;
            this.f608d = geVar.f589d;
            this.f609e = geVar.f590e;
            this.f610f = geVar.f591f;
            this.f611g = geVar.f592g;
            this.f612h = geVar.f593h;
            this.f613i = geVar.f594i;
            this.f614j = geVar.f595j;
            this.f615k = geVar.f596k;
            this.f616l = geVar.f597l;
            this.f617m = geVar.f598m;
            this.f618n = geVar.f599n;
            this.f619o = geVar.f600o;
            this.f620p = geVar.f601p;
            this.f621q = geVar.f602q;
            this.f622r = geVar.f603r;
            this.f623s = geVar.f604s;
            return this;
        }

        /* renamed from: a */
        public C0540ge mo7133a() {
            return new C0540ge(this);
        }
    }

    private C0540ge(C0542a aVar) {
        this.f586a = aVar.f605a;
        this.f587b = aVar.f606b;
        this.f588c = aVar.f607c;
        this.f589d = aVar.f608d;
        this.f590e = aVar.f609e;
        this.f591f = aVar.f610f;
        this.f592g = aVar.f611g;
        this.f593h = aVar.f612h;
        this.f594i = aVar.f613i;
        this.f595j = aVar.f614j;
        this.f596k = aVar.f615k;
        this.f597l = aVar.f616l;
        this.f598m = aVar.f617m;
        this.f599n = aVar.f618n;
        this.f600o = aVar.f619o;
        this.f601p = aVar.f620p;
        this.f602q = aVar.f621q;
        this.f603r = aVar.f622r;
        this.f604s = aVar.f623s;
    }

    /* renamed from: a */
    public boolean mo7109a() {
        return (this.f589d == null && this.f586a == 0) ? false : true;
    }

    /* renamed from: b */
    public boolean mo7111b() {
        return (this.f590e == null && this.f587b == 0) ? false : true;
    }

    /* renamed from: c */
    public boolean mo7113c() {
        return (this.f591f == null && this.f588c == 0) ? false : true;
    }

    /* renamed from: d */
    public boolean mo7114d() {
        return this.f600o != null;
    }

    /* renamed from: e */
    public boolean mo7115e() {
        return this.f601p != null;
    }

    /* renamed from: f */
    public boolean mo7116f() {
        return this.f597l > 0;
    }

    /* renamed from: a */
    public Drawable mo7108a(Resources resources) {
        return this.f586a != 0 ? resources.getDrawable(this.f586a) : this.f589d;
    }

    /* renamed from: b */
    public Drawable mo7110b(Resources resources) {
        return this.f587b != 0 ? resources.getDrawable(this.f587b) : this.f590e;
    }

    /* renamed from: c */
    public Drawable mo7112c(Resources resources) {
        return this.f588c != 0 ? resources.getDrawable(this.f588c) : this.f591f;
    }

    /* renamed from: g */
    public boolean mo7117g() {
        return this.f592g;
    }

    /* renamed from: h */
    public boolean mo7118h() {
        return this.f593h;
    }

    /* renamed from: i */
    public boolean mo7119i() {
        return this.f594i;
    }

    /* renamed from: j */
    public C0562go mo7120j() {
        return this.f595j;
    }

    /* renamed from: k */
    public Options mo7121k() {
        return this.f596k;
    }

    /* renamed from: l */
    public int mo7122l() {
        return this.f597l;
    }

    /* renamed from: m */
    public boolean mo7123m() {
        return this.f598m;
    }

    /* renamed from: n */
    public Object mo7124n() {
        return this.f599n;
    }

    /* renamed from: o */
    public C0594hk mo7125o() {
        return this.f600o;
    }

    /* renamed from: p */
    public C0594hk mo7126p() {
        return this.f601p;
    }

    /* renamed from: q */
    public C0582ha mo7127q() {
        return this.f602q;
    }

    /* renamed from: r */
    public Handler mo7128r() {
        return this.f603r;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: s */
    public boolean mo7129s() {
        return this.f604s;
    }

    /* renamed from: t */
    public static C0540ge m812t() {
        return new C0542a().mo7133a();
    }
}
