package p004bo.app;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import com.amazon.device.messaging.development.ADMManifest;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.au */
public class C0361au {

    /* renamed from: c */
    private static final String f107c = AppboyLogger.getAppboyLogTag(C0361au.class);

    /* renamed from: a */
    private final Context f108a;

    /* renamed from: b */
    private final C0380bi f109b;

    public C0361au(Context context, C0380bi biVar) {
        this.f108a = context;
        this.f109b = biVar;
    }

    /* renamed from: a */
    public void mo6754a() {
        if (this.f109b.mo6801a() != null) {
            AppboyLogger.m1737i(f107c, "The device is already registered with the ADM server and is eligible to receive ADM messages.");
            AppboyLogger.m1737i(f107c, "ADM registration id: " + this.f109b.mo6801a());
            this.f109b.mo6802a(this.f109b.mo6801a());
            return;
        }
        ADM adm = new ADM(this.f108a);
        if (adm.isSupported()) {
            AppboyLogger.m1737i(f107c, "Registering with ADM server...");
            adm.startRegister();
        }
    }

    /* renamed from: a */
    public static boolean m73a(Context context) {
        return m74b() && m75b(context);
    }

    /* renamed from: b */
    private static boolean m74b() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (Exception e) {
            AppboyLogger.m1737i(f107c, "com.amazon.device.messaging.ADM not found");
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m75b(Context context) {
        try {
            ADMManifest.checkManifestAuthoredProperly(context);
            return true;
        } catch (Exception e) {
            AppboyLogger.m1737i(f107c, "Manifest not authored properly to support ADM.");
            AppboyLogger.m1738i(f107c, "ADM manifest exception: ", e);
            return false;
        }
    }
}
