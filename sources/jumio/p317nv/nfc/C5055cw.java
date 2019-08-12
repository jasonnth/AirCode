package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.cw */
/* compiled from: DataBlkFloat */
public class C5055cw extends C5054cv {

    /* renamed from: h */
    public float[] f5484h;

    public C5055cw() {
    }

    public C5055cw(int i, int i2, int i3, int i4) {
        this.f5477a = i;
        this.f5478b = i2;
        this.f5479c = i3;
        this.f5480d = i4;
        this.f5481e = 0;
        this.f5482f = i3;
        this.f5484h = new float[(i3 * i4)];
    }

    /* renamed from: a */
    public final int mo47101a() {
        return 4;
    }

    /* renamed from: b */
    public final Object mo47103b() {
        return this.f5484h;
    }

    /* renamed from: c */
    public final float[] mo47104c() {
        return this.f5484h;
    }

    /* renamed from: a */
    public final void mo47102a(Object obj) {
        this.f5484h = (float[]) obj;
    }
}
