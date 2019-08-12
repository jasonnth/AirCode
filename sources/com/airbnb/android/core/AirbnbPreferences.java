package com.airbnb.android.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.airbnb.android.utils.AirbnbConstants;

public class AirbnbPreferences {
    private final SharedPreferences globalPreferences;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences stringPreferences;

    public AirbnbPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(AirbnbConstants.AIRBNB_PREFS, 0);
        this.globalPreferences = context.getSharedPreferences(AirbnbConstants.AIRBNB_GLOBAL_PREFS, 0);
        this.stringPreferences = context.getSharedPreferences(AirbnbConstants.AIRBNB_STRING_PREFS, 0);
    }

    public SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public SharedPreferences getGlobalSharedPreferences() {
        return this.globalPreferences;
    }

    public SharedPreferences getStringPreferences() {
        return this.stringPreferences;
    }

    public SharedPreferences getDeviceOrUserPreferences() {
        return CoreApplication.instance().component().accountManager().isCurrentUserAuthorized() ? this.sharedPreferences : this.globalPreferences;
    }

    public void clearAll() {
        this.globalPreferences.edit().clear().apply();
        this.sharedPreferences.edit().clear().apply();
        this.stringPreferences.edit().clear().apply();
    }
}
