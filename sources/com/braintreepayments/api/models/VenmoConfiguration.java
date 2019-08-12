package com.braintreepayments.api.models;

import android.net.Uri;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class VenmoConfiguration {
    private static final Uri VENMO_AUTHORITY_URI = Uri.parse("content://com.venmo.whitelistprovider");
    private String mAccessToken;
    private String mEnvironment;
    private String mMerchantId;

    static VenmoConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        VenmoConfiguration venmoConfiguration = new VenmoConfiguration();
        venmoConfiguration.mAccessToken = Json.optString(json, "accessToken", "");
        venmoConfiguration.mEnvironment = Json.optString(json, "environment", "");
        venmoConfiguration.mMerchantId = Json.optString(json, "merchantId", "");
        return venmoConfiguration;
    }
}
