package p004bo.app;

import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ek */
public final class C0482ek implements C0475ee {

    /* renamed from: a */
    private String f443a;

    C0482ek() {
    }

    public C0482ek(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("campaign_id")) {
            this.f443a = optJSONObject.optString("campaign_id", null);
        }
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (exVar instanceof C0501fc) {
            if (StringUtils.isNullOrBlank(this.f443a)) {
                return true;
            }
            C0501fc fcVar = (C0501fc) exVar;
            if (!StringUtils.isNullOrBlank(fcVar.mo7056a()) && fcVar.mo7056a().equals(this.f443a)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "push_click");
            if (this.f443a == null) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("campaign_id", this.f443a);
            jSONObject.putOpt("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
