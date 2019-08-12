package com.braintreepayments.api.models;

import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class AnalyticsConfiguration {
    private String mUrl;

    public static AnalyticsConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        AnalyticsConfiguration analyticsConfiguration = new AnalyticsConfiguration();
        analyticsConfiguration.mUrl = Json.optString(json, "url", null);
        return analyticsConfiguration;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isEnabled() {
        return !TextUtils.isEmpty(this.mUrl);
    }
}
