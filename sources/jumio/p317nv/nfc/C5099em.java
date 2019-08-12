package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.em */
/* compiled from: SynWTFilterSpec */
public class C5099em extends C5013br {
    public C5099em(int i, int i2, byte b) {
        super(i, i2, b);
    }

    /* renamed from: c */
    public C5094eh[] mo47161c(int i, int i2) {
        return ((C5094eh[][]) mo46975b(i, i2))[0];
    }

    /* renamed from: d */
    public C5094eh[] mo47162d(int i, int i2) {
        return ((C5094eh[][]) mo46975b(i, i2))[1];
    }

    /* renamed from: e */
    public boolean mo47163e(int i, int i2) {
        C5094eh[] c = mo47161c(i, i2);
        C5094eh[] d = mo47162d(i, i2);
        for (int length = c.length - 1; length >= 0; length--) {
            if (!c[length].mo47147b() || !d[length].mo47147b()) {
                return false;
            }
        }
        return true;
    }
}
