package p004bo.app;

import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.ThreadFactory;

/* renamed from: bo.app.j */
public class C0615j implements C0627p {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f830a = AppboyLogger.getAppboyLogTag(C0615j.class);

    /* renamed from: b */
    private final AppboyConfigurationProvider f831b;

    /* renamed from: c */
    private final C0426cw f832c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final C0625n f833d;

    /* renamed from: e */
    private final Object f834e = new Object();

    /* renamed from: f */
    private volatile boolean f835f = false;

    /* renamed from: g */
    private volatile Thread f836g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public volatile boolean f837h = true;

    /* renamed from: i */
    private C0427cx f838i;

    /* renamed from: j */
    private boolean f839j = false;

    /* renamed from: bo.app.j$a */
    class C0617a implements Runnable {
        private C0617a() {
        }

        public void run() {
            while (C0615j.this.f837h) {
                try {
                    C0615j.this.m1131b(C0615j.this.f833d.mo7325b());
                } catch (InterruptedException e) {
                    AppboyLogger.m1733d(C0615j.f830a, String.format("Automatic thread interrupted! [%s]", new Object[]{e.getMessage()}));
                }
            }
        }
    }

    public C0615j(AppboyConfigurationProvider appboyConfigurationProvider, C0343ac acVar, C0426cw cwVar, C0625n nVar, ThreadFactory threadFactory, boolean z) {
        this.f831b = appboyConfigurationProvider;
        this.f832c = cwVar;
        this.f833d = nVar;
        this.f836g = threadFactory.newThread(new C0617a());
        this.f838i = new C0427cx(acVar);
        this.f839j = z;
    }

    /* renamed from: c */
    private C0422cs m1132c() {
        return new C0422cs(this.f831b.getBaseUrlForRequests(), new C0629r[0]);
    }

    /* renamed from: a */
    public void mo7305a(C0391bt btVar) {
        this.f833d.mo7305a(btVar);
    }

    /* renamed from: a */
    public void mo7306a(C0424cu cuVar) {
        this.f833d.mo7306a(cuVar);
    }

    /* renamed from: a */
    public void mo7304a(C0340ab abVar) {
        synchronized (this.f834e) {
            this.f837h = false;
            this.f836g.interrupt();
            this.f836g = null;
        }
        if (!this.f833d.mo7324a()) {
            this.f833d.mo7306a((C0424cu) m1132c());
        }
        C0424cu c = this.f833d.mo7326c();
        if (c != null) {
            m1133c(c);
        }
        abVar.mo6735a();
    }

    /* renamed from: a */
    public void mo7303a() {
        synchronized (this.f834e) {
            if (this.f835f) {
                AppboyLogger.m1733d(f830a, "Automatic request execution start was previously requested, continuing without action.");
                return;
            }
            if (this.f836g != null) {
                this.f836g.start();
            }
            this.f835f = true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1131b(C0424cu cuVar) {
        if (cuVar.mo6920f() || this.f839j) {
            this.f838i.mo6923a(cuVar);
        } else {
            this.f832c.mo6923a(cuVar);
        }
    }

    /* renamed from: c */
    private void m1133c(C0424cu cuVar) {
        if (cuVar.mo6920f() || this.f839j) {
            this.f838i.mo6924b(cuVar);
        } else {
            this.f832c.mo6924b(cuVar);
        }
    }
}
