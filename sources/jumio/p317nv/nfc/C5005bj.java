package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bj */
/* compiled from: ICCTextType */
public class C5005bj extends C5001bg {

    /* renamed from: a */
    public final int f5098a;

    /* renamed from: b */
    public final int f5099b;

    /* renamed from: c */
    public final byte[] f5100c;

    protected C5005bj(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i3);
        this.f5098a = C4981an.m3258e(bArr, i2);
        int i4 = i2 + 4;
        this.f5099b = C4981an.m3258e(bArr, i4);
        int i5 = i4 + 4;
        int i6 = 0;
        while (bArr[i5 + i6] != 0) {
            i6++;
        }
        this.f5100c = new byte[i6];
        System.arraycopy(bArr, i5, this.f5100c, 0, i6);
    }
}
