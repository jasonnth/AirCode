package p004bo.app;

import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.eb */
public final class C0472eb implements C0475ee {

    /* renamed from: a */
    private String f429a;

    public C0472eb(JSONObject jSONObject) {
        this.f429a = jSONObject.getJSONObject("data").getString("event_name");
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (exVar instanceof C0494ew) {
            C0494ew ewVar = (C0494ew) exVar;
            if (!StringUtils.isNullOrBlank(ewVar.mo7047a()) && ewVar.mo7047a().equals(this.f429a)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "custom_event");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("event_name", this.f429a);
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
