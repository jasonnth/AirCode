package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.utils.Strap;

public class EditProfileAnalytics {
    public static final String EDIT_PROFILE = "edit_profile";

    private static Strap makeParams(String page, String operation, String section, Strap additionalParams) {
        Strap strap = new Strap().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.SECTION, section);
        if (additionalParams != null) {
            strap.mix(additionalParams);
        }
        return strap;
    }

    public static void trackAction(String operation, String section, Strap additionalParams) {
        AirbnbEventLogger.track(EDIT_PROFILE, makeParams(EDIT_PROFILE, operation, section, additionalParams));
    }

    public static void trackTextSectionAction(ProfileSection section, String action, Strap additionalParams) {
        switch (section) {
            case About:
                trackAction(action, "about_me", additionalParams);
                return;
            case Email:
                trackAction(action, "email", additionalParams);
                return;
            case Live:
                trackAction(action, "location", additionalParams);
                return;
            case School:
                trackAction(action, "school", additionalParams);
                return;
            case Work:
                trackAction(action, "work", additionalParams);
                return;
            case Name:
                trackAction(action, "name", additionalParams);
                return;
            default:
                return;
        }
    }
}
