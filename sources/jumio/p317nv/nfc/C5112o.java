package jumio.p317nv.nfc;

import java.io.Serializable;

/* renamed from: jumio.nv.nfc.o */
/* compiled from: ReadResult */
public class C5112o extends Throwable implements Serializable {

    /* renamed from: a */
    private C5113p f5632a;

    /* renamed from: b */
    private C5114q f5633b = C5114q.SUCCESSFUL;

    /* renamed from: c */
    private Object f5634c = null;

    public C5112o(C5113p pVar, C5114q qVar) {
        this.f5632a = pVar;
        this.f5633b = qVar;
    }

    public C5112o(C5113p pVar) {
        this.f5632a = pVar;
    }

    public C5112o() {
    }

    public C5112o(C5113p pVar, C5114q qVar, Throwable th) {
        this.f5632a = pVar;
        this.f5633b = qVar;
        this.f5633b.mo47215a(th);
    }

    public <T> C5112o(C5113p pVar, C5114q qVar, T t) {
        mo47204a(pVar);
        mo47205a(qVar);
        mo47203a(t);
    }

    public <T> C5112o(C5112o oVar) {
        this.f5632a = oVar.f5632a;
        this.f5633b = oVar.f5633b;
        this.f5634c = oVar.f5634c;
    }

    /* renamed from: a */
    public C5113p mo47202a() {
        return this.f5632a;
    }

    /* renamed from: a */
    public void mo47204a(C5113p pVar) {
        this.f5632a = pVar;
    }

    /* renamed from: b */
    public C5114q mo47207b() {
        return this.f5633b;
    }

    /* renamed from: a */
    public void mo47205a(C5114q qVar) {
        mo47206a(qVar, null);
    }

    /* renamed from: c */
    public boolean mo47208c() {
        return this.f5633b == C5114q.SUCCESSFUL;
    }

    /* renamed from: d */
    public boolean mo47209d() {
        return this.f5633b == C5114q.FAILED;
    }

    /* renamed from: a */
    public void mo47206a(C5114q qVar, Throwable th) {
        this.f5633b = qVar;
        this.f5633b.mo47215a(th);
    }

    /* renamed from: a */
    public <T> void mo47203a(T t) {
        this.f5634c = t;
    }

    /* renamed from: e */
    public <T> T mo47210e() {
        return this.f5634c;
    }

    public String toString() {
        return this.f5632a.toString() + " -> " + this.f5633b.toString();
    }

    /* renamed from: f */
    public String mo47211f() {
        String oVar = toString();
        if (this.f5634c != null) {
            return oVar + "    (data: " + this.f5634c.toString() + ")";
        }
        return oVar;
    }

    /* renamed from: g */
    public boolean mo47212g() {
        return this.f5633b == C5114q.ERROR;
    }
}
