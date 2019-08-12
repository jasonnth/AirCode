package p004bo.app;

import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.cd */
public final class C0403cd implements C0388bq {

    /* renamed from: a */
    private static final String f257a = AppboyLogger.getAppboyLogTag(C0403cd.class);

    /* renamed from: b */
    private final double f258b;

    /* renamed from: c */
    private final double f259c;

    /* renamed from: d */
    private final Double f260d;

    /* renamed from: e */
    private final Double f261e;

    public C0403cd(double d, double d2, Double d3, Double d4) {
        if (d > 90.0d || d < -90.0d || d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("Unable to create Location. Latitude and longitude values are bounded by ±90 and ±180 respectively");
        }
        this.f258b = d;
        this.f259c = d2;
        this.f260d = d3;
        this.f261e = d4;
    }

    /* renamed from: a */
    public boolean mo6890a() {
        return this.f260d != null;
    }

    /* renamed from: b */
    public boolean mo6891b() {
        return this.f261e != null;
    }

    /* renamed from: c */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitude", this.f258b);
            jSONObject.put("longitude", this.f259c);
            if (mo6890a()) {
                jSONObject.put("altitude", this.f260d);
            }
            if (mo6891b()) {
                jSONObject.put("ll_accuracy", this.f261e);
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f257a, "Caught exception creating location Json.", e);
        }
        return jSONObject;
    }
}
