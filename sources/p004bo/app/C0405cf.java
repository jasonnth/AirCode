package p004bo.app;

import com.appboy.models.IPutIntoJson;
import org.json.JSONObject;

/* renamed from: bo.app.cf */
public class C0405cf implements C0387bp, IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private final JSONObject f266a;

    public C0405cf(JSONObject jSONObject) {
        this.f266a = jSONObject;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        return this.f266a;
    }

    /* renamed from: h */
    public boolean mo6822h() {
        return forJsonPut() == null || forJsonPut().length() == 0;
    }
}
