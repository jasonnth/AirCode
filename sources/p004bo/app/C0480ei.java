package p004bo.app;

import com.appboy.support.StringUtils;
import com.mparticle.commerce.Product;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ei */
public final class C0480ei implements C0475ee {

    /* renamed from: a */
    String f440a;

    C0480ei() {
    }

    public C0480ei(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("product_id")) {
            this.f440a = optJSONObject.optString("product_id", null);
        }
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (exVar instanceof C0500fb) {
            if (StringUtils.isNullOrBlank(this.f440a)) {
                return true;
            }
            C0500fb fbVar = (C0500fb) exVar;
            if (!StringUtils.isNullOrBlank(fbVar.mo7055a()) && fbVar.mo7055a().equals(this.f440a)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Product.PURCHASE);
            if (this.f440a == null) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("product_id", this.f440a);
            jSONObject.putOpt("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
