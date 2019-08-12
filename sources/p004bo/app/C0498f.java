package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* renamed from: bo.app.f */
final class C0498f implements C0431d {

    /* renamed from: a */
    private static final String f468a = AppboyLogger.getAppboyLogTag(C0498f.class);

    /* renamed from: b */
    private final C0431d f469b;

    public C0498f(C0431d dVar) {
        this.f469b = dVar;
    }

    /* renamed from: a */
    public JSONObject mo6883a(URI uri, Map<String, String> map) {
        m649b(uri, map);
        JSONObject a = this.f469b.mo6883a(uri, map);
        m648a(a);
        return a;
    }

    /* renamed from: a */
    public JSONObject mo6884a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        m650b(uri, map, jSONObject);
        JSONObject a = this.f469b.mo6884a(uri, map, jSONObject);
        m648a(a);
        return a;
    }

    /* renamed from: b */
    private void m649b(URI uri, Map<String, String> map) {
        try {
            AppboyLogger.m1733d(f468a, String.format("Making request to [%s], with headers: [%s]", new Object[]{uri.toString(), m647a(map)}));
        } catch (Exception e) {
            AppboyLogger.m1734d(f468a, "Exception while logging request: ", e);
        }
    }

    /* renamed from: b */
    private void m650b(URI uri, Map<String, String> map, JSONObject jSONObject) {
        try {
            AppboyLogger.m1733d(f468a, String.format("Making request to [%s], with headers: [%s] and JSON parameters: [%s]", new Object[]{uri.toString(), m647a(map), jSONObject.toString(2)}));
        } catch (Exception e) {
            AppboyLogger.m1734d(f468a, "Exception while logging request: ", e);
        }
    }

    /* renamed from: a */
    private void m648a(JSONObject jSONObject) {
        try {
            String str = f468a;
            String str2 = "Result [%s]";
            Object[] objArr = new Object[1];
            objArr[0] = jSONObject == null ? "none" : jSONObject.toString(2);
            AppboyLogger.m1733d(str, String.format(str2, objArr));
        } catch (Exception e) {
            AppboyLogger.m1734d(f468a, "Exception while logging result: ", e);
        }
    }

    /* renamed from: a */
    private String m647a(Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Entry entry : map.entrySet()) {
            if (!((String) entry.getKey()).equals("X-Appboy-Api-Key")) {
                arrayList.add(String.format("(%s / %s)", new Object[]{entry.getKey(), entry.getValue()}));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
            sb.append(", ");
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.substring(0, sb.length() - 2);
    }
}
