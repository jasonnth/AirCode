package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ea */
public abstract class C0471ea implements C0467dx {

    /* renamed from: a */
    private static final String f424a = AppboyLogger.getAppboyLogTag(C0471ea.class);

    /* renamed from: b */
    private final String f425b;

    /* renamed from: c */
    private final C0489er f426c;

    /* renamed from: d */
    private final List<C0475ee> f427d = new ArrayList();

    /* renamed from: e */
    private boolean f428e;

    protected C0471ea(JSONObject jSONObject) {
        this.f425b = jSONObject.getString("id");
        this.f426c = new C0491et(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("trigger_condition");
        if (jSONArray != null && jSONArray.length() > 0) {
            this.f427d.addAll(C0515fo.m689a(jSONArray));
        }
        this.f428e = jSONObject.optBoolean("prefetch", true);
    }

    /* renamed from: a */
    public boolean mo7014a(C0495ex exVar) {
        if (!mo7022i()) {
            AppboyLogger.m1733d(f424a, "Triggered action " + this.f425b + "not eligible to be triggered by " + exVar.mo7048b() + " event. Current device time outside triggered action time window.");
            return false;
        }
        for (C0475ee a : this.f427d) {
            if (a.mo7026a(exVar)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo7013a() {
        return this.f428e;
    }

    /* renamed from: c */
    public C0489er mo7016c() {
        return this.f426c;
    }

    /* renamed from: b */
    public String mo7015b() {
        return this.f425b;
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = (JSONObject) this.f426c.forJsonPut();
            jSONObject.put("id", this.f425b);
            if (this.f427d == null) {
                return jSONObject;
            }
            JSONArray jSONArray = new JSONArray();
            for (C0475ee forJsonPut : this.f427d) {
                jSONArray.put(forJsonPut.forJsonPut());
            }
            jSONObject.put("trigger_condition", jSONArray);
            jSONObject.put("prefetch", this.f428e);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: i */
    public boolean mo7022i() {
        return mo7023j() && mo7024k();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public boolean mo7023j() {
        return this.f426c.mo7038a() == -1 || C0455dp.m515a() > this.f426c.mo7038a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public boolean mo7024k() {
        return this.f426c.mo7039b() == -1 || C0455dp.m515a() < this.f426c.mo7039b();
    }
}
