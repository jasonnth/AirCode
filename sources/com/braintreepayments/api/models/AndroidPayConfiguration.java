package com.braintreepayments.api.models;

import android.content.Context;
import com.braintreepayments.api.Json;
import com.google.android.gms.common.GoogleApiAvailability;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidPayConfiguration {
    private String mDisplayName;
    private boolean mEnabled;
    private String mEnvironment;
    private String mGoogleAuthorizationFingerprint;
    private String[] mSupportedNetworks;

    public static AndroidPayConfiguration fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        AndroidPayConfiguration androidPayConfiguration = new AndroidPayConfiguration();
        androidPayConfiguration.mEnabled = json.optBoolean("enabled", false);
        androidPayConfiguration.mGoogleAuthorizationFingerprint = Json.optString(json, "googleAuthorizationFingerprint", null);
        androidPayConfiguration.mEnvironment = Json.optString(json, "environment", null);
        androidPayConfiguration.mDisplayName = Json.optString(json, "displayName", "");
        JSONArray supportedNetworks = json.optJSONArray("supportedNetworks");
        if (supportedNetworks != null) {
            androidPayConfiguration.mSupportedNetworks = new String[supportedNetworks.length()];
            for (int i = 0; i < supportedNetworks.length(); i++) {
                try {
                    androidPayConfiguration.mSupportedNetworks[i] = supportedNetworks.getString(i);
                } catch (JSONException e) {
                }
            }
        } else {
            androidPayConfiguration.mSupportedNetworks = new String[0];
        }
        return androidPayConfiguration;
    }

    public boolean isEnabled(Context context) {
        try {
            return this.mEnabled && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
        } catch (NoClassDefFoundError e) {
            return false;
        }
    }

    public String getGoogleAuthorizationFingerprint() {
        return this.mGoogleAuthorizationFingerprint;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String[] getSupportedNetworks() {
        return this.mSupportedNetworks;
    }
}
