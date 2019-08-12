package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bk */
/* compiled from: ICCXYZType */
public class C5006bk extends C5001bg {

    /* renamed from: a */
    public final long f5101a;

    /* renamed from: b */
    public final long f5102b;

    /* renamed from: c */
    public final long f5103c;

    /* renamed from: a */
    public static double m3291a(long j) {
        return ((double) j) / 65536.0d;
    }

    protected C5006bk(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i3);
        this.f5101a = (long) C4981an.m3258e(bArr, i2 + 8);
        this.f5102b = (long) C4981an.m3258e(bArr, i2 + 12);
        this.f5103c = (long) C4981an.m3258e(bArr, i2 + 16);
    }
}
