package com.airbnb.android.booking.fragments.redirectpay;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;

public class RedirectPayAnalytics extends BaseAnalytics {
    public static final String APP_NAME_ALIPAY = "alipay";
    private static final String EVENT_NAME = "app_redirect_payment";
    private static final String PARAM_APP_NAME = "app_name";
    private static final String PARAM_QUERIED_TIMES = "query_times";
    private static final String SECTION_QUERY_FAIL = "query_fail";
    private static final String SECTION_QUERY_START = "query_start";
    private static final String SECTION_QUERY_SUCCESS = "query_success";
    private static final String SECTION_REDIRECT_FAIL = "open_fail";
    private static final String SECTION_REDIRECT_SUCCESS = "open_success";

    public static void trackRedirectSuccess(String appName) {
        trackEvent(appName, SECTION_REDIRECT_SUCCESS, null);
    }

    public static void trackRedirectFail(String appName) {
        trackEvent(appName, SECTION_REDIRECT_FAIL, null);
    }

    public static void trackQueryStart(String appName) {
        trackEvent(appName, SECTION_QUERY_START, null);
    }

    public static void trackQuerySuccess(String appName, int queriedTimes) {
        trackEvent(appName, SECTION_QUERY_SUCCESS, Strap.make().mo11637kv(PARAM_QUERIED_TIMES, queriedTimes));
    }

    public static void trackQueryFail(String appName, int queriedTimes) {
        trackEvent(appName, SECTION_QUERY_FAIL, Strap.make().mo11637kv(PARAM_QUERIED_TIMES, queriedTimes));
    }

    private static void trackEvent(String appName, String section, Strap extra) {
        if (extra == null) {
            extra = Strap.make();
        }
        AirbnbEventLogger.track(EVENT_NAME, extra.mo11639kv(BaseAnalytics.SECTION, section).mo11639kv("app_name", appName));
    }
}
