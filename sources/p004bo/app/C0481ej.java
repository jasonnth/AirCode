package p004bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ej */
public final class C0481ej extends C0484em {

    /* renamed from: b */
    private static final String f441b = AppboyLogger.getAppboyLogTag(C0481ej.class);

    /* renamed from: c */
    private String f442c;

    public C0481ej(JSONObject jSONObject) {
        super(jSONObject);
        this.f442c = jSONObject.getJSONObject("data").getString("product_id");
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (exVar instanceof C0500fb) {
            if (StringUtils.isNullOrBlank(this.f442c)) {
                return false;
            }
            C0500fb fbVar = (C0500fb) exVar;
            if (!StringUtils.isNullOrBlank(fbVar.mo7055a()) && fbVar.mo7055a().equals(this.f442c)) {
                return super.mo7026a(exVar);
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject a = super.forJsonPut();
        try {
            a.put("type", "purchase_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put("product_id", this.f442c);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f441b, "Caught exception creating Json.", e);
        }
        return a;
    }
}
