package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.bi */
/* compiled from: ICCTextDescriptionType */
public class C5004bi extends C5001bg {

    /* renamed from: a */
    public final int f5094a;

    /* renamed from: b */
    public final int f5095b;

    /* renamed from: c */
    public final int f5096c;

    /* renamed from: d */
    public final byte[] f5097d = new byte[(this.f5096c - 1)];

    protected C5004bi(int i, byte[] bArr, int i2, int i3) {
        super(i, bArr, i2, i3);
        this.f5094a = C4981an.m3258e(bArr, i2);
        int i4 = i2 + 4;
        this.f5095b = C4981an.m3258e(bArr, i4);
        int i5 = i4 + 4;
        this.f5096c = C4981an.m3258e(bArr, i5);
        int i6 = i5 + 4;
        System.arraycopy(bArr, i6, this.f5097d, 0, this.f5096c - 1);
    }
}
