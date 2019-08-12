package com.braintreepayments.api.models;

import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.PushService;

public class MetadataBuilder {
    private JSONObject mJson = new JSONObject();

    public MetadataBuilder() {
        try {
            this.mJson.put(PushService.PARAM_PLATFORM, "android");
        } catch (JSONException e) {
        }
    }

    public MetadataBuilder source(String source) {
        try {
            this.mJson.put("source", source);
        } catch (JSONException e) {
        }
        return this;
    }

    public MetadataBuilder integration(String integration) {
        try {
            this.mJson.put("integration", integration);
        } catch (JSONException e) {
        }
        return this;
    }

    public MetadataBuilder sessionId(String sessionId) {
        try {
            this.mJson.put("sessionId", sessionId);
        } catch (JSONException e) {
        }
        return this;
    }

    public MetadataBuilder version() {
        try {
            this.mJson.put("version", "2.5.2");
        } catch (JSONException e) {
        }
        return this;
    }

    public JSONObject build() {
        return this.mJson;
    }

    public String toString() {
        return this.mJson.toString();
    }
}
