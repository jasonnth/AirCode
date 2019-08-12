package jumio.p317nv.nfc;

import android.content.Context;
import android.nfc.NfcAdapter;
import java.security.cert.CertificateException;

/* renamed from: jumio.nv.nfc.e */
/* compiled from: NfcFeatureService */
public class C5086e {

    /* renamed from: a */
    private final String f5550a = "org.spongycastle.jcajce.provider.asymmetric.ElGamal";

    /* renamed from: b */
    private C5102h f5551b;

    /* renamed from: c */
    private Context f5552c;

    public C5086e(Context context) {
        try {
            this.f5552c = context;
            this.f5551b = new C5102h();
        } catch (CertificateException e) {
            this.f5551b = null;
        }
    }

    /* renamed from: a */
    public boolean mo47149a(String str) {
        return this.f5551b != null && this.f5551b.mo47166a(str);
    }

    /* renamed from: a */
    public boolean mo47148a() {
        return this.f5552c.getPackageManager().hasSystemFeature("android.hardware.nfc");
    }

    /* renamed from: b */
    public boolean mo47150b() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.f5552c);
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    /* renamed from: c */
    public C5102h mo47151c() {
        return this.f5551b;
    }
}
