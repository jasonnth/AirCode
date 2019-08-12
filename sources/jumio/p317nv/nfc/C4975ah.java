package jumio.p317nv.nfc;

import java.io.IOException;
import java.util.Vector;

/* renamed from: jumio.nv.nfc.ah */
/* compiled from: ComponentMappingBox */
public final class C4975ah extends C4977aj {

    /* renamed from: a */
    private int f4981a;

    /* renamed from: b */
    private Vector f4982b = new Vector();

    static {
        f4991i = 1668112752;
    }

    public C4975ah(C5065df dfVar, int i) throws IOException, C5144z {
        super(dfVar, i);
        mo46952a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo46952a() throws IOException {
        this.f4981a = (this.f4995m - this.f4996n) / 4;
        this.f4993k.mo47121a(this.f4996n);
        for (int i = this.f4996n; i < this.f4995m; i += 4) {
            byte[] bArr = new byte[4];
            this.f4993k.mo47122a(bArr, 0, 4);
            this.f4982b.addElement(bArr);
        }
    }
}
