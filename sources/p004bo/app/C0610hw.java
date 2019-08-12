package p004bo.app;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: bo.app.hw */
public abstract class C0610hw implements C0612hy {
    /* renamed from: a */
    public final C0607hu mo7289a(JSONObject jSONObject, JSONObject jSONObject2) {
        C0607hu huVar = new C0607hu();
        mo7297c("", jSONObject, jSONObject2, huVar);
        return huVar;
    }

    /* renamed from: a */
    public final C0607hu mo7288a(JSONArray jSONArray, JSONArray jSONArray2) {
        C0607hu huVar = new C0607hu();
        mo7298e("", jSONArray, jSONArray2, huVar);
        return huVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7291a(String str, JSONObject jSONObject, JSONObject jSONObject2, C0607hu huVar) {
        for (String str2 : C0613hz.m1117a(jSONObject2)) {
            if (!jSONObject.has(str2)) {
                huVar.mo7285b(str, str2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo7293b(String str, JSONObject jSONObject, JSONObject jSONObject2, C0607hu huVar) {
        for (String str2 : C0613hz.m1117a(jSONObject)) {
            Object obj = jSONObject.get(str2);
            if (jSONObject2.has(str2)) {
                mo7296a(C0613hz.m1112a(str, str2), obj, jSONObject2.get(str2), huVar);
            } else {
                huVar.mo7281a(str, str2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7290a(String str, JSONArray jSONArray, JSONArray jSONArray2, C0607hu huVar) {
        String a = C0613hz.m1114a(jSONArray);
        if (a == null || !C0613hz.m1119a(a, jSONArray2)) {
            mo7295d(str, jSONArray, jSONArray2, huVar);
            return;
        }
        Map a2 = C0613hz.m1116a(jSONArray, a);
        Map a3 = C0613hz.m1116a(jSONArray2, a);
        for (Object next : a2.keySet()) {
            if (!a3.containsKey(next)) {
                huVar.mo7281a(C0613hz.m1113a(str, a, next), a2.get(next));
            } else {
                mo7296a(C0613hz.m1113a(str, a, next), (JSONObject) a2.get(next), (JSONObject) a3.get(next), huVar);
            }
        }
        for (Object next2 : a3.keySet()) {
            if (!a2.containsKey(next2)) {
                huVar.mo7285b(C0613hz.m1113a(str, a, next2), a3.get(next2));
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo7292b(String str, JSONArray jSONArray, JSONArray jSONArray2, C0607hu huVar) {
        Map a = C0613hz.m1115a((Collection<T>) C0613hz.m1120b(jSONArray));
        Map a2 = C0613hz.m1115a((Collection<T>) C0613hz.m1120b(jSONArray2));
        for (Object next : a.keySet()) {
            if (!a2.containsKey(next)) {
                huVar.mo7281a(str + "[]", next);
            } else if (!((Integer) a2.get(next)).equals(a.get(next))) {
                huVar.mo7283a(str + "[]: Expected " + a.get(next) + " occurrence(s) of " + next + " but got " + a2.get(next) + " occurrence(s)");
            }
        }
        for (Object next2 : a2.keySet()) {
            if (!a.containsKey(next2)) {
                huVar.mo7285b(str + "[]", next2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo7294c(String str, JSONArray jSONArray, JSONArray jSONArray2, C0607hu huVar) {
        for (int i = 0; i < jSONArray.length(); i++) {
            mo7296a(str + "[" + i + "]", jSONArray.get(i), jSONArray2.get(i), huVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo7295d(String str, JSONArray jSONArray, JSONArray jSONArray2, C0607hu huVar) {
        boolean z;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray2.length()) {
                    z = false;
                    break;
                }
                Object obj2 = jSONArray2.get(i2);
                if (!hashSet.contains(Integer.valueOf(i2)) && obj2.getClass().equals(obj.getClass())) {
                    if (obj instanceof JSONObject) {
                        if (mo7289a((JSONObject) obj, (JSONObject) obj2).mo7284a()) {
                            hashSet.add(Integer.valueOf(i2));
                            z = true;
                            break;
                        }
                    } else if (obj instanceof JSONArray) {
                        if (mo7288a((JSONArray) obj, (JSONArray) obj2).mo7284a()) {
                            hashSet.add(Integer.valueOf(i2));
                            z = true;
                            break;
                        }
                    } else if (obj.equals(obj2)) {
                        hashSet.add(Integer.valueOf(i2));
                        z = true;
                        break;
                    }
                }
                i2++;
            }
            if (!z) {
                huVar.mo7283a(str + "[" + i + "] Could not find match for element " + obj);
                return;
            }
        }
    }
}
