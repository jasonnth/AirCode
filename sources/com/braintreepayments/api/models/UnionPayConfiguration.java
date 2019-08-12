package com.braintreepayments.api.models;

import org.json.JSONObject;

public class UnionPayConfiguration {
    private boolean mEnabled;

    static UnionPayConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        UnionPayConfiguration unionPayConfiguration = new UnionPayConfiguration();
        unionPayConfiguration.mEnabled = json.optBoolean("enabled", false);
        return unionPayConfiguration;
    }
}
