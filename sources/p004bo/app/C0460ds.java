package p004bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ds */
public final class C0460ds extends JSONObject {

    /* renamed from: a */
    private static final String f387a = AppboyLogger.getAppboyLogTag(C0460ds.class);

    /* renamed from: a */
    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum m527a(JSONObject jSONObject, String str, Class<TargetEnum> cls) {
        return Enum.valueOf(cls, jSONObject.getString(str).toUpperCase(Locale.US));
    }

    /* renamed from: a */
    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum m528a(JSONObject jSONObject, String str, Class<TargetEnum> cls, TargetEnum targetenum) {
        try {
            return m527a(jSONObject, str, cls);
        } catch (Exception e) {
            return targetenum;
        }
    }

    /* renamed from: a */
    public static <T> JSONArray m531a(Collection<? extends IPutIntoJson<T>> collection) {
        JSONArray jSONArray = new JSONArray();
        for (IPutIntoJson forJsonPut : collection) {
            jSONArray.put(forJsonPut.forJsonPut());
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static String m529a(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    /* renamed from: a */
    public static Map<String, String> m530a(JSONObject jSONObject, Map<String, String> map) {
        if (jSONObject != null) {
            map = new HashMap<>();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                map.put(str, jSONObject.getString(str));
            }
        } else if (map == null) {
            String str2 = "Cannot convert JSONObject to Map because JSONObject is null and no default was provided.";
            AppboyLogger.m1733d(f387a, str2);
            throw new JSONException(str2);
        } else {
            AppboyLogger.m1733d(f387a, "Cannot convert JSONObject to Map because JSONObject is null. Returning default Map.");
        }
        return map;
    }

    /* renamed from: a */
    public static JSONObject m532a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject3.put(str, jSONObject.get(str));
            }
            Iterator keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String str2 = (String) keys2.next();
                jSONObject3.put(str2, jSONObject2.get(str2));
            }
            return jSONObject3;
        } catch (JSONException e) {
            AppboyLogger.m1736e(f387a, "Caught exception merging Json objects.", e);
            return null;
        }
    }
}
