package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.aw */
/* compiled from: LookUpTable32LinearSRGBtoSRGB */
public class C4990aw extends C4989av {
    /* renamed from: a */
    public static C4990aw m3273a(int i, int i2, double d, double d2, double d3, double d4, double d5) {
        return new C4990aw(i, i2, d, d2, d3, d4, d5);
    }

    protected C4990aw(int i, int i2, double d, double d2, double d3, double d4, double d5) {
        super(i + 1, i2);
        double d6 = 1.0d / ((double) i);
        int floor = (int) Math.floor(((double) i) * d);
        double d7 = ((double) i2) * d2;
        int i3 = (i2 + 1) / 2;
        int i4 = 0;
        while (i4 <= floor) {
            this.f5036e[i4] = (int) (Math.floor(((((double) i4) * d6) * d7) + 0.5d) - ((double) i3));
            i4++;
        }
        double d8 = ((double) i2) * d3;
        double d9 = ((double) i2) * d5;
        while (i4 <= i) {
            this.f5036e[i4] = (int) (Math.floor(((Math.pow(((double) i4) * d6, d4) * d8) - d9) + 0.5d) - ((double) i3));
            i4++;
        }
    }
}
