package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ck */
/* compiled from: ByteInputBuffer */
public class C5043ck {

    /* renamed from: a */
    private byte[] f5426a;

    /* renamed from: b */
    private int f5427b;

    /* renamed from: c */
    private int f5428c;

    public C5043ck(byte[] bArr, int i, int i2) {
        this.f5426a = bArr;
        this.f5428c = i;
        this.f5427b = i + i2;
    }

    /* renamed from: a */
    public void mo47081a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            if (i2 < 0 || this.f5427b + i2 > this.f5426a.length) {
                throw new IllegalArgumentException();
            } else if (i < 0) {
                this.f5428c = this.f5427b;
                this.f5427b += i2;
            } else {
                this.f5427b = i + i2;
                this.f5428c = i;
            }
        } else if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException();
        } else {
            this.f5426a = bArr;
            this.f5427b = i + i2;
            this.f5428c = i;
        }
    }

    /* renamed from: a */
    public int mo47080a() {
        if (this.f5428c >= this.f5427b) {
            return -1;
        }
        byte[] bArr = this.f5426a;
        int i = this.f5428c;
        this.f5428c = i + 1;
        return bArr[i] & 255;
    }
}
