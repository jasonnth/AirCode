package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cc */
public class C0402cc implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f251a = AppboyLogger.getAppboyLogTag(C0402cc.class);

    /* renamed from: b */
    private final int f252b;

    /* renamed from: c */
    private final int f253c;

    /* renamed from: d */
    private final float f254d;

    /* renamed from: e */
    private final float f255e;

    /* renamed from: f */
    private final int f256f;

    /* renamed from: a */
    public static C0402cc m326a(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("resolution_width", -1);
        int optInt2 = jSONObject.optInt("resolution_height", -1);
        float optDouble = (float) jSONObject.optDouble("x_dpi", -1.0d);
        float optDouble2 = (float) jSONObject.optDouble("y_dpi", -1.0d);
        int optInt3 = jSONObject.optInt("density_default", -1);
        if (optInt == -1 || optInt2 == -1 || optDouble == -1.0f || optDouble2 == -1.0f || optInt3 == -1) {
            return null;
        }
        return new C0402cc(optInt, optInt2, optDouble, optDouble2, optInt3);
    }

    public C0402cc(int i, int i2, float f, float f2, int i3) {
        this.f252b = i;
        this.f253c = i2;
        this.f254d = f;
        this.f255e = f2;
        this.f256f = i3;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resolution_height", this.f253c);
            jSONObject.put("resolution_width", this.f252b);
            jSONObject.put("x_dpi", (double) this.f254d);
            jSONObject.put("y_dpi", (double) this.f255e);
            jSONObject.put("density_default", this.f256f);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f251a, "Caught exception creating display Json.", e);
        }
        return jSONObject;
    }
}
