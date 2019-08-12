package p004bo.app;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.io.InputStream;
import java.util.concurrent.Executor;
import p004bo.app.C0586hd.C0587a;

/* renamed from: bo.app.gg */
public final class C0544gg {

    /* renamed from: a */
    final Resources f629a;

    /* renamed from: b */
    final int f630b;

    /* renamed from: c */
    final int f631c;

    /* renamed from: d */
    final int f632d;

    /* renamed from: e */
    final int f633e;

    /* renamed from: f */
    final C0594hk f634f;

    /* renamed from: g */
    final Executor f635g;

    /* renamed from: h */
    final Executor f636h;

    /* renamed from: i */
    final boolean f637i;

    /* renamed from: j */
    final boolean f638j;

    /* renamed from: k */
    final int f639k;

    /* renamed from: l */
    final int f640l;

    /* renamed from: m */
    final C0565gr f641m;

    /* renamed from: n */
    final C0533fz f642n;

    /* renamed from: o */
    final C0516fp f643o;

    /* renamed from: p */
    final C0586hd f644p;

    /* renamed from: q */
    final C0579gy f645q;

    /* renamed from: r */
    final C0540ge f646r;

    /* renamed from: s */
    final C0586hd f647s;

    /* renamed from: t */
    final C0586hd f648t;

    /* renamed from: bo.app.gg$a */
    public static class C0546a {

        /* renamed from: a */
        public static final C0565gr f650a = C0565gr.FIFO;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public Context f651b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f652c = 0;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public int f653d = 0;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public int f654e = 0;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public int f655f = 0;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public C0594hk f656g = null;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public Executor f657h = null;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public Executor f658i = null;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public boolean f659j = false;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public boolean f660k = false;
        /* access modifiers changed from: private */

        /* renamed from: l */
        public int f661l = 3;
        /* access modifiers changed from: private */

        /* renamed from: m */
        public int f662m = 3;

        /* renamed from: n */
        private boolean f663n = false;
        /* access modifiers changed from: private */

        /* renamed from: o */
        public C0565gr f664o = f650a;

        /* renamed from: p */
        private int f665p = 0;

        /* renamed from: q */
        private long f666q = 0;

        /* renamed from: r */
        private int f667r = 0;
        /* access modifiers changed from: private */

        /* renamed from: s */
        public C0533fz f668s = null;
        /* access modifiers changed from: private */

        /* renamed from: t */
        public C0516fp f669t = null;

        /* renamed from: u */
        private C0530fw f670u = null;
        /* access modifiers changed from: private */

        /* renamed from: v */
        public C0586hd f671v = null;
        /* access modifiers changed from: private */

        /* renamed from: w */
        public C0579gy f672w;
        /* access modifiers changed from: private */

        /* renamed from: x */
        public C0540ge f673x = null;
        /* access modifiers changed from: private */

        /* renamed from: y */
        public boolean f674y = false;

        public C0546a(Context context) {
            this.f651b = context.getApplicationContext();
        }

        /* renamed from: a */
        public C0546a mo7143a(int i) {
            if (!(this.f657h == null && this.f658i == null)) {
                C0599hn.m1064c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (i < 1) {
                this.f662m = 1;
            } else if (i > 10) {
                this.f662m = 10;
            } else {
                this.f662m = i;
            }
            return this;
        }

        /* renamed from: a */
        public C0546a mo7142a() {
            this.f663n = true;
            return this;
        }

        /* renamed from: a */
        public C0546a mo7146a(C0565gr grVar) {
            if (!(this.f657h == null && this.f658i == null)) {
                C0599hn.m1064c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.f664o = grVar;
            return this;
        }

        /* renamed from: b */
        public C0546a mo7147b(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("memoryCacheSize must be a positive number");
            }
            if (this.f668s != null) {
                C0599hn.m1064c("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.f665p = i;
            return this;
        }

        @Deprecated
        /* renamed from: c */
        public C0546a mo7150c(int i) {
            return mo7151d(i);
        }

        /* renamed from: d */
        public C0546a mo7151d(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("maxFileCount must be a positive number");
            }
            if (this.f669t != null) {
                C0599hn.m1064c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
            }
            this.f667r = i;
            return this;
        }

        @Deprecated
        /* renamed from: a */
        public C0546a mo7144a(C0530fw fwVar) {
            return mo7148b(fwVar);
        }

        /* renamed from: b */
        public C0546a mo7148b(C0530fw fwVar) {
            if (this.f669t != null) {
                C0599hn.m1064c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }
            this.f670u = fwVar;
            return this;
        }

        /* renamed from: a */
        public C0546a mo7145a(C0540ge geVar) {
            this.f673x = geVar;
            return this;
        }

        /* renamed from: b */
        public C0544gg mo7149b() {
            m872c();
            return new C0544gg(this);
        }

        /* renamed from: c */
        private void m872c() {
            if (this.f657h == null) {
                this.f657h = C0537gc.m785a(this.f661l, this.f662m, this.f664o);
            } else {
                this.f659j = true;
            }
            if (this.f658i == null) {
                this.f658i = C0537gc.m785a(this.f661l, this.f662m, this.f664o);
            } else {
                this.f660k = true;
            }
            if (this.f669t == null) {
                if (this.f670u == null) {
                    this.f670u = C0537gc.m787b();
                }
                this.f669t = C0537gc.m780a(this.f651b, this.f670u, this.f666q, this.f667r);
            }
            if (this.f668s == null) {
                this.f668s = C0537gc.m781a(this.f651b, this.f665p);
            }
            if (this.f663n) {
                this.f668s = new C0535ga(this.f668s, C0600ho.m1067a());
            }
            if (this.f671v == null) {
                this.f671v = C0537gc.m783a(this.f651b);
            }
            if (this.f672w == null) {
                this.f672w = C0537gc.m782a(this.f674y);
            }
            if (this.f673x == null) {
                this.f673x = C0540ge.m812t();
            }
        }
    }

    /* renamed from: bo.app.gg$b */
    static class C0547b implements C0586hd {

        /* renamed from: a */
        private final C0586hd f675a;

        public C0547b(C0586hd hdVar) {
            this.f675a = hdVar;
        }

        /* renamed from: a */
        public InputStream mo7152a(String str, Object obj) {
            switch (C0587a.m1009a(str)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.f675a.mo7152a(str, obj);
            }
        }
    }

    /* renamed from: bo.app.gg$c */
    static class C0548c implements C0586hd {

        /* renamed from: a */
        private final C0586hd f676a;

        public C0548c(C0586hd hdVar) {
            this.f676a = hdVar;
        }

        /* renamed from: a */
        public InputStream mo7152a(String str, Object obj) {
            InputStream a = this.f676a.mo7152a(str, obj);
            switch (C0587a.m1009a(str)) {
                case HTTP:
                case HTTPS:
                    return new C0561gn(a);
                default:
                    return a;
            }
        }
    }

    private C0544gg(C0546a aVar) {
        this.f629a = aVar.f651b.getResources();
        this.f630b = aVar.f652c;
        this.f631c = aVar.f653d;
        this.f632d = aVar.f654e;
        this.f633e = aVar.f655f;
        this.f634f = aVar.f656g;
        this.f635g = aVar.f657h;
        this.f636h = aVar.f658i;
        this.f639k = aVar.f661l;
        this.f640l = aVar.f662m;
        this.f641m = aVar.f664o;
        this.f643o = aVar.f669t;
        this.f642n = aVar.f668s;
        this.f646r = aVar.f673x;
        this.f644p = aVar.f671v;
        this.f645q = aVar.f672w;
        this.f637i = aVar.f659j;
        this.f638j = aVar.f660k;
        this.f647s = new C0547b(this.f644p);
        this.f648t = new C0548c(this.f644p);
        C0599hn.m1062a(aVar.f674y);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public C0563gp mo7141a() {
        DisplayMetrics displayMetrics = this.f629a.getDisplayMetrics();
        int i = this.f630b;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.f631c;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new C0563gp(i, i2);
    }
}
