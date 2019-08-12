package p004bo.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.hv */
public class C0608hv {
    /* renamed from: a */
    public static Object m1094a(final String str) {
        if (str.trim().startsWith("{")) {
            return new JSONObject(str);
        }
        if (str.trim().startsWith("[")) {
            return new JSONArray(str);
        }
        if (str.trim().startsWith("\"") || str.trim().matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
            return new C0603hq() {
                /* renamed from: a */
                public String mo7278a() {
                    return str;
                }
            };
        }
        throw new JSONException("Unparsable JSON string: " + str);
    }
}
