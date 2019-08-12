package com.airbnb.android.core.superhero;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.utils.AirbnbConstants;

public class SuperHeroUtils {
    private SuperHeroUtils() {
    }

    public static void saveSuperHeroSetting(AirbnbPreferences airbnbPreferences, boolean enabled) {
        airbnbPreferences.getSharedPreferences().edit().putBoolean(AirbnbConstants.PREFS_ENABLE_SUPERHERO, enabled).apply();
    }

    public static boolean isSuperHeroEnabled(AirbnbPreferences airbnbPreferences) {
        if (!FeatureToggles.isSuperHeroEnabled() || !airbnbPreferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_ENABLE_SUPERHERO, true)) {
            return false;
        }
        return true;
    }
}
