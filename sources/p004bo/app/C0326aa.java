package p004bo.app;

import android.content.Context;
import android.content.Intent;
import com.appboy.events.IEventSubscriber;
import com.appboy.services.AppboyDataSyncService;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: bo.app.aa */
public class C0326aa {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f43a = AppboyLogger.getAppboyLogTag(C0326aa.class);
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C0379bh f44b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C0436de f45c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final C0627p f46d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final C0368az f47e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public final C0375bd f48f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final Context f49g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public final C0451dl f50h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final Intent f51i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public final C0442df f52j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public final C0448dk f53k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public final C0506fh f54l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public final C0412cl f55m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public AtomicBoolean f56n = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: o */
    public AtomicBoolean f57o = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: p */
    public C0353am f58p;

    public C0326aa(C0379bh bhVar, C0436de deVar, C0627p pVar, C0368az azVar, C0365ax axVar, Context context, C0451dl dlVar, C0442df dfVar, C0448dk dkVar, C0506fh fhVar, C0412cl clVar) {
        this.f44b = bhVar;
        this.f45c = deVar;
        this.f46d = pVar;
        this.f47e = azVar;
        this.f48f = axVar;
        this.f49g = context;
        this.f50h = dlVar;
        this.f51i = new Intent(context.getPackageName() + ".REQUEST_DATA_SYNC").setClass(context, AppboyDataSyncService.class);
        this.f52j = dfVar;
        this.f53k = dkVar;
        this.f54l = fhVar;
        this.f55m = clVar;
    }

    /* renamed from: a */
    public void mo6707a(C0340ab abVar) {
        abVar.mo6737a(mo6706a((Semaphore) null), C0344ad.class);
        abVar.mo6737a(mo6714g(), C0349ai.class);
        abVar.mo6737a(mo6715h(), C0350aj.class);
        abVar.mo6737a(mo6717j(), C0353am.class);
        abVar.mo6737a(mo6716i(), C0348ah.class);
        abVar.mo6737a(mo6708b((Semaphore) null), Throwable.class);
        abVar.mo6737a(mo6720m(), C0356ap.class);
        abVar.mo6737a(mo6718k(), C0355ao.class);
        abVar.mo6737a(mo6705a(), C0345ae.class);
        abVar.mo6737a(mo6713f(), C0418cp.class);
        abVar.mo6737a(mo6711d(), C0411ck.class);
        abVar.mo6737a(mo6712e(), C0410cj.class);
        abVar.mo6737a(mo6719l(), C0354an.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public IEventSubscriber<C0345ae> mo6705a() {
        return new IEventSubscriber<C0345ae>() {
            /* renamed from: a */
            public void trigger(C0345ae aeVar) {
                C0424cu a = aeVar.mo6744a();
                C0404ce c = a.mo6917c();
                if (c != null) {
                    if (a.mo6917c().mo6893a() != null) {
                        try {
                            for (C0385bn bnVar : a.mo6917c().mo6893a()) {
                                bnVar.mo6815b();
                                if (!bnVar.mo6814a().mo6845m() || bnVar.mo6814a().mo6828a() == C0326aa.this.f47e.mo6786c() || bnVar.mo6814a() == C0366ay.m110a().mo6779b()) {
                                    C0326aa.this.f45c.mo6938a(bnVar.mo6814a());
                                } else {
                                    AppboyLogger.m1733d(C0326aa.f43a, String.format("Clearing fully dispatched sealed session %s", new Object[]{bnVar.mo6814a().mo6828a().toString()}));
                                    C0326aa.this.f45c.mo6942c(bnVar.mo6814a()).get();
                                }
                            }
                        } catch (Exception e) {
                            AppboyLogger.m1736e(C0326aa.f43a, "Caught exception while trying to clear sealed sessions", e);
                        }
                    }
                    C0405cf c2 = c.mo6895c();
                    if (c2 != null) {
                        C0326aa.this.f50h.mo6932b(c2);
                    }
                    C0399ca b = c.mo6894b();
                    if (b != null) {
                        C0326aa.this.f52j.mo6932b(b);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public IEventSubscriber<C0344ad> mo6706a(final Semaphore semaphore) {
        return new IEventSubscriber<C0344ad>() {
            /* renamed from: a */
            public void trigger(C0344ad adVar) {
                if (adVar.mo6742b() != null && adVar.mo6742b().mo6887a()) {
                    C0326aa.this.f53k.mo6966a(true);
                }
                JSONObject c = adVar.mo6743c();
                if (c != null && c.toString().contains(C0634w.TRIGGERS.forJsonPut())) {
                    C0326aa.this.mo6709b();
                    C0326aa.this.mo6710c();
                }
                C0404ce a = adVar.mo6741a();
                if (a != null) {
                    for (C0385bn bnVar : C0454do.m514a(a.mo6893a())) {
                        bnVar.mo6816c();
                        C0326aa.this.f46d.mo7305a(bnVar.mo6814a());
                    }
                    if (semaphore != null) {
                        semaphore.release();
                    }
                    if (a.mo6895c() != null) {
                        C0326aa.this.f50h.mo6932b(null);
                    }
                    if (a.mo6894b() != null) {
                        C0326aa.this.f52j.mo6932b(null);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6709b() {
        if (this.f56n.compareAndSet(true, false)) {
            this.f54l.mo7059a(new C0499fa());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo6710c() {
        if (this.f57o.compareAndSet(true, false) && this.f58p.mo6747a() != null) {
            this.f54l.mo7059a(new C0501fc(this.f58p.mo6747a(), this.f58p.mo6748b()));
            this.f58p = null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public IEventSubscriber<C0411ck> mo6711d() {
        return new IEventSubscriber<C0411ck>() {
            /* renamed from: a */
            public void trigger(C0411ck ckVar) {
                C0417co a = ckVar.mo6903a();
                if (a.mo6912a()) {
                    AppboyLogger.m1737i(C0326aa.f43a, "Received PlaceIQ id: " + a.mo6913b());
                    try {
                        C0326aa.this.f55m.mo6904a();
                        C0326aa.this.f50h.mo6994h(a.mo6913b());
                    } catch (Exception e) {
                        AppboyLogger.m1736e(C0326aa.f43a, "Failed to log PlaceIQ id event", e);
                    }
                } else {
                    AppboyLogger.m1739w(C0326aa.f43a, "Received PlaceIQ response without PlaceIQ Id.");
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public IEventSubscriber<C0410cj> mo6712e() {
        return new IEventSubscriber<C0410cj>() {
            /* renamed from: a */
            public void trigger(C0410cj cjVar) {
                AppboyLogger.m1737i(C0326aa.f43a, "Place IQ dispatch failed for: " + cjVar.mo6902a().mo6916b());
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public IEventSubscriber<C0418cp> mo6713f() {
        return new IEventSubscriber<C0418cp>() {
            /* renamed from: a */
            public void trigger(C0418cp cpVar) {
                C0326aa.this.f55m.mo6905b();
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public IEventSubscriber<C0349ai> mo6714g() {
        return new IEventSubscriber<C0349ai>() {
            /* renamed from: a */
            public void trigger(C0349ai aiVar) {
                C0326aa.this.f44b.mo6757a();
                C0326aa.this.f44b.mo6759b();
                C0326aa.this.f56n.set(true);
                C0326aa.this.f48f.mo6768a(C0629r.TRIGGERS);
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public IEventSubscriber<C0350aj> mo6715h() {
        return new IEventSubscriber<C0350aj>() {
            /* renamed from: a */
            public void trigger(C0350aj ajVar) {
                C0326aa.this.f46d.mo7305a(ajVar.mo6746a());
                C0326aa.this.f49g.startService(C0326aa.this.f51i);
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public IEventSubscriber<C0348ah> mo6716i() {
        return new IEventSubscriber<C0348ah>() {
            /* renamed from: a */
            public void trigger(C0348ah ahVar) {
                C0326aa.this.f44b.mo6756a(ahVar.mo6745a());
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public IEventSubscriber<C0353am> mo6717j() {
        return new IEventSubscriber<C0353am>() {
            /* renamed from: a */
            public void trigger(C0353am amVar) {
                C0326aa.this.f57o.set(true);
                C0326aa.this.f58p = amVar;
                AppboyLogger.m1737i(C0326aa.f43a, "Requesting trigger update due to trigger-eligible push click event");
                C0326aa.this.f48f.mo6768a(C0629r.TRIGGERS);
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public IEventSubscriber<C0355ao> mo6718k() {
        return new IEventSubscriber<C0355ao>() {
            /* renamed from: a */
            public void trigger(C0355ao aoVar) {
                C0326aa.this.f54l.mo7060a(aoVar.mo6750a());
                C0326aa.this.mo6709b();
                C0326aa.this.mo6710c();
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public IEventSubscriber<C0354an> mo6719l() {
        return new IEventSubscriber<C0354an>() {
            /* renamed from: a */
            public void trigger(C0354an anVar) {
                C0326aa.this.f54l.mo7059a(anVar.mo6749a());
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public IEventSubscriber<Throwable> mo6708b(final Semaphore semaphore) {
        return new IEventSubscriber<Throwable>() {
            /* renamed from: a */
            public void trigger(Throwable th) {
                try {
                    C0326aa.this.f48f.mo6767a(th);
                    if (semaphore != null) {
                        semaphore.release();
                    }
                } catch (Exception e) {
                    AppboyLogger.m1736e(C0326aa.f43a, "Failed to log error.", e);
                    if (semaphore != null) {
                        semaphore.release();
                    }
                } catch (Throwable th2) {
                    if (semaphore != null) {
                        semaphore.release();
                    }
                    throw th2;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public IEventSubscriber<C0356ap> mo6720m() {
        return new IEventSubscriber<C0356ap>() {
            /* renamed from: a */
            public void trigger(C0356ap apVar) {
                try {
                    C0326aa.this.f48f.mo6763a(apVar);
                } catch (Exception e) {
                    AppboyLogger.m1736e(C0326aa.f43a, "Failed to log the database exception.", e);
                }
            }
        };
    }
}
