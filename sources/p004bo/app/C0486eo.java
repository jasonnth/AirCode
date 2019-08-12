package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.List;
import org.json.JSONArray;

/* renamed from: bo.app.eo */
public abstract class C0486eo implements C0474ed {

    /* renamed from: b */
    private static final String f446b = AppboyLogger.getAppboyLogTag(C0486eo.class);

    /* renamed from: a */
    protected List<C0474ed> f447a;

    protected C0486eo(List<C0474ed> list) {
        this.f447a = list;
    }

    /* renamed from: a */
    public JSONArray forJsonPut() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (C0474ed forJsonPut : this.f447a) {
                jSONArray.put(forJsonPut.forJsonPut());
            }
        } catch (Exception e) {
            AppboyLogger.m1736e(f446b, "Caught exception creating Json.", e);
        }
        return jSONArray;
    }
}
