package p004bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: bo.app.hs */
public final class C0605hs {
    /* renamed from: a */
    private static C0612hy m1081a(C0606ht htVar) {
        return new C0611hx(htVar);
    }

    /* renamed from: a */
    public static C0607hu m1078a(String str, String str2, C0612hy hyVar) {
        Object a = C0608hv.m1094a(str);
        Object a2 = C0608hv.m1094a(str2);
        if ((a instanceof JSONObject) && (a2 instanceof JSONObject)) {
            return m1080a((JSONObject) a, (JSONObject) a2, hyVar);
        }
        if ((a instanceof JSONArray) && (a2 instanceof JSONArray)) {
            return m1079a((JSONArray) a, (JSONArray) a2, hyVar);
        }
        if ((a instanceof C0603hq) && (a2 instanceof C0603hq)) {
            return m1076a((C0603hq) a, (C0603hq) a2);
        }
        if (a instanceof JSONObject) {
            return new C0607hu().mo7282a("", a, a2);
        }
        return new C0607hu().mo7282a("", a, a2);
    }

    /* renamed from: a */
    public static C0607hu m1080a(JSONObject jSONObject, JSONObject jSONObject2, C0612hy hyVar) {
        return hyVar.mo7289a(jSONObject, jSONObject2);
    }

    /* renamed from: a */
    public static C0607hu m1079a(JSONArray jSONArray, JSONArray jSONArray2, C0612hy hyVar) {
        return hyVar.mo7288a(jSONArray, jSONArray2);
    }

    /* renamed from: a */
    public static C0607hu m1076a(C0603hq hqVar, C0603hq hqVar2) {
        C0607hu huVar = new C0607hu();
        if (!hqVar.mo7278a().equals(hqVar2.mo7278a())) {
            huVar.mo7283a("");
        }
        return huVar;
    }

    /* renamed from: a */
    public static C0607hu m1077a(String str, String str2, C0606ht htVar) {
        return m1078a(str, str2, m1081a(htVar));
    }
}
