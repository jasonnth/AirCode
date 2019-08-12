package p004bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: bo.app.hx */
public class C0611hx extends C0610hw {

    /* renamed from: a */
    C0606ht f826a;

    public C0611hx(C0606ht htVar) {
        this.f826a = htVar;
    }

    /* renamed from: c */
    public void mo7297c(String str, JSONObject jSONObject, JSONObject jSONObject2, C0607hu huVar) {
        mo7293b(str, jSONObject, jSONObject2, huVar);
        if (!this.f826a.mo7279a()) {
            mo7291a(str, jSONObject, jSONObject2, huVar);
        }
    }

    /* renamed from: a */
    public void mo7296a(String str, Object obj, Object obj2, C0607hu huVar) {
        if (!(obj instanceof Number) || !(obj2 instanceof Number)) {
            if (!obj.getClass().isAssignableFrom(obj2.getClass())) {
                huVar.mo7282a(str, obj, obj2);
            } else if (obj instanceof JSONArray) {
                mo7298e(str, (JSONArray) obj, (JSONArray) obj2, huVar);
            } else if (obj instanceof JSONObject) {
                mo7297c(str, (JSONObject) obj, (JSONObject) obj2, huVar);
            } else if (!obj.equals(obj2)) {
                huVar.mo7282a(str, obj, obj2);
            }
        } else if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
            huVar.mo7282a(str, obj, obj2);
        }
    }

    /* renamed from: e */
    public void mo7298e(String str, JSONArray jSONArray, JSONArray jSONArray2, C0607hu huVar) {
        if (jSONArray.length() != jSONArray2.length()) {
            huVar.mo7283a(str + "[]: Expected " + jSONArray.length() + " values but got " + jSONArray2.length());
        } else if (jSONArray.length() == 0) {
        } else {
            if (this.f826a.mo7280b()) {
                mo7294c(str, jSONArray, jSONArray2, huVar);
            } else if (C0613hz.m1121c(jSONArray)) {
                mo7292b(str, jSONArray, jSONArray2, huVar);
            } else if (C0613hz.m1122d(jSONArray)) {
                mo7290a(str, jSONArray, jSONArray2, huVar);
            } else {
                mo7295d(str, jSONArray, jSONArray2, huVar);
            }
        }
    }
}
