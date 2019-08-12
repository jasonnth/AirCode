package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.an */
/* compiled from: ICCProfile */
public abstract class C4981an {

    /* renamed from: a */
    public static final int f5001a = m3258e(new String("acsp").getBytes(), 0);

    /* renamed from: b */
    public static final int f5002b = m3258e(new String("psca").getBytes(), 0);

    /* renamed from: c */
    public static final int f5003c = m3258e(new String("scnr").getBytes(), 0);

    /* renamed from: d */
    public static final int f5004d = m3258e(new String("mntr").getBytes(), 0);

    /* renamed from: e */
    public static final int f5005e = m3258e(new String("RGB ").getBytes(), 0);

    /* renamed from: f */
    public static final int f5006f = m3258e(new String("GRAY").getBytes(), 0);

    /* renamed from: g */
    public static final int f5007g = m3258e(new String("XYZ ").getBytes(), 0);

    /* renamed from: h */
    public static final int f5008h = m3258e(new String("kTRC").getBytes(), 0);

    /* renamed from: i */
    public static final int f5009i = m3258e(new String("rXYZ").getBytes(), 0);

    /* renamed from: j */
    public static final int f5010j = m3258e(new String("gXYZ").getBytes(), 0);

    /* renamed from: k */
    public static final int f5011k = m3258e(new String("bXYZ").getBytes(), 0);

    /* renamed from: l */
    public static final int f5012l = m3258e(new String("rTRC").getBytes(), 0);

    /* renamed from: m */
    public static final int f5013m = m3258e(new String("gTRC").getBytes(), 0);

    /* renamed from: n */
    public static final int f5014n = m3258e(new String("bTRC").getBytes(), 0);

    /* renamed from: o */
    public static final int f5015o = m3258e(new String("cprt").getBytes(), 0);

    /* renamed from: p */
    public static final int f5016p = m3258e(new String("wtpt").getBytes(), 0);

    /* renamed from: q */
    public static final int f5017q = m3258e(new String("desc").getBytes(), 0);

    /* renamed from: r */
    private static final String f5018r = System.getProperty("line.separator");

    /* renamed from: s */
    private C5009bn f5019s = null;

    /* renamed from: t */
    private C5002bh f5020t = null;

    /* renamed from: u */
    private byte[] f5021u = null;

    /* renamed from: a */
    public static C5011bp m3250a(byte[] bArr, int i) {
        return new C5011bp(m3258e(bArr, i), m3258e(bArr, i + 4), m3258e(bArr, i + 8));
    }

    /* renamed from: b */
    public static C5010bo m3253b(byte[] bArr, int i) {
        return new C5010bo(bArr[i], bArr[i + 1], bArr[i + 2], bArr[i + 3]);
    }

    /* renamed from: c */
    public static C5008bm m3255c(byte[] bArr, int i) {
        return new C5008bm(m3257d(bArr, i), m3257d(bArr, i + 2), m3257d(bArr, i + 4), m3257d(bArr, i + 6), m3257d(bArr, i + 8), m3257d(bArr, i + 10));
    }

    /* renamed from: d */
    public static short m3257d(byte[] bArr, int i) {
        return (short) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
    }

    /* renamed from: e */
    public static int m3258e(byte[] bArr, int i) {
        return ((m3257d(bArr, i) & 65535) << 16) | (m3257d(bArr, i + 2) & 65535);
    }

    /* renamed from: f */
    public static long m3259f(byte[] bArr, int i) {
        return (((long) (m3258e(bArr, i) & -1)) << 32) | ((long) (m3258e(bArr, i + 4) & -1));
    }

    /* renamed from: b */
    private int m3252b() {
        return this.f5019s.f5118e;
    }

    /* renamed from: c */
    private int m3254c() {
        return this.f5019s.f5120g;
    }

    /* renamed from: d */
    private int m3256d() {
        return this.f5019s.f5121h;
    }

    protected C4981an(C5140y yVar) throws C5144z, C4983ap {
        this.f5021u = yVar.mo47259a();
        m3251a(this.f5021u);
    }

    /* renamed from: a */
    private void m3251a(byte[] bArr) throws C4983ap {
        this.f5019s = new C5009bn(bArr);
        this.f5020t = C5002bh.m3287a(bArr);
        if (m3256d() != f5001a || ((m3252b() != f5003c && m3252b() != f5004d) || m3254c() != f5007g)) {
            throw new C4983ap();
        }
    }

    /* renamed from: a */
    public C4987at mo46959a() throws C4983ap {
        C4999be beVar = (C4999be) this.f5020t.get(new Integer(f5008h));
        if (beVar != null) {
            return C4987at.m3270b(beVar);
        }
        C4999be beVar2 = (C4999be) this.f5020t.get(new Integer(f5012l));
        if (beVar2 != null) {
            return C4987at.m3271b(beVar2, (C4999be) this.f5020t.get(new Integer(f5013m)), (C4999be) this.f5020t.get(new Integer(f5014n)), (C5006bk) this.f5020t.get(new Integer(f5009i)), (C5006bk) this.f5020t.get(new Integer(f5010j)), (C5006bk) this.f5020t.get(new Integer(f5011k)));
        }
        throw new C4983ap("curve data not found in profile");
    }
}
