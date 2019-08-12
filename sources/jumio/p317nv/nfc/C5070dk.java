package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.dk */
/* compiled from: Dequantizer */
public abstract class C5070dk extends C5092ef implements C5084dy {

    /* renamed from: f */
    private static final String[][] f5512f = null;

    /* renamed from: a */
    protected C5069dj f5513a;

    /* renamed from: b */
    protected int[] f5514b = null;

    /* renamed from: c */
    protected int[] f5515c = null;

    /* renamed from: g */
    private C5052ct f5516g;

    /* renamed from: h */
    private C5099em f5517h;

    public C5070dk(C5069dj djVar, int[] iArr, C5039cg cgVar) {
        super(djVar);
        if (iArr.length != djVar.mo47009c()) {
            throw new IllegalArgumentException();
        }
        this.f5513a = djVar;
        this.f5515c = iArr;
        this.f5516g = cgVar.f5411k;
        this.f5517h = cgVar.f5406f;
    }

    /* renamed from: g */
    public int mo47128g(int i) {
        return this.f5514b[i];
    }

    /* renamed from: e */
    public C5093eg mo47019e(int i, int i2) {
        return this.f5513a.mo47019e(i, i2);
    }

    /* renamed from: a */
    public static String[][] m3552a() {
        return f5512f;
    }

    /* renamed from: f */
    public void mo47028f(int i, int i2) {
        int i3;
        boolean z = false;
        this.f5513a.mo47028f(i, i2);
        this.f5562d = mo47013d();
        if (((Integer) this.f5516g.mo46979d(this.f5562d)).intValue() != 0) {
            int c = this.f5513a.mo47009c() > 3 ? 3 : this.f5513a.mo47009c();
            int i4 = 0;
            for (int i5 = 0; i5 < c; i5++) {
                if (this.f5517h.mo47163e(this.f5562d, i5)) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i4 += i3;
            }
            if (i4 == 3) {
                z = true;
            } else if (i4 == 0) {
                z = true;
            } else {
                throw new IllegalArgumentException("Wavelet transformation and component transformation not coherent in tile" + this.f5562d);
            }
        }
        switch (z) {
            case false:
                this.f5514b = this.f5515c;
                return;
            case true:
                this.f5514b = C5061db.m3524a(this.f5515c, 1, null);
                return;
            case true:
                this.f5514b = C5061db.m3524a(this.f5515c, 2, null);
                return;
            default:
                throw new IllegalArgumentException("Non JPEG 2000 part I component transformation for tile: " + this.f5562d);
        }
    }
}
