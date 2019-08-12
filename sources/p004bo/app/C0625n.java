package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: bo.app.n */
public final class C0625n implements C0627p {

    /* renamed from: a */
    private static final String f861a = AppboyLogger.getAppboyLogTag(C0625n.class);

    /* renamed from: b */
    private final C0377bf f862b;

    /* renamed from: c */
    private final C0451dl f863c;

    /* renamed from: d */
    private final ConcurrentHashMap<String, C0391bt> f864d = new ConcurrentHashMap<>();

    /* renamed from: e */
    private final LinkedBlockingQueue<C0424cu> f865e = new LinkedBlockingQueue<>(1000);

    /* renamed from: f */
    private C0375bd f866f;

    public C0625n(C0451dl dlVar, C0377bf bfVar) {
        this.f863c = dlVar;
        this.f862b = bfVar;
    }

    /* renamed from: a */
    public void mo7305a(C0391bt btVar) {
        if (btVar == null) {
            throw new NullPointerException();
        }
        this.f864d.putIfAbsent(btVar.mo6828a().toString(), btVar);
    }

    /* renamed from: a */
    public void mo7306a(C0424cu cuVar) {
        if (cuVar == null) {
            throw new NullPointerException();
        } else if (mo7327d()) {
            AppboyLogger.m1737i(f861a, "Network requests are offline, not adding request to queue.");
        } else {
            AppboyLogger.m1737i(f861a, String.format("Adding request to dispatcher with parameters: %s", new Object[]{String.valueOf(cuVar.mo6919e())}));
            this.f865e.add(cuVar);
        }
    }

    /* renamed from: a */
    public boolean mo7324a() {
        return !this.f865e.isEmpty();
    }

    /* renamed from: b */
    public C0424cu mo7325b() {
        C0424cu cuVar = (C0424cu) this.f865e.take();
        try {
            if (this.f866f != null) {
                this.f866f.mo6774d();
            }
        } catch (Exception e) {
            AppboyLogger.m1734d(f861a, "Caught exception while logging stored push clicks during takeRequest().", e);
        }
        return m1160b(cuVar);
    }

    /* renamed from: c */
    public C0424cu mo7326c() {
        C0424cu cuVar = (C0424cu) this.f865e.poll();
        if (cuVar != null) {
            m1160b(cuVar);
        }
        return cuVar;
    }

    /* renamed from: a */
    public void mo7323a(C0375bd bdVar) {
        this.f866f = bdVar;
    }

    /* renamed from: b */
    private synchronized C0424cu m1160b(C0424cu cuVar) {
        if (cuVar == null) {
            cuVar = null;
        } else if (!(cuVar instanceof C0433db)) {
            cuVar.mo6915a(new C0404ce(m1161e(), this.f862b.mo6795c(), (C0405cf) this.f863c.mo6931b()));
            if (this.f862b.mo6796d() != null) {
                cuVar.mo6914a(this.f862b.mo6796d().dispatch());
            }
        }
        return cuVar;
    }

    /* renamed from: e */
    private List<C0385bn> m1161e() {
        AppboyLogger.m1733d(f861a, "dispatching sessions:");
        Collection<C0391bt> values = this.f864d.values();
        ArrayList arrayList = new ArrayList();
        for (C0391bt btVar : values) {
            C0385bn l = btVar.mo6844l();
            AppboyLogger.m1733d(f861a, l.forJsonPut().toString());
            arrayList.add(l);
            values.remove(btVar);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public boolean mo7327d() {
        return this.f866f != null && this.f866f.mo6776f();
    }
}
