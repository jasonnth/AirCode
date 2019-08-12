package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bn */
/* compiled from: ICCProfileHeader */
public class C5009bn {

    /* renamed from: a */
    public static int f5113a = C4981an.m3258e(new String("acsp").getBytes(), 0);

    /* renamed from: b */
    public static int f5114b = C4981an.m3258e(new String("psca").getBytes(), 0);

    /* renamed from: u */
    private static final String f5115u = System.getProperty("line.separator");

    /* renamed from: c */
    public int f5116c;

    /* renamed from: d */
    public int f5117d;

    /* renamed from: e */
    public int f5118e;

    /* renamed from: f */
    public int f5119f;

    /* renamed from: g */
    public int f5120g;

    /* renamed from: h */
    public int f5121h;

    /* renamed from: i */
    public int f5122i;

    /* renamed from: j */
    public int f5123j;

    /* renamed from: k */
    public int f5124k;

    /* renamed from: l */
    public int f5125l;

    /* renamed from: m */
    public int f5126m;

    /* renamed from: n */
    public int f5127n;

    /* renamed from: o */
    public int f5128o;

    /* renamed from: p */
    public int f5129p;

    /* renamed from: q */
    public byte[] f5130q = new byte[44];

    /* renamed from: r */
    public C5010bo f5131r;

    /* renamed from: s */
    public C5008bm f5132s;

    /* renamed from: t */
    public C5011bp f5133t;

    /* renamed from: v */
    private byte[] f5134v = null;

    public C5009bn() {
    }

    public C5009bn(byte[] bArr) {
        this.f5116c = C4981an.m3258e(bArr, 0);
        this.f5117d = C4981an.m3258e(bArr, 4);
        this.f5118e = C4981an.m3258e(bArr, 12);
        this.f5119f = C4981an.m3258e(bArr, 16);
        this.f5120g = C4981an.m3258e(bArr, 20);
        this.f5121h = C4981an.m3258e(bArr, 36);
        this.f5122i = C4981an.m3258e(bArr, 40);
        this.f5123j = C4981an.m3258e(bArr, 44);
        this.f5124k = C4981an.m3258e(bArr, 48);
        this.f5125l = C4981an.m3258e(bArr, 52);
        this.f5126m = C4981an.m3258e(bArr, 60);
        this.f5127n = C4981an.m3258e(bArr, 60);
        this.f5128o = C4981an.m3258e(bArr, 64);
        this.f5129p = C4981an.m3258e(bArr, 80);
        this.f5131r = C4981an.m3253b(bArr, 8);
        this.f5132s = C4981an.m3255c(bArr, 24);
        this.f5133t = C4981an.m3250a(bArr, 68);
        for (int i = 0; i < this.f5130q.length; i++) {
            this.f5130q[i] = bArr[i + 84];
        }
    }
}
