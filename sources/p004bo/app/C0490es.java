package p004bo.app;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.es */
public class C0490es implements C0488eq {

    /* renamed from: a */
    private final int f448a;

    public C0490es(JSONObject jSONObject) {
        this.f448a = jSONObject.optInt("re_eligibility", -1);
    }

    /* renamed from: a */
    public boolean mo7035a() {
        return this.f448a == 0;
    }

    /* renamed from: b */
    public boolean mo7036b() {
        return this.f448a == -1;
    }

    /* renamed from: c */
    public Integer mo7037c() {
        if (this.f448a > 0) {
            return Integer.valueOf(this.f448a);
        }
        return null;
    }

    /* renamed from: d */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("re_eligibility", this.f448a);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
