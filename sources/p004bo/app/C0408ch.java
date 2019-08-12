package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: bo.app.ch */
public class C0408ch implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f274a = AppboyLogger.getAppboyLogTag(C0408ch.class);

    /* renamed from: b */
    private final String f275b;

    /* renamed from: a */
    public static C0408ch m341a(JSONObject jSONObject) {
        return new C0408ch(StringUtils.emptyToNull(jSONObject.optString(JPushReportInterface.ANDROID_ID)));
    }

    public C0408ch(String str) {
        this.f275b = str;
    }

    /* renamed from: a */
    public String mo6900a() {
        return this.f275b;
    }

    /* renamed from: b */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(JPushReportInterface.ANDROID_ID, this.f275b);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f274a, "Caught exception creating wear device identifier Json.", e);
        }
        return jSONObject;
    }
}
