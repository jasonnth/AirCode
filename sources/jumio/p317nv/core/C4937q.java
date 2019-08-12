package jumio.p317nv.core;

import android.content.Context;
import com.jumio.p311nv.extraction.NfcController;

/* renamed from: jumio.nv.core.q */
/* compiled from: NfcControllerFactory */
public class C4937q {
    /* renamed from: a */
    public static NfcController m3077a(Context context) {
        try {
            return (NfcController) Class.forName("com.jumio.nv.nfc.core.NfcControllerImpl").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        } catch (Exception e) {
            return null;
        }
    }
}
