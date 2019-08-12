package p004bo.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: bo.app.hz */
public final class C0613hz {

    /* renamed from: a */
    private static Integer f827a = new Integer(1);

    /* renamed from: a */
    public static Map<Object, JSONObject> m1116a(JSONArray jSONArray, String str) {
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= jSONArray.length()) {
                return hashMap;
            }
            JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
            hashMap.put(jSONObject.get(str), jSONObject);
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static String m1114a(JSONArray jSONArray) {
        for (String str : m1117a((JSONObject) jSONArray.get(0))) {
            if (m1119a(str, jSONArray)) {
                return str;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m1119a(String str, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (!(obj instanceof JSONObject)) {
                return false;
            }
            JSONObject jSONObject = (JSONObject) obj;
            if (!jSONObject.has(str)) {
                return false;
            }
            Object obj2 = jSONObject.get(str);
            if (!m1118a(obj2) || hashSet.contains(obj2)) {
                return false;
            }
            hashSet.add(obj2);
        }
        return true;
    }

    /* renamed from: b */
    public static List<Object> m1120b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.get(i));
        }
        return arrayList;
    }

    /* renamed from: c */
    public static boolean m1121c(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!m1118a(jSONArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m1118a(Object obj) {
        return !(obj instanceof JSONObject) && !(obj instanceof JSONArray);
    }

    /* renamed from: d */
    public static boolean m1122d(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!(jSONArray.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static Set<String> m1117a(JSONObject jSONObject) {
        TreeSet treeSet = new TreeSet();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            treeSet.add((String) keys.next());
        }
        return treeSet;
    }

    /* renamed from: a */
    public static String m1112a(String str, String str2) {
        return "".equals(str) ? str2 : str + "." + str2;
    }

    /* renamed from: a */
    public static String m1113a(String str, String str2, Object obj) {
        return str + "[" + str2 + "=" + obj + "]";
    }

    /* renamed from: a */
    public static <T> Map<T, Integer> m1115a(Collection<T> collection) {
        HashMap hashMap = new HashMap();
        for (Object next : collection) {
            Integer num = (Integer) hashMap.get(next);
            if (num == null) {
                hashMap.put(next, f827a);
            } else {
                hashMap.put(next, new Integer(num.intValue() + 1));
            }
        }
        return hashMap;
    }
}
