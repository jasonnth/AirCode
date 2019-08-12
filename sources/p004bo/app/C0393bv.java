package p004bo.app;

import com.appboy.models.ResponseError;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.bv */
public final class C0393bv {

    /* renamed from: a */
    private static final String f214a = AppboyLogger.getAppboyLogTag(C0393bv.class);

    /* renamed from: b */
    private final ResponseError f215b;

    public C0393bv(JSONObject jSONObject) {
        ResponseError responseError;
        JSONObject optJSONObject = jSONObject.optJSONObject("error");
        if (optJSONObject != null) {
            try {
                responseError = new ResponseError(optJSONObject);
            } catch (JSONException e) {
                AppboyLogger.m1740w(f214a, "Encountered exception processing ResponseError: " + optJSONObject.toString(), e);
            }
            this.f215b = responseError;
        }
        responseError = null;
        this.f215b = responseError;
    }

    /* renamed from: a */
    public ResponseError mo6856a() {
        return this.f215b;
    }
}
