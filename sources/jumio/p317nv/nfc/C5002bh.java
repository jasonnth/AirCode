package jumio.p317nv.nfc;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.tls.CipherSuite;

/* renamed from: jumio.nv.nfc.bh */
/* compiled from: ICCTagTable */
public class C5002bh extends Hashtable {

    /* renamed from: a */
    private static final String f5088a = System.getProperty("line.separator");

    /* renamed from: b */
    private final Vector f5089b = new Vector();

    /* renamed from: c */
    private int f5090c;

    /* renamed from: jumio.nv.nfc.bh$a */
    /* compiled from: ICCTagTable */
    static class C5003a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f5091a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public int f5092b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f5093c;

        C5003a(int i, int i2, int i3) {
            this.f5091a = i;
            this.f5092b = i2;
            this.f5093c = i3;
        }
    }

    /* renamed from: a */
    public static C5002bh m3287a(byte[] bArr) {
        return new C5002bh(bArr);
    }

    protected C5002bh(byte[] bArr) {
        this.f5090c = C4981an.m3258e(bArr, 128);
        int i = CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA;
        for (int i2 = 0; i2 < this.f5090c; i2++) {
            this.f5089b.addElement(new C5003a(C4981an.m3258e(bArr, i), C4981an.m3258e(bArr, i + 4), C4981an.m3258e(bArr, i + 8)));
            i += 12;
        }
        Enumeration elements = this.f5089b.elements();
        while (elements.hasMoreElements()) {
            C5003a aVar = (C5003a) elements.nextElement();
            C5001bg a = C5001bg.m3286a(aVar.f5091a, bArr, aVar.f5092b, aVar.f5093c);
            put(new Integer(a.f5083e), a);
        }
    }
}
