package jumio.p317nv.nfc;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

/* renamed from: jumio.nv.nfc.af */
/* compiled from: ChannelDefinitionBox */
public final class C4973af extends C4977aj {

    /* renamed from: a */
    private int f4976a;

    /* renamed from: b */
    private Hashtable f4977b = new Hashtable();

    static {
        f4991i = 1667523942;
    }

    public C4973af(C5065df dfVar, int i) throws IOException, C5144z {
        super(dfVar, i);
        m3231a();
    }

    /* renamed from: a */
    private void m3231a() throws IOException {
        byte[] bArr = new byte[8];
        this.f4993k.mo47121a(this.f4996n);
        this.f4993k.mo47122a(bArr, 0, 2);
        this.f4976a = C4981an.m3257d(bArr, 0) & 65535;
        this.f4993k.mo47121a(this.f4996n + 2);
        for (int i = 0; i < this.f4976a; i++) {
            this.f4993k.mo47122a(bArr, 0, 6);
            C4981an.m3257d(bArr, 0);
            int[] iArr = {m3229a(bArr), m3232b(bArr), m3234c(bArr)};
            this.f4977b.put(new Integer(iArr[0]), iArr);
        }
    }

    /* renamed from: a */
    public int mo46948a(int i) {
        Enumeration keys = this.f4977b.keys();
        while (keys.hasMoreElements()) {
            int[] iArr = (int[]) this.f4977b.get(keys.nextElement());
            if (i == m3233b(iArr)) {
                return m3230a(iArr);
            }
        }
        return i;
    }

    /* renamed from: a */
    private int m3229a(byte[] bArr) {
        return C4981an.m3257d(bArr, 0);
    }

    /* renamed from: b */
    private int m3232b(byte[] bArr) {
        return C4981an.m3257d(bArr, 2);
    }

    /* renamed from: c */
    private int m3234c(byte[] bArr) {
        return C4981an.m3257d(bArr, 4);
    }

    /* renamed from: a */
    private int m3230a(int[] iArr) {
        return iArr[0];
    }

    /* renamed from: b */
    private int m3233b(int[] iArr) {
        return iArr[2];
    }
}
