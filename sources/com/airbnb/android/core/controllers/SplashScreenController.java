package com.airbnb.android.core.controllers;

import android.content.SharedPreferences;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;

public class SplashScreenController {
    public static final int EXPERIMENT_CONFIG_VERSION = 8;
    public static final String PREF_KEY_EXPERIMENT_CONFIG_VERSION = "pref_key_experiment_config_version";
    private int experimentConfigVersion = this.globalPreferences.getInt(PREF_KEY_EXPERIMENT_CONFIG_VERSION, 0);
    private final SharedPreferences globalPreferences;

    public SplashScreenController(AirbnbPreferences airbnbPreferences) {
        this.globalPreferences = airbnbPreferences.getGlobalSharedPreferences();
    }

    public boolean shouldShowSplashScreen() {
        if (!Trebuchet.launch(TrebuchetKeys.DISABLE_SPLASH_SCREEN, false) && this.experimentConfigVersion < 8) {
            return true;
        }
        return false;
    }

    public void onSplashScreenFinished(boolean configUpdated) {
        this.experimentConfigVersion = 8;
        if (configUpdated) {
            this.globalPreferences.edit().putInt(PREF_KEY_EXPERIMENT_CONFIG_VERSION, this.experimentConfigVersion).apply();
        }
    }
}
