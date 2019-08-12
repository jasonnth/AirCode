package p004bo.app;

import com.appboy.models.IInAppMessage;
import com.appboy.support.AppboyLogger;
import com.mparticle.commerce.Product;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.fo */
public final class C0515fo {

    /* renamed from: a */
    private static final String f506a = AppboyLogger.getAppboyLogTag(C0515fo.class);

    /* renamed from: a */
    public static IInAppMessage m688a(JSONObject jSONObject, C0375bd bdVar) {
        if (jSONObject == null) {
            try {
                AppboyLogger.m1733d(f506a, "Templated message Json was null. Not de-serializing templated message.");
                return null;
            } catch (JSONException e) {
                AppboyLogger.m1740w(f506a, "Encountered JSONException processing templated message: " + jSONObject, e);
                return null;
            } catch (Exception e2) {
                AppboyLogger.m1740w(f506a, "Encountered general exception processing templated message: " + jSONObject, e2);
                return null;
            }
        } else {
            String string = jSONObject.getString("type");
            if (string.equals("inapp")) {
                return C0458dr.m526a(jSONObject.getJSONObject("data"), bdVar);
            }
            AppboyLogger.m1739w(f506a, String.format("Received templated message Json with unknown type: %s. Not parsing.", new Object[]{string}));
            return null;
        }
    }

    /* renamed from: a */
    public static List<C0467dx> m690a(JSONArray jSONArray, C0375bd bdVar) {
        if (jSONArray == null) {
            try {
                AppboyLogger.m1733d(f506a, "Triggered actions Json array was null. Not de-serializing triggered actions.");
                return null;
            } catch (JSONException e) {
                AppboyLogger.m1740w(f506a, "Encountered JSONException processing triggered actions Json array: " + jSONArray, e);
                return null;
            } catch (Exception e2) {
                AppboyLogger.m1740w(f506a, "Failed to deserialize triggered actions Json array: " + jSONArray, e2);
                return null;
            }
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                C0467dx b = m691b(jSONArray.getJSONObject(i), bdVar);
                if (b != null) {
                    arrayList.add(b);
                }
            }
            return arrayList;
        }
    }

    /* renamed from: b */
    public static C0467dx m691b(JSONObject jSONObject, C0375bd bdVar) {
        C0467dx dxVar;
        try {
            String string = jSONObject.getString("type");
            if (string.equals("inapp")) {
                dxVar = new C0468dy(jSONObject, bdVar);
            } else if (string.equals("templated_iam")) {
                dxVar = new C0469dz(jSONObject, bdVar);
            } else {
                AppboyLogger.m1737i(f506a, "Received unknown trigger type: " + string);
                dxVar = null;
            }
            return dxVar;
        } catch (JSONException e) {
            AppboyLogger.m1740w(f506a, "Encountered JSONException processing triggered action Json: " + jSONObject, e);
            return null;
        } catch (Exception e2) {
            AppboyLogger.m1740w(f506a, "Failed to deserialize triggered action Json: " + jSONObject, e2);
            return null;
        }
    }

    /* renamed from: a */
    public static List<C0475ee> m689a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                AppboyLogger.m1739w(f506a, "Received null or blank trigger condition Json. Not parsing.");
            } else {
                String string = optJSONObject.getString("type");
                if (string.equals(Product.PURCHASE)) {
                    arrayList.add(new C0480ei(optJSONObject));
                } else if (string.equals("custom_event")) {
                    arrayList.add(new C0472eb(optJSONObject));
                } else if (string.equals("push_click")) {
                    arrayList.add(new C0482ek(optJSONObject));
                } else if (string.equals("open")) {
                    arrayList.add(new C0477eg());
                } else if (string.equals("iam_click")) {
                    arrayList.add(new C0476ef(optJSONObject));
                } else if (string.equals("test")) {
                    arrayList.add(new C0483el());
                } else if (string.equals("custom_event_property")) {
                    arrayList.add(new C0473ec(optJSONObject));
                } else if (string.equals("purchase_property")) {
                    arrayList.add(new C0481ej(optJSONObject));
                } else {
                    AppboyLogger.m1739w(f506a, String.format("Received triggered condition Json with unknown type: %s. Not parsing.", new Object[]{string}));
                }
            }
        }
        return arrayList;
    }
}
