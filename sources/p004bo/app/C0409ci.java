package p004bo.app;

import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ci */
public final class C0409ci extends C0402cc {

    /* renamed from: a */
    private static final String f276a = AppboyLogger.getAppboyLogTag(C0409ci.class);

    /* renamed from: b */
    private final String f277b;

    /* renamed from: b */
    public static C0409ci m344b(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("resolution_width", -1);
        int optInt2 = jSONObject.optInt("resolution_height", -1);
        float optDouble = (float) jSONObject.optDouble("x_dpi", -1.0d);
        float optDouble2 = (float) jSONObject.optDouble("y_dpi", -1.0d);
        int optInt3 = jSONObject.optInt("density_default", -1);
        String optString = jSONObject.optString("screen_type", null);
        if (optInt == -1 || optInt2 == -1 || optDouble == -1.0f || optDouble2 == -1.0f || optInt3 == -1) {
            return null;
        }
        return new C0409ci(optInt, optInt2, optDouble, optDouble2, optInt3, optString);
    }

    public C0409ci(int i, int i2, float f, float f2, int i3, String str) {
        super(i, i2, f, f2, i3);
        this.f277b = str;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject a = super.forJsonPut();
        try {
            if (this.f277b != null) {
                a.put("screen_type", this.f277b);
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f276a, "Caught exception creating wear display Json.", e);
        }
        return a;
    }
}
