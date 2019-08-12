package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.em */
public abstract class C0484em implements C0475ee {

    /* renamed from: b */
    private static final String f444b = AppboyLogger.getAppboyLogTag(C0484em.class);

    /* renamed from: a */
    C0485en f445a;

    public C0484em(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("property_filters");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(new C0478eh(jSONArray2.getJSONObject(i2)));
            }
            arrayList.add(new C0487ep(arrayList2));
        }
        this.f445a = new C0485en(arrayList);
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        return this.f445a.mo7026a(exVar);
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("property_filters", this.f445a.forJsonPut());
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            AppboyLogger.m1736e(f444b, "Caught exception creating Json.", e);
        }
        return jSONObject;
    }
}
