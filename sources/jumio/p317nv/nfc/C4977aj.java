package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.aj */
/* compiled from: JP2Box */
public abstract class C4977aj {

    /* renamed from: h */
    public static final String f4990h = System.getProperty("line.separator");

    /* renamed from: i */
    public static int f4991i;

    /* renamed from: j */
    public int f4992j;

    /* renamed from: k */
    protected C5065df f4993k;

    /* renamed from: l */
    protected int f4994l;

    /* renamed from: m */
    protected int f4995m;

    /* renamed from: n */
    protected int f4996n;

    public C4977aj(C5065df dfVar, int i) throws IOException, C5144z {
        byte[] bArr = new byte[16];
        this.f4993k = dfVar;
        this.f4994l = i;
        this.f4993k.mo47121a(this.f4994l);
        this.f4993k.mo47122a(bArr, 0, 8);
        this.f4996n = i + 8;
        this.f4992j = C4981an.m3258e(bArr, 0);
        this.f4995m = this.f4992j + i;
        if (this.f4992j == 1) {
            throw new C5144z("extended length boxes not supported");
        }
    }
}
