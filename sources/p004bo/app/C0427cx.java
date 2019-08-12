package p004bo.app;

import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.cx */
public class C0427cx implements C0426cw {

    /* renamed from: a */
    private static final String f306a = AppboyLogger.getAppboyLogTag(C0427cx.class);

    /* renamed from: b */
    private final C0343ac f307b;

    public C0427cx(C0343ac acVar) {
        this.f307b = acVar;
    }

    /* renamed from: a */
    public void mo6923a(C0425cv cvVar) {
        m398c(cvVar);
    }

    /* renamed from: b */
    public void mo6924b(C0425cv cvVar) {
        m398c(cvVar);
    }

    /* renamed from: c */
    private void m398c(C0425cv cvVar) {
        AppboyLogger.m1737i(f306a, "Short circuiting execution of network request and immediately marking it as succeeded.");
        cvVar.mo6910a(this.f307b, (C0392bu) null);
        cvVar.mo6909a(this.f307b);
        if (cvVar instanceof C0424cu) {
            this.f307b.mo6736a(new C0345ae((C0424cu) cvVar), C0345ae.class);
        } else if (cvVar instanceof C0415cm) {
            this.f307b.mo6736a(new C0411ck((C0415cm) cvVar, null), C0411ck.class);
        }
    }
}
