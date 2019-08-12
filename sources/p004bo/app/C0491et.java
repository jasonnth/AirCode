package p004bo.app;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.et */
public class C0491et implements C0489er {

    /* renamed from: a */
    private final long f449a;

    /* renamed from: b */
    private final long f450b;

    /* renamed from: c */
    private final int f451c;

    /* renamed from: d */
    private final int f452d;

    /* renamed from: e */
    private final int f453e;

    /* renamed from: f */
    private final C0488eq f454f;

    /* renamed from: g */
    private final int f455g;

    public C0491et(JSONObject jSONObject) {
        this.f449a = jSONObject.optLong("start_time", -1);
        this.f450b = jSONObject.optLong("end_time", -1);
        this.f451c = jSONObject.optInt("priority", 0);
        this.f455g = jSONObject.optInt("min_seconds_since_last_trigger", -1);
        this.f452d = jSONObject.optInt("delay", 0);
        this.f453e = jSONObject.optInt("timeout", -1);
        this.f454f = new C0490es(jSONObject);
    }

    /* renamed from: a */
    public long mo7038a() {
        return this.f449a;
    }

    /* renamed from: b */
    public long mo7039b() {
        return this.f450b;
    }

    /* renamed from: c */
    public int mo7040c() {
        return this.f451c;
    }

    /* renamed from: d */
    public int mo7041d() {
        return this.f452d;
    }

    /* renamed from: e */
    public int mo7042e() {
        return this.f453e;
    }

    /* renamed from: f */
    public C0488eq mo7043f() {
        return this.f454f;
    }

    /* renamed from: g */
    public int mo7044g() {
        return this.f455g;
    }

    /* renamed from: h */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = (JSONObject) this.f454f.forJsonPut();
            jSONObject.put("start_time", this.f449a);
            jSONObject.put("end_time", this.f450b);
            jSONObject.put("priority", this.f451c);
            jSONObject.put("min_seconds_since_last_trigger", this.f455g);
            jSONObject.put("timeout", this.f453e);
            jSONObject.put("delay", this.f452d);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
