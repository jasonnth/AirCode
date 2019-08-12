package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ax */
/* compiled from: LookUpTableFP */
public abstract class C4991ax extends C4988au {

    /* renamed from: d */
    public final float[] f5037d;

    /* renamed from: a */
    public static C4991ax m3274a(C4999be beVar, int i) {
        if (beVar.f5056c == 1) {
            return new C4992ay(beVar, i);
        }
        return new C4993az(beVar, i);
    }

    protected C4991ax(C4999be beVar, int i) {
        super(beVar, i);
        this.f5037d = new float[i];
    }
}
