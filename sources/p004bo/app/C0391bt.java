package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: bo.app.bt */
public class C0391bt {

    /* renamed from: a */
    private static final String f199a = AppboyLogger.getAppboyLogTag(C0391bt.class);

    /* renamed from: b */
    private final ConcurrentLinkedQueue<C0386bo> f200b;

    /* renamed from: c */
    private final ConcurrentLinkedQueue<C0386bo> f201c;

    /* renamed from: d */
    private final C0395bx f202d;

    /* renamed from: e */
    private final double f203e;

    /* renamed from: f */
    private volatile C0384bm<Void> f204f;

    /* renamed from: g */
    private volatile C0384bm<Double> f205g;

    /* renamed from: h */
    private volatile boolean f206h;

    /* renamed from: i */
    private final Object f207i;

    public C0391bt(C0395bx bxVar, double d) {
        this(bxVar, d, null, new C0383bl(Collections.emptySet()), true, false, false);
    }

    public C0391bt(C0395bx bxVar, double d, Double d2, C0383bl blVar, boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        this.f206h = false;
        this.f207i = new Object();
        this.f202d = bxVar;
        this.f203e = d;
        if (!z) {
            z4 = true;
        }
        this.f204f = new C0384bm<>(null, z4);
        this.f205g = new C0384bm<>(d2, z3);
        this.f200b = new ConcurrentLinkedQueue<>(blVar.mo6806a());
        this.f201c = new ConcurrentLinkedQueue<>();
        this.f206h = z2;
    }

    /* renamed from: a */
    public C0395bx mo6828a() {
        return this.f202d;
    }

    /* renamed from: b */
    public double mo6833b() {
        return this.f203e;
    }

    /* renamed from: c */
    public Double mo6835c() {
        Double d;
        synchronized (this.f207i) {
            d = (Double) this.f205g.mo6807a();
        }
        return d;
    }

    /* renamed from: d */
    public C0384bm<Double> mo6836d() {
        C0384bm<Double> bmVar;
        synchronized (this.f207i) {
            bmVar = this.f205g;
        }
        return bmVar;
    }

    /* renamed from: a */
    public void mo6830a(Double d) {
        synchronized (this.f207i) {
            this.f205g.mo6808a(d);
        }
    }

    /* renamed from: e */
    public boolean mo6837e() {
        boolean c;
        synchronized (this.f207i) {
            c = this.f204f.mo6810c();
        }
        return c;
    }

    /* renamed from: f */
    public C0384bm<Void> mo6838f() {
        C0384bm<Void> bmVar;
        synchronized (this.f207i) {
            bmVar = this.f204f;
        }
        return bmVar;
    }

    /* renamed from: g */
    public boolean mo6839g() {
        boolean z;
        synchronized (this.f207i) {
            z = this.f206h;
        }
        return z;
    }

    /* renamed from: h */
    public boolean mo6840h() {
        boolean c;
        synchronized (this.f207i) {
            c = this.f205g.mo6810c();
        }
        return c;
    }

    /* renamed from: i */
    public void mo6841i() {
        synchronized (this.f207i) {
            this.f206h = true;
            mo6830a(Double.valueOf(C0455dp.m522b()));
        }
    }

    /* renamed from: a */
    public boolean mo6832a(C0386bo boVar) {
        synchronized (this.f207i) {
            this.f200b.add(boVar);
        }
        return true;
    }

    /* renamed from: j */
    public C0383bl mo6842j() {
        C0383bl blVar;
        synchronized (this.f207i) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.f200b);
            hashSet.addAll(this.f201c);
            blVar = new C0383bl(hashSet);
        }
        return blVar;
    }

    /* renamed from: k */
    public ConcurrentLinkedQueue<C0386bo> mo6843k() {
        ConcurrentLinkedQueue<C0386bo> concurrentLinkedQueue;
        synchronized (this.f207i) {
            concurrentLinkedQueue = this.f200b;
        }
        return concurrentLinkedQueue;
    }

    /* renamed from: l */
    public C0385bn mo6844l() {
        C0385bn bnVar;
        synchronized (this.f207i) {
            boolean a = mo6831a(mo6838f());
            if (a) {
                this.f204f.mo6811d();
            }
            boolean z = this.f206h && mo6831a(mo6836d());
            if (z) {
                this.f205g.mo6811d();
            }
            bnVar = new C0385bn(this, Double.valueOf(mo6833b()), z ? mo6835c() : null, new HashSet(mo6843k()), mo6828a(), a);
            this.f201c.addAll(this.f200b);
            this.f200b.clear();
        }
        return bnVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo6831a(C0384bm bmVar) {
        return bmVar != null && !bmVar.mo6809b() && !bmVar.mo6810c();
    }

    /* renamed from: a */
    public void mo6829a(C0385bn bnVar) {
        synchronized (this.f207i) {
            m223a(bnVar.mo6819f().mo6806a());
            if (bnVar.mo6817d()) {
                this.f204f.mo6812e();
            }
            if (bnVar.mo6818e() != null) {
                this.f205g.mo6812e();
            }
        }
    }

    /* renamed from: b */
    public void mo6834b(C0385bn bnVar) {
        synchronized (this.f207i) {
            m224b(bnVar.mo6819f().mo6806a());
            if (bnVar.mo6817d()) {
                this.f204f.mo6813f();
            }
            if (bnVar.mo6818e() != null) {
                this.f205g.mo6813f();
            }
        }
    }

    /* renamed from: a */
    private void m223a(Set<C0386bo> set) {
        synchronized (this.f207i) {
            this.f201c.removeAll(set);
        }
    }

    /* renamed from: b */
    private void m224b(Set<C0386bo> set) {
        synchronized (this.f207i) {
            AppboyLogger.m1733d(f199a, String.format("Adding %s failed events back into session", new Object[]{Integer.valueOf(set.size())}));
            this.f201c.removeAll(set);
            this.f200b.addAll(set);
        }
    }

    /* renamed from: m */
    public boolean mo6845m() {
        boolean z;
        synchronized (this.f207i) {
            z = mo6840h() && mo6837e() && mo6842j().mo6806a().isEmpty();
        }
        return z;
    }
}
