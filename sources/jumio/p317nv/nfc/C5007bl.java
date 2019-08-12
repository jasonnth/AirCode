package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bl */
/* compiled from: ICCXYZTypeReverse */
public class C5007bl extends C5006bk {

    /* renamed from: d */
    public final long f5104d;

    /* renamed from: j */
    public final long f5105j;

    /* renamed from: k */
    public final long f5106k;

    protected C5007bl(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i3);
        this.f5106k = (long) C4981an.m3258e(bArr, i2 + 8);
        this.f5105j = (long) C4981an.m3258e(bArr, i2 + 12);
        this.f5104d = (long) C4981an.m3258e(bArr, i2 + 16);
    }
}
