package com.braintreepayments.api.models;

import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class PayPalConfiguration {
    private String mClientId;
    private String mCurrencyIsoCode;
    private String mDirectBaseUrl;
    private String mDisplayName;
    private String mEnvironment;
    private String mPrivacyUrl;
    private boolean mTouchDisabled;
    private boolean mUseBillingAgreement;
    private String mUserAgreementUrl;

    public static PayPalConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        PayPalConfiguration payPalConfiguration = new PayPalConfiguration();
        payPalConfiguration.mDisplayName = Json.optString(json, "displayName", null);
        payPalConfiguration.mClientId = Json.optString(json, "clientId", null);
        payPalConfiguration.mPrivacyUrl = Json.optString(json, "privacyUrl", null);
        payPalConfiguration.mUserAgreementUrl = Json.optString(json, "userAgreementUrl", null);
        payPalConfiguration.mDirectBaseUrl = Json.optString(json, "directBaseUrl", null);
        payPalConfiguration.mEnvironment = Json.optString(json, "environment", null);
        payPalConfiguration.mTouchDisabled = json.optBoolean("touchDisabled", true);
        payPalConfiguration.mCurrencyIsoCode = Json.optString(json, "currencyIsoCode", null);
        payPalConfiguration.mUseBillingAgreement = json.optBoolean("billingAgreementsEnabled", false);
        return payPalConfiguration;
    }

    public boolean isEnabled() {
        boolean enabled;
        if (TextUtils.isEmpty(this.mEnvironment) || TextUtils.isEmpty(this.mDisplayName) || TextUtils.isEmpty(this.mPrivacyUrl) || TextUtils.isEmpty(this.mUserAgreementUrl)) {
            enabled = false;
        } else {
            enabled = true;
        }
        if ("offline".equals(this.mEnvironment)) {
            return enabled;
        }
        if (!enabled || TextUtils.isEmpty(this.mClientId)) {
            return false;
        }
        return true;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getPrivacyUrl() {
        return this.mPrivacyUrl;
    }

    public String getUserAgreementUrl() {
        return this.mUserAgreementUrl;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getCurrencyIsoCode() {
        return this.mCurrencyIsoCode;
    }

    public boolean shouldUseBillingAgreement() {
        return this.mUseBillingAgreement;
    }
}
