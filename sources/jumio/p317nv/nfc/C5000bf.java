package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bf */
/* compiled from: ICCCurveTypeReverse */
public class C5000bf extends C5001bg {

    /* renamed from: j */
    private static final String f5058j = System.getProperty("line.separator");

    /* renamed from: a */
    public final int f5059a;

    /* renamed from: b */
    public final int f5060b;

    /* renamed from: c */
    public final int f5061c;

    /* renamed from: d */
    public final int[] f5062d = new int[this.f5061c];

    protected C5000bf(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i2 + 8);
        this.f5059a = C4981an.m3258e(bArr, i2);
        this.f5060b = C4981an.m3258e(bArr, i2 + 4);
        this.f5061c = C4981an.m3258e(bArr, i2 + 8);
        for (int i4 = 0; i4 < this.f5061c; i4++) {
            this.f5062d[(this.f5061c - 1) + i4] = C4981an.m3257d(bArr, i2 + 12 + (i4 * 2)) & 65535;
        }
    }
}
