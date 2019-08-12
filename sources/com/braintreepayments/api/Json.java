package com.braintreepayments.api;

import org.json.JSONObject;

public class Json {
    public static String optString(JSONObject json, String name, String fallback) {
        return json.isNull(name) ? fallback : json.optString(name, fallback);
    }
}
