package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.cx */
/* compiled from: DataBlkInt */
public class C5056cx extends C5054cv {

    /* renamed from: h */
    public int[] f5485h;

    public C5056cx() {
    }

    public C5056cx(int i, int i2, int i3, int i4) {
        this.f5477a = i;
        this.f5478b = i2;
        this.f5479c = i3;
        this.f5480d = i4;
        this.f5481e = 0;
        this.f5482f = i3;
        this.f5485h = new int[(i3 * i4)];
    }

    /* renamed from: a */
    public final int mo47101a() {
        return 3;
    }

    /* renamed from: b */
    public final Object mo47103b() {
        return this.f5485h;
    }

    /* renamed from: c */
    public final int[] mo47105c() {
        return this.f5485h;
    }

    /* renamed from: a */
    public final void mo47102a(Object obj) {
        this.f5485h = (int[]) obj;
    }
}
