package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bz */
/* compiled from: CBlkInfo */
public class C5031bz {

    /* renamed from: a */
    public int f5292a;

    /* renamed from: b */
    public int f5293b;

    /* renamed from: c */
    public int f5294c;

    /* renamed from: d */
    public int f5295d;

    /* renamed from: e */
    public int f5296e;

    /* renamed from: f */
    public int[] f5297f;

    /* renamed from: g */
    public int[] f5298g;

    /* renamed from: h */
    public int[] f5299h;

    /* renamed from: i */
    public int f5300i;

    /* renamed from: j */
    public int[][] f5301j;

    /* renamed from: k */
    public int[] f5302k;

    public C5031bz(int i, int i2, int i3, int i4, int i5) {
        this.f5292a = i;
        this.f5293b = i2;
        this.f5294c = i3;
        this.f5295d = i4;
        this.f5298g = new int[i5];
        this.f5297f = new int[i5];
        this.f5299h = new int[i5];
        this.f5301j = new int[i5][];
        this.f5302k = new int[i5];
        for (int i6 = i5 - 1; i6 >= 0; i6--) {
            this.f5302k[i6] = -1;
        }
    }

    /* renamed from: a */
    public void mo47026a(int i, int i2) {
        this.f5299h[i] = i2;
        this.f5300i = 0;
        for (int i3 = 0; i3 <= i; i3++) {
            this.f5300i += this.f5299h[i3];
        }
    }
}
