package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cb */
public class C0401cb implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f245a = AppboyLogger.getAppboyLogTag(C0401cb.class);

    /* renamed from: b */
    private final String f246b;

    /* renamed from: c */
    private final int f247c;

    /* renamed from: d */
    private final String f248d;

    /* renamed from: e */
    private final String f249e;

    /* renamed from: f */
    private final String f250f;

    public C0401cb(String str, int i, String str2, String str3, String str4) {
        this.f246b = str;
        this.f247c = i;
        this.f248d = str2;
        this.f249e = str3;
        this.f250f = str4;
    }

    /* renamed from: a */
    public boolean mo6887a() {
        return this.f250f != null;
    }

    /* renamed from: b */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_version", this.f246b);
            jSONObject.put("now", C0455dp.m522b());
            jSONObject.put("version_code", this.f247c);
            jSONObject.put("version_name", this.f248d);
            jSONObject.put("package_name", this.f249e);
            if (this.f250f != null) {
                jSONObject.put("config_time", this.f250f);
            }
            jSONObject.put("no_acks", true);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f245a, "Caught exception creating dispatch environment Json.", e);
        }
        return jSONObject;
    }
}
