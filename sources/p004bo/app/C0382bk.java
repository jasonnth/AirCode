package p004bo.app;

import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.PushService;

/* renamed from: bo.app.bk */
public final class C0382bk {

    /* renamed from: a */
    private final C0392bu f182a;

    /* renamed from: b */
    private final C0393bv f183b;

    public C0382bk(JSONObject jSONObject, C0375bd bdVar) {
        JSONObject optJSONObject = jSONObject.optJSONObject("extras");
        if (optJSONObject != null) {
            this.f182a = new C0392bu(optJSONObject, bdVar);
        } else {
            this.f182a = null;
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(PushService.PARAM_RESULT);
        if (optJSONObject2 != null) {
            this.f183b = new C0393bv(optJSONObject2);
        } else {
            this.f183b = null;
        }
    }

    /* renamed from: a */
    public C0392bu mo6804a() {
        return this.f182a;
    }

    /* renamed from: b */
    public C0393bv mo6805b() {
        return this.f183b;
    }
}
