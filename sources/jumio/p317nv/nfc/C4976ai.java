package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.ai */
/* compiled from: ImageHeaderBox */
public final class C4976ai extends C4977aj {

    /* renamed from: a */
    long f4983a;

    /* renamed from: b */
    long f4984b;

    /* renamed from: c */
    int f4985c;

    /* renamed from: d */
    short f4986d;

    /* renamed from: e */
    short f4987e;

    /* renamed from: f */
    boolean f4988f;

    /* renamed from: g */
    boolean f4989g;

    static {
        f4991i = 69686472;
    }

    public C4976ai(C5065df dfVar, int i) throws IOException, C5144z {
        super(dfVar, i);
        mo46953a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo46953a() throws IOException {
        boolean z;
        boolean z2 = true;
        byte[] bArr = new byte[14];
        this.f4993k.mo47121a(this.f4996n);
        this.f4993k.mo47122a(bArr, 0, 14);
        this.f4983a = (long) C4981an.m3258e(bArr, 0);
        this.f4984b = (long) C4981an.m3258e(bArr, 4);
        this.f4985c = C4981an.m3257d(bArr, 8);
        this.f4986d = (short) (bArr[10] & 255);
        this.f4987e = (short) (bArr[11] & 255);
        if (bArr[12] == 0) {
            z = true;
        } else {
            z = false;
        }
        this.f4988f = z;
        if (bArr[13] != 1) {
            z2 = false;
        }
        this.f4989g = z2;
    }
}
