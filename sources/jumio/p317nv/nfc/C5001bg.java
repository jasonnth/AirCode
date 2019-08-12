package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bg */
/* compiled from: ICCTag */
public abstract class C5001bg {

    /* renamed from: a */
    private static final int f5063a = C4981an.m3258e("cprt".getBytes(), 0);

    /* renamed from: b */
    private static final int f5064b = C4981an.m3258e("desc".getBytes(), 0);

    /* renamed from: c */
    private static final int f5065c = C4981an.m3258e("wtpt".getBytes(), 0);

    /* renamed from: d */
    private static final int f5066d = C4981an.m3258e("bkpt".getBytes(), 0);

    /* renamed from: j */
    private static final int f5067j = C4981an.m3258e("rXYZ".getBytes(), 0);

    /* renamed from: k */
    private static final int f5068k = C4981an.m3258e("gXYZ".getBytes(), 0);

    /* renamed from: l */
    private static final int f5069l = C4981an.m3258e("bXYZ".getBytes(), 0);

    /* renamed from: m */
    private static final int f5070m = C4981an.m3258e("kXYZ".getBytes(), 0);

    /* renamed from: n */
    private static final int f5071n = C4981an.m3258e("rTRC".getBytes(), 0);

    /* renamed from: o */
    private static final int f5072o = C4981an.m3258e("gTRC".getBytes(), 0);

    /* renamed from: p */
    private static final int f5073p = C4981an.m3258e("bTRC".getBytes(), 0);

    /* renamed from: q */
    private static final int f5074q = C4981an.m3258e("kTRC".getBytes(), 0);

    /* renamed from: r */
    private static final int f5075r = C4981an.m3258e("dmnd".getBytes(), 0);

    /* renamed from: s */
    private static final int f5076s = C4981an.m3258e("dmdd".getBytes(), 0);

    /* renamed from: t */
    private static final int f5077t = C4981an.m3258e("desc".getBytes(), 0);

    /* renamed from: u */
    private static final int f5078u = C4981an.m3258e("text".getBytes(), 0);

    /* renamed from: v */
    private static final int f5079v = C4981an.m3258e("curv".getBytes(), 0);

    /* renamed from: w */
    private static final int f5080w = C4981an.m3258e("vruc".getBytes(), 0);

    /* renamed from: x */
    private static final int f5081x = C4981an.m3258e("XYZ ".getBytes(), 0);

    /* renamed from: y */
    private static final int f5082y = C4981an.m3258e(" ZYX".getBytes(), 0);

    /* renamed from: e */
    public final int f5083e;

    /* renamed from: f */
    public final int f5084f;

    /* renamed from: g */
    public final byte[] f5085g;

    /* renamed from: h */
    public final int f5086h;

    /* renamed from: i */
    public final int f5087i;

    /* renamed from: a */
    public static C5001bg m3286a(int i, byte[] bArr, int i2, int i3) {
        int e = C4981an.m3258e(bArr, i2);
        if (e == f5077t) {
            return new C5004bi(i, bArr, i2, i3);
        }
        if (e == f5078u) {
            return new C5005bj(i, bArr, i2, i3);
        }
        if (e == f5081x) {
            return new C5006bk(i, bArr, i2, i3);
        }
        if (e == f5082y) {
            return new C5007bl(i, bArr, i2, i3);
        }
        if (e == f5079v) {
            return new C4999be(i, bArr, i2, i3);
        }
        if (e == f5080w) {
            return new C5000bf(i, bArr, i2, i3);
        }
        throw new IllegalArgumentException("bad tag type");
    }

    protected C5001bg(int i, byte[] bArr, int i2, int i3) {
        this.f5083e = i;
        this.f5085g = bArr;
        this.f5086h = i2;
        this.f5087i = i3;
        this.f5084f = C4981an.m3258e(bArr, i2);
    }
}
