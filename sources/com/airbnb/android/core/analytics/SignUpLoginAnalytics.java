package com.airbnb.android.core.analytics;

import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.utils.Strap;

public class SignUpLoginAnalytics {
    private static final String ERROR_MESSAGE_PARAM = "error_message";
    public static final String EVENT_SIGNUP_LOGIN = "signup_login_flow";
    private static final String LANDING = "landing";

    private static Strap makeParams(String page, String action, Strap additionalParams) {
        Strap params = Strap.make().mo11639kv("page", page).mo11639kv("action", action);
        if (additionalParams != null) {
            params.mix(additionalParams);
        }
        return params;
    }

    public static void trackAuthSuccess(AuthorizeService authorizeService) {
        trackLandingAction(authorizeService.name + "_auth_success");
    }

    public static void trackAuthFail(AuthorizeService authorizeService, String errorMessage) {
        trackLandingAction(authorizeService.name + "_auth_fail", TextUtils.isEmpty(errorMessage) ? null : Strap.make().mo11639kv("error_message", errorMessage));
    }

    public static void trackAuthCancel(AuthorizeService authorizeService) {
        trackLandingAction(authorizeService.name + "_auth_cancel");
    }

    public static void trackAuthDeny(AuthorizeService authorizeService) {
        trackLandingAction(authorizeService.name + "_auth_deny");
    }

    public static void trackLandingAction(String action, Strap extraParams) {
        AirbnbEventLogger.track(EVENT_SIGNUP_LOGIN, makeParams(LANDING, action, extraParams));
    }

    public static void trackLandingAction(String action) {
        trackLandingAction(action, null);
    }
}
