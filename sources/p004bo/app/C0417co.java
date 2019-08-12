package p004bo.app;

import org.json.JSONObject;

/* renamed from: bo.app.co */
public final class C0417co {

    /* renamed from: a */
    private final String f287a;

    public C0417co(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.f287a = jSONObject.optString("piqid", null);
        } else {
            this.f287a = null;
        }
    }

    /* renamed from: a */
    public boolean mo6912a() {
        return this.f287a != null;
    }

    /* renamed from: b */
    public String mo6913b() {
        return this.f287a;
    }
}
