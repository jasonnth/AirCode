package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Configuration {
    private AnalyticsConfiguration mAnalyticsConfiguration;
    private AndroidPayConfiguration mAndroidPayConfiguration;
    private CardConfiguration mCardConfiguration;
    private final Set<String> mChallenges = new HashSet();
    private String mClientApiUrl;
    private String mConfigurationString;
    private String mEnvironment;
    private KountConfiguration mKountConfiguration;
    private String mMerchantAccountId;
    private String mMerchantId;
    private PayPalConfiguration mPayPalConfiguration;
    private boolean mPaypalEnabled;
    private boolean mThreeDSecureEnabled;
    private UnionPayConfiguration mUnionPayConfiguration;
    private VenmoConfiguration mVenmoConfiguration;
    private VisaCheckoutConfiguration mVisaCheckoutConfiguration;

    public static Configuration fromJson(String configurationString) throws JSONException {
        return new Configuration(configurationString);
    }

    protected Configuration(String configurationString) throws JSONException {
        if (configurationString == null) {
            throw new JSONException("Configuration cannot be null");
        }
        this.mConfigurationString = configurationString;
        JSONObject json = new JSONObject(configurationString);
        this.mClientApiUrl = json.getString("clientApiUrl");
        parseJsonChallenges(json.optJSONArray("challenges"));
        this.mEnvironment = json.getString("environment");
        this.mMerchantId = json.getString("merchantId");
        this.mMerchantAccountId = Json.optString(json, "merchantAccountId", null);
        this.mAnalyticsConfiguration = AnalyticsConfiguration.fromJson(json.optJSONObject("analytics"));
        this.mCardConfiguration = CardConfiguration.fromJson(json.optJSONObject("creditCards"));
        this.mPaypalEnabled = json.optBoolean("paypalEnabled", false);
        this.mPayPalConfiguration = PayPalConfiguration.fromJson(json.optJSONObject("paypal"));
        this.mAndroidPayConfiguration = AndroidPayConfiguration.fromJson(json.optJSONObject("androidPay"));
        this.mThreeDSecureEnabled = json.optBoolean("threeDSecureEnabled", false);
        this.mVenmoConfiguration = VenmoConfiguration.fromJson(json.optJSONObject("payWithVenmo"));
        this.mKountConfiguration = KountConfiguration.fromJson(json.optJSONObject("kount"));
        this.mUnionPayConfiguration = UnionPayConfiguration.fromJson(json.optJSONObject("unionPay"));
        this.mVisaCheckoutConfiguration = VisaCheckoutConfiguration.fromJson(json.optJSONObject("visaCheckout"));
    }

    public String toJson() {
        return this.mConfigurationString;
    }

    public String getClientApiUrl() {
        return this.mClientApiUrl;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public boolean isPayPalEnabled() {
        return this.mPaypalEnabled && this.mPayPalConfiguration.isEnabled();
    }

    public PayPalConfiguration getPayPal() {
        return this.mPayPalConfiguration;
    }

    public AndroidPayConfiguration getAndroidPay() {
        return this.mAndroidPayConfiguration;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public AnalyticsConfiguration getAnalytics() {
        return this.mAnalyticsConfiguration;
    }

    public KountConfiguration getKount() {
        return this.mKountConfiguration;
    }

    private void parseJsonChallenges(JSONArray jsonArray) {
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                this.mChallenges.add(jsonArray.optString(i, ""));
            }
        }
    }
}
