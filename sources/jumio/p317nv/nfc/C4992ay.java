package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ay */
/* compiled from: LookUpTableFPGamma */
public class C4992ay extends C4991ax {

    /* renamed from: f */
    private static final String f5038f = System.getProperty("line.separator");

    /* renamed from: e */
    double f5039e = -1.0d;

    public C4992ay(C4999be beVar, int i) {
        super(beVar, i);
        this.f5039e = C4999be.m3284b(beVar.mo46966c(0));
        for (int i2 = 0; i2 < i; i2++) {
            this.f5037d[i2] = (float) Math.pow(((double) i2) / ((double) (i - 1)), this.f5039e);
        }
    }
}
