package com.airbnb.android.utils;

import android.support.p000v4.util.ArrayMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class Strap extends ArrayMap<String, String> {
    public static Strap make() {
        return new Strap();
    }

    /* renamed from: kv */
    public Strap mo11639kv(String k, String v) {
        put(k, v);
        return this;
    }

    /* renamed from: kv */
    public Strap mo11638kv(String k, long v) {
        return mo11639kv(k, Long.toString(v));
    }

    /* renamed from: kv */
    public Strap mo11637kv(String k, int v) {
        return mo11639kv(k, Integer.toString(v));
    }

    /* renamed from: kv */
    public Strap mo11640kv(String k, boolean v) {
        return mo11639kv(k, Boolean.toString(v));
    }

    /* renamed from: kv */
    public Strap mo11636kv(String k, float v) {
        return mo11639kv(k, Float.toString(v));
    }

    /* renamed from: kv */
    public Strap mo11635kv(String k, double v) {
        return mo11639kv(k, Double.toString(v));
    }

    public Strap mix(Map<String, String> strap) {
        if (strap != null) {
            putAll(strap);
        }
        return this;
    }

    public Strap mix(Map<String, String> strap, boolean overrideFields) {
        if (strap != null) {
            for (Entry<String, String> entry : strap.entrySet()) {
                if (overrideFields || !containsKey(entry.getKey())) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
        return this;
    }

    public String getString(String key) {
        String value = (String) super.get(key);
        return value != null ? value : "";
    }

    public String getRawString(String key) {
        return (String) super.get(key);
    }

    public String toJsonString() {
        return new JSONObject(this).toString();
    }
}
