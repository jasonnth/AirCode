package p004bo.app;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.el */
public final class C0483el implements C0475ee {
    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        return exVar instanceof C0502fd;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "test");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
