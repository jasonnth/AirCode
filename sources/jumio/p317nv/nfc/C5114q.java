package jumio.p317nv.nfc;

import java.io.Serializable;

/* renamed from: jumio.nv.nfc.q */
/* compiled from: Verdict */
public enum C5114q implements Serializable {
    SUCCESSFUL,
    FAILED,
    NOT_AVAILABLE,
    ERROR;
    

    /* renamed from: e */
    private Throwable f5650e;

    /* renamed from: a */
    public void mo47215a(Throwable th) {
        this.f5650e = th;
    }

    /* renamed from: a */
    public Throwable mo47214a() {
        return this.f5650e;
    }

    public String toString() {
        return super.toString() + (this.f5650e != null ? " -- " + this.f5650e.toString() + " / " : "");
    }
}
