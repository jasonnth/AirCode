package com.braintreepayments.api.models;

import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class KountConfiguration {
    private String mKountMerchantId;

    public static KountConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        KountConfiguration kountConfiguration = new KountConfiguration();
        kountConfiguration.mKountMerchantId = Json.optString(json, "kountMerchantId", "");
        return kountConfiguration;
    }

    public boolean isEnabled() {
        return !TextUtils.isEmpty(this.mKountMerchantId);
    }

    public String getKountMerchantId() {
        return this.mKountMerchantId;
    }
}
