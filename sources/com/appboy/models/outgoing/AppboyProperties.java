package com.appboy.models.outgoing;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppboyProperties implements IPutIntoJson<JSONObject> {

    /* renamed from: a */
    private static final String f2887a = AppboyLogger.getAppboyLogTag(AppboyProperties.class);

    /* renamed from: b */
    private JSONObject f2888b = new JSONObject();

    public AppboyProperties() {
    }

    public AppboyProperties(JSONObject jsonObject) {
        this.f2888b = jsonObject;
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        for (String str : arrayList) {
            if (!m1720a(str)) {
                this.f2888b.remove(str);
            } else {
                try {
                    if (jsonObject.get(str) instanceof String) {
                        if (!m1721b(jsonObject.getString(str))) {
                            this.f2888b.remove(str);
                        }
                    } else if (jsonObject.get(str) == JSONObject.NULL) {
                        this.f2888b.remove(str);
                    }
                } catch (JSONException e) {
                    AppboyLogger.m1736e(f2887a, "Caught json exception validating property with key name: " + str, e);
                }
            }
        }
    }

    public AppboyProperties addProperty(String key, String value) {
        if (m1720a(key) && m1721b(value)) {
            try {
                this.f2888b.put(ValidationUtils.ensureAppboyFieldLength(key), ValidationUtils.ensureAppboyFieldLength(value));
            } catch (JSONException e) {
                AppboyLogger.m1736e(f2887a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public int size() {
        return this.f2888b.length();
    }

    /* renamed from: a */
    static boolean m1720a(String str) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m1739w(f2887a, "The Appboy property key cannot be null or contain only whitespaces. Not adding property.");
            return false;
        } else if (!str.startsWith("$")) {
            return true;
        } else {
            AppboyLogger.m1739w(f2887a, "The leading character in the key string may not be '$'. Not adding property.");
            return false;
        }
    }

    /* renamed from: b */
    static boolean m1721b(String str) {
        if (!StringUtils.isNullOrBlank(str)) {
            return true;
        }
        AppboyLogger.m1739w(f2887a, "The Appboy property value cannot be null or contain only whitespaces. Not adding property.");
        return false;
    }

    public JSONObject forJsonPut() {
        return this.f2888b;
    }
}
