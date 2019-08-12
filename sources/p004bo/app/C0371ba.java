package p004bo.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.services.AppboyWearableListenerService;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ba */
public class C0371ba implements C0376be<C0406cg> {

    /* renamed from: a */
    private static final String f159a = AppboyLogger.getAppboyLogTag(C0371ba.class);

    /* renamed from: e */
    private static final String[] f160e = {"com.google.android.gms.common.api.GoogleApiClient", "com.google.android.gms.wearable.DataEvent", "com.google.android.gms.wearable.DataEventBuffer", "com.google.android.gms.wearable.DataItem", "com.google.android.gms.wearable.DataMap", "com.google.android.gms.wearable.DataMapItem", "com.google.android.gms.wearable.Wearable", "com.google.android.gms.wearable.WearableListenerService", "com.appboy.services.AppboyWearableListenerService"};

    /* renamed from: b */
    private final Context f161b;

    /* renamed from: c */
    private final boolean f162c = m139c();

    /* renamed from: d */
    private final SharedPreferences f163d;

    public C0371ba(Context context) {
        this.f161b = context.getApplicationContext();
        this.f163d = context.getSharedPreferences("com.appboy.managers.connected_device_storage", 0);
    }

    /* renamed from: c */
    private boolean m139c() {
        try {
            ClassLoader classLoader = C0371ba.class.getClassLoader();
            for (String cls : f160e) {
                if (Class.forName(cls, false, classLoader) == null) {
                    AppboyLogger.m1737i(f159a, "Required Google Play Services Wearable classes not found in path. Appboy wearable service not available.");
                    return false;
                }
            }
            if (C0462du.m536a(this.f161b, AppboyWearableListenerService.class)) {
                return true;
            }
            AppboyLogger.m1737i(f159a, "Appboy wearable service is not available. Declare <service android:name=\"com.appboy.services.AppboyWearableListenerService\"/> in your appboy.xml to enable Appboy wearable service. See the Droidboy manifest for an example");
            return false;
        } catch (Exception e) {
            AppboyLogger.m1733d(f159a, "Google Play Services Wearable library not found in path. Appboy wearable service not available.");
            return false;
        } catch (NoClassDefFoundError e2) {
            AppboyLogger.m1733d(f159a, "Google Play Services Wearable library not found in path. Appboy wearable service not available.");
            return false;
        } catch (Throwable th) {
            AppboyLogger.m1733d(f159a, "Google Play Services Wearable library not found in path. Appboy wearable service not available.");
            return false;
        }
    }

    /* renamed from: a */
    public synchronized List<C0406cg> mo6790a() {
        ArrayList arrayList;
        Editor edit = this.f163d.edit();
        arrayList = new ArrayList();
        for (String str : this.f163d.getAll().keySet()) {
            String string = this.f163d.getString(str, null);
            if (!StringUtils.isNullOrBlank(string)) {
                try {
                    arrayList.add(C0406cg.m338a(new JSONObject(string)));
                } catch (JSONException e) {
                    AppboyLogger.m1736e(f159a, "JSON error while pulling connected device from storage: " + string, e);
                    edit.remove(str);
                }
            }
        }
        edit.apply();
        return arrayList;
    }

    /* renamed from: a */
    public synchronized void mo6792a(C0406cg cgVar) {
        Editor edit = this.f163d.edit();
        edit.putString(cgVar.mo6898a(), cgVar.forJsonPut().toString());
        edit.apply();
    }

    /* renamed from: b */
    public void mo6793b() {
        if (!this.f162c) {
            AppboyLogger.m1733d(f159a, "Appboy wearable service not available. Appboy wearable service not started.");
            return;
        }
        AppboyLogger.m1737i(f159a, "Starting AppboyWearableListenerService.");
        this.f161b.startService(new Intent().setClass(this.f161b, AppboyWearableListenerService.class));
    }
}
