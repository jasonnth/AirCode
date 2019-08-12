package jumio.p317nv.nfc;

import java.io.IOException;
import jumio.p317nv.nfc.C5140y.C5141a;
import jumio.p317nv.nfc.C5140y.C5143c;

/* renamed from: jumio.nv.nfc.ag */
/* compiled from: ColorSpecificationBox */
public final class C4974ag extends C4977aj {

    /* renamed from: a */
    private C5143c f4978a = null;

    /* renamed from: b */
    private C5141a f4979b = null;

    /* renamed from: c */
    private byte[] f4980c = null;

    static {
        f4991i = 1668246642;
    }

    public C4974ag(C5065df dfVar, int i) throws IOException, C5144z {
        super(dfVar, i);
        m3236d();
    }

    /* renamed from: d */
    private void m3236d() throws IOException, C5144z {
        byte[] bArr = new byte[256];
        this.f4993k.mo47121a(this.f4996n);
        this.f4993k.mo47122a(bArr, 0, 11);
        switch (bArr[0]) {
            case 1:
                this.f4978a = C5140y.f5719e;
                switch (C4981an.m3258e(bArr, 3)) {
                    case 16:
                        this.f4979b = C5140y.f5720f;
                        return;
                    case 17:
                        this.f4979b = C5140y.f5721g;
                        return;
                    case 18:
                        this.f4979b = C5140y.f5722h;
                        return;
                    default:
                        this.f4979b = C5140y.f5724j;
                        return;
                }
            case 2:
                this.f4978a = C5140y.f5718d;
                int e = C4981an.m3258e(bArr, 3);
                this.f4980c = new byte[e];
                this.f4993k.mo47121a(this.f4996n + 3);
                this.f4993k.mo47122a(this.f4980c, 0, e);
                return;
            default:
                throw new C5144z("Bad specification method (" + bArr[0] + ") in " + this);
        }
    }

    /* renamed from: a */
    public C5143c mo46949a() {
        return this.f4978a;
    }

    /* renamed from: b */
    public C5141a mo46950b() {
        return this.f4979b;
    }

    /* renamed from: c */
    public byte[] mo46951c() {
        return this.f4980c;
    }
}
