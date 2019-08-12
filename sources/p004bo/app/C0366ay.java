package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* renamed from: bo.app.ay */
public class C0366ay {
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static final String f137b = AppboyLogger.getAppboyLogTag(C0366ay.class);

    /* renamed from: c */
    private static volatile C0366ay f138c = null;

    /* renamed from: a */
    public final Object f139a = new Object();
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f140d = false;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C0391bt f141e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public HashSet<C0386bo> f142f = new HashSet<>();

    /* renamed from: a */
    public static C0366ay m110a() {
        if (f138c == null) {
            synchronized (C0366ay.class) {
                if (f138c == null) {
                    f138c = new C0366ay();
                }
            }
        }
        return f138c;
    }

    private C0366ay() {
    }

    /* renamed from: b */
    public C0391bt mo6779b() {
        return this.f141e;
    }

    /* renamed from: c */
    public Object mo6780c() {
        return this.f139a;
    }

    /* renamed from: d */
    public boolean mo6781d() {
        return this.f140d;
    }

    /* renamed from: a */
    public void mo6777a(C0386bo boVar) {
        synchronized (this.f139a) {
            if (boVar != null) {
                AppboyLogger.m1737i(f137b, "Queued event because no session exists.");
                this.f142f.add(boVar);
            }
        }
    }

    /* renamed from: a */
    public void mo6778a(Executor executor, final C0446di diVar, final C0615j jVar, final C0365ax axVar) {
        if (!this.f140d) {
            executor.execute(new Runnable() {
                public void run() {
                    boolean z;
                    boolean z2 = false;
                    AppboyLogger.m1733d(C0366ay.f137b, "Started offline recovery task.");
                    Collection<C0391bt> b = diVar.mo6940b();
                    synchronized (C0366ay.this.f139a) {
                        try {
                            C0366ay.this.m113a(b);
                            if (C0366ay.this.f141e == null) {
                                AppboyLogger.m1733d(C0366ay.f137b, "Did not set fossil session.");
                            } else if (C0366ay.this.f142f.size() != 0) {
                                AppboyLogger.m1733d(C0366ay.f137b, "Flushing queued event");
                                Iterator it = C0366ay.this.f142f.iterator();
                                while (it.hasNext()) {
                                    C0386bo boVar = (C0386bo) it.next();
                                    C0366ay.this.f141e.mo6832a(boVar);
                                    diVar.mo6939a(C0366ay.this.f141e, boVar);
                                }
                            }
                            for (C0391bt btVar : b) {
                                jVar.mo7305a(btVar);
                                if (btVar.mo6842j().mo6806a().size() > 0) {
                                    z = true;
                                } else {
                                    z = z2;
                                }
                                z2 = z;
                            }
                            if (z2) {
                                axVar.mo6768a(new C0629r[0]);
                            }
                            C0366ay.this.f140d = true;
                            AppboyLogger.m1733d(C0366ay.f137b, "Finished offline recovery task.");
                        } catch (Exception e) {
                            AppboyLogger.m1736e(C0366ay.f137b, "Exception occured recovering sealed sessions", e);
                            axVar.mo6767a((Throwable) e);
                        }
                    }
                }
            });
        } else {
            AppboyLogger.m1733d(f137b, "Sealed sessions should be synced once per app run.");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m113a(Collection<C0391bt> collection) {
        for (C0391bt btVar : collection) {
            if (this.f141e == null) {
                this.f141e = btVar;
            } else if (this.f141e.mo6835c().doubleValue() < btVar.mo6835c().doubleValue()) {
                this.f141e = btVar;
            }
        }
        if (this.f141e != null) {
            AppboyLogger.m1733d(f137b, "Set fossil session with id: " + this.f141e.mo6828a().toString());
        }
    }
}
