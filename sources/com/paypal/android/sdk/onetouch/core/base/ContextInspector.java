package com.paypal.android.sdk.onetouch.core.base;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextInspector {
    private final Context mContext;
    private final SharedPreferences mPreferences = this.mContext.getSharedPreferences("PayPalOTC", 0);

    public ContextInspector(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public String getStringPreference(String key) {
        return this.mPreferences.getString(key, null);
    }

    public long getLongPreference(String key, long defaultValue) {
        return this.mPreferences.getLong(key, defaultValue);
    }

    public boolean getBooleanPreference(String key, boolean defaultValue) {
        return this.mPreferences.getBoolean(key, defaultValue);
    }

    public void setPreference(String key, String value) {
        this.mPreferences.edit().putString(key, value).apply();
    }

    public void setPreference(String key, long value) {
        this.mPreferences.edit().putLong(key, value).apply();
    }

    public void setPreference(String key, boolean value) {
        this.mPreferences.edit().putBoolean(key, value).apply();
    }

    public Context getContext() {
        return this.mContext;
    }
}
