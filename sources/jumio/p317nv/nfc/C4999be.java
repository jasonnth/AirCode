package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.be */
/* compiled from: ICCCurveType */
public class C4999be extends C5001bg {

    /* renamed from: j */
    private static final String f5053j = System.getProperty("line.separator");

    /* renamed from: a */
    public final int f5054a;

    /* renamed from: b */
    public final int f5055b;

    /* renamed from: c */
    public final int f5056c;

    /* renamed from: d */
    public final int[] f5057d = new int[this.f5056c];

    /* renamed from: a */
    public static double m3283a(int i) {
        return ((double) i) / 65535.0d;
    }

    /* renamed from: b */
    public static double m3284b(int i) {
        return ((double) i) / 256.0d;
    }

    protected C4999be(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i2 + 8);
        this.f5054a = C4981an.m3258e(bArr, i2);
        this.f5055b = C4981an.m3258e(bArr, i2 + 4);
        this.f5056c = C4981an.m3258e(bArr, i2 + 8);
        for (int i4 = 0; i4 < this.f5056c; i4++) {
            this.f5057d[i4] = C4981an.m3257d(bArr, i2 + 12 + (i4 * 2)) & 65535;
        }
    }

    /* renamed from: c */
    public final int mo46966c(int i) {
        return this.f5057d[i];
    }
}
