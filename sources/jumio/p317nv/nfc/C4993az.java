package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.az */
/* compiled from: LookUpTableFPInterp */
public class C4993az extends C4991ax {
    public C4993az(C4999be beVar, int i) {
        super(beVar, i);
        double d = ((double) (beVar.f5056c - 1)) / ((double) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            double d2 = ((double) i2) * d;
            double floor = Math.floor(d2);
            int i3 = (int) floor;
            int ceil = (int) Math.ceil(d2);
            if (i3 == ceil) {
                this.f5037d[i2] = (float) C4999be.m3283a(beVar.mo46966c(i3));
            } else {
                double a = C4999be.m3283a(beVar.mo46966c(i3));
                double d3 = d2 - floor;
                this.f5037d[i2] = (float) ((d3 * (C4999be.m3283a(beVar.mo46966c(ceil)) - a)) + a);
            }
        }
    }
}
