package p004bo.app;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.eg */
public final class C0477eg implements C0475ee {
    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        return exVar instanceof C0499fa;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "open");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
