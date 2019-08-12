package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.cl */
/* compiled from: ByteToBitInput */
public class C5044cl {

    /* renamed from: a */
    C5043ck f5429a;

    /* renamed from: b */
    int f5430b;

    /* renamed from: c */
    int f5431c = -1;

    public C5044cl(C5043ck ckVar) {
        this.f5429a = ckVar;
    }

    /* renamed from: a */
    public final int mo47082a() {
        if (this.f5431c < 0) {
            if ((this.f5430b & 255) != 255) {
                this.f5430b = this.f5429a.mo47080a();
                this.f5431c = 7;
            } else {
                this.f5430b = this.f5429a.mo47080a();
                this.f5431c = 6;
            }
        }
        int i = this.f5430b;
        int i2 = this.f5431c;
        this.f5431c = i2 - 1;
        return (i >> i2) & 1;
    }

    /* renamed from: b */
    public boolean mo47084b() {
        if (this.f5431c < 0 && (this.f5430b & 255) == 255) {
            this.f5430b = this.f5429a.mo47080a();
            this.f5431c = 6;
        }
        if (this.f5431c >= 0 && (this.f5430b & ((1 << (this.f5431c + 1)) - 1)) != (85 >> (7 - this.f5431c))) {
            return true;
        }
        if (this.f5430b != -1) {
            if (this.f5430b == 255 && this.f5431c == 0) {
                if ((this.f5429a.mo47080a() & 255) >= 128) {
                    return true;
                }
            } else if (this.f5429a.mo47080a() != -1) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo47083a(byte[] bArr, int i, int i2) {
        this.f5429a.mo47081a(bArr, i, i2);
        this.f5430b = 0;
        this.f5431c = -1;
    }
}
