package p004bo.app;

import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ef */
public final class C0476ef implements C0475ee {

    /* renamed from: a */
    private String f432a;

    /* renamed from: b */
    private Set<String> f433b = new HashSet();

    public C0476ef(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.f432a = jSONObject2.getString("id");
        JSONArray optJSONArray = jSONObject2.optJSONArray("buttons");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.f433b.add(optJSONArray.getString(i));
            }
        }
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        if (!(exVar instanceof C0497ez)) {
            return false;
        }
        C0497ez ezVar = (C0497ez) exVar;
        if (StringUtils.isNullOrBlank(ezVar.mo7053a()) || !ezVar.mo7053a().equals(this.f432a)) {
            return false;
        }
        if (this.f433b.size() <= 0) {
            return StringUtils.isNullOrBlank(ezVar.mo7054f());
        }
        if (StringUtils.isNullOrBlank(ezVar.mo7054f()) || !this.f433b.contains(ezVar.mo7054f())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "iam_click");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", this.f432a);
            if (this.f433b.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.f433b) {
                    jSONArray.put(put);
                }
                jSONObject2.put("buttons", jSONArray);
            }
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
