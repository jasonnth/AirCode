package p004bo.app;

import java.util.concurrent.Executor;

/* renamed from: bo.app.da */
public class C0432da implements C0426cw {

    /* renamed from: a */
    private final C0325a f317a;

    /* renamed from: b */
    private final C0431d f318b;

    /* renamed from: c */
    private final C0343ac f319c;

    /* renamed from: d */
    private final C0343ac f320d;

    /* renamed from: e */
    private final Executor f321e;

    /* renamed from: f */
    private final C0444dh f322f;

    /* renamed from: g */
    private final C0448dk f323g;

    /* renamed from: h */
    private C0375bd f324h;

    public C0432da(C0325a aVar, C0431d dVar, C0343ac acVar, C0343ac acVar2, Executor executor, C0444dh dhVar, C0448dk dkVar) {
        this.f317a = aVar;
        this.f318b = dVar;
        this.f319c = acVar;
        this.f320d = acVar2;
        this.f321e = executor;
        this.f322f = dhVar;
        this.f323g = dkVar;
    }

    /* renamed from: a */
    public void mo6927a(C0375bd bdVar) {
        this.f324h = bdVar;
    }

    /* renamed from: a */
    public void mo6923a(C0425cv cvVar) {
        if (cvVar instanceof C0424cu) {
            this.f321e.execute(new C0420cr((C0424cu) cvVar, this.f317a, this.f318b, this.f319c, this.f320d, this.f322f, this.f324h, this.f323g));
        } else if (cvVar instanceof C0415cm) {
            this.f321e.execute(new C0428cy((C0415cm) cvVar, new C0416cn(), this.f318b, this.f319c, this.f320d));
        }
    }

    /* renamed from: b */
    public void mo6924b(C0425cv cvVar) {
        if (cvVar instanceof C0424cu) {
            new C0420cr((C0424cu) cvVar, this.f317a, this.f318b, this.f319c, this.f320d, this.f322f, this.f324h, this.f323g).run();
        } else if (cvVar instanceof C0415cm) {
            new C0428cy((C0415cm) cvVar, new C0416cn(), this.f318b, this.f319c, this.f320d).run();
        }
    }
}
