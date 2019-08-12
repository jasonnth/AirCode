package p004bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ec */
public final class C0473ec extends C0484em {

    /* renamed from: b */
    private static final String f430b = AppboyLogger.getAppboyLogTag(C0473ec.class);

    /* renamed from: c */
    private String f431c;

    public C0473ec(JSONObject jSONObject) {
        super(jSONObject);
        this.f431c = jSONObject.getJSONObject("data").getString("event_name");
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (exVar instanceof C0494ew) {
            C0494ew ewVar = (C0494ew) exVar;
            if (!StringUtils.isNullOrBlank(ewVar.mo7047a()) && ewVar.mo7047a().equals(this.f431c)) {
                return super.mo7026a(exVar);
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject a = super.forJsonPut();
        try {
            a.put("type", "custom_event_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put("event_name", this.f431c);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f430b, "Caught exception creating CustomEventWithPropertiesTriggerCondition Json.", e);
        }
        return a;
    }
}
