package p004bo.app;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bw */
public final class C0394bw {

    /* renamed from: a */
    private long f216a;

    /* renamed from: b */
    private Set<String> f217b;

    /* renamed from: c */
    private Set<String> f218c;

    /* renamed from: d */
    private Set<String> f219d;

    /* renamed from: e */
    private boolean f220e = false;

    /* renamed from: f */
    private boolean f221f = false;

    /* renamed from: g */
    private long f222g = -1;

    /* renamed from: h */
    private float f223h = -1.0f;

    /* renamed from: i */
    private boolean f224i = false;

    public C0394bw() {
    }

    public C0394bw(JSONObject jSONObject) {
        this.f217b = m254a(jSONObject, "events_blacklist");
        this.f218c = m254a(jSONObject, "attributes_blacklist");
        this.f219d = m254a(jSONObject, "purchases_blacklist");
        this.f216a = jSONObject.optLong("time", 0);
        JSONObject optJSONObject = jSONObject.optJSONObject("location");
        if (optJSONObject != null) {
            try {
                this.f221f = optJSONObject.getBoolean("enabled");
                this.f220e = true;
            } catch (JSONException e) {
                this.f220e = false;
            }
            long optLong = optJSONObject.optLong("time", -1);
            if (optLong >= 0) {
                this.f222g = optLong * 1000;
            }
            this.f223h = (float) optJSONObject.optDouble("distance", -1.0d);
            this.f224i = optJSONObject.optBoolean("piq_enabled", false);
        }
    }

    /* renamed from: a */
    public boolean mo6861a() {
        return this.f224i;
    }

    /* renamed from: b */
    public long mo6862b() {
        return this.f216a;
    }

    /* renamed from: c */
    public Set<String> mo6866c() {
        return this.f217b;
    }

    /* renamed from: d */
    public Set<String> mo6869d() {
        return this.f218c;
    }

    /* renamed from: e */
    public Set<String> mo6870e() {
        return this.f219d;
    }

    /* renamed from: f */
    public boolean mo6871f() {
        return this.f221f;
    }

    /* renamed from: g */
    public boolean mo6872g() {
        return this.f220e;
    }

    /* renamed from: h */
    public long mo6873h() {
        return this.f222g;
    }

    /* renamed from: i */
    public float mo6874i() {
        return this.f223h;
    }

    /* renamed from: a */
    public void mo6860a(boolean z) {
        this.f224i = z;
    }

    /* renamed from: a */
    public void mo6858a(long j) {
        this.f216a = j;
    }

    /* renamed from: a */
    public void mo6859a(Set<String> set) {
        this.f217b = set;
    }

    /* renamed from: b */
    public void mo6864b(Set<String> set) {
        this.f218c = set;
    }

    /* renamed from: c */
    public void mo6867c(Set<String> set) {
        this.f219d = set;
    }

    /* renamed from: b */
    public void mo6865b(boolean z) {
        this.f221f = z;
    }

    /* renamed from: c */
    public void mo6868c(boolean z) {
        this.f220e = z;
    }

    /* renamed from: b */
    public void mo6863b(long j) {
        this.f222g = j;
    }

    /* renamed from: a */
    public void mo6857a(float f) {
        this.f223h = f;
    }

    /* renamed from: a */
    private Set<String> m254a(JSONObject jSONObject, String str) {
        if (jSONObject.optJSONArray(str) == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        for (int i = 0; i < optJSONArray.length(); i++) {
            hashSet.add(optJSONArray.getString(i));
        }
        return hashSet;
    }
}
