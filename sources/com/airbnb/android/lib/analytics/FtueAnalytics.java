package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class FtueAnalytics {
    private static final String EVENT_APP_INTRO = "app_intro";
    private static final String EVENT_WHY_HOST = "why_host";
    public static final String IMPRESSION = "impression";
    private static final String PAGE_NUM = "page_num";

    protected static Strap makeParams(String event, String action) {
        return new Strap().mo11639kv("page", event).mo11639kv("action", action);
    }

    public static void trackAppIntroImpression() {
        AirbnbEventLogger.track(EVENT_APP_INTRO, makeParams(EVENT_APP_INTRO, "impression"));
    }

    public static void trackAppIntroSkip(int page) {
        AirbnbEventLogger.track(EVENT_APP_INTRO, makeParams(EVENT_APP_INTRO, "skip_click").mo11637kv(PAGE_NUM, page));
    }

    public static void trackAppIntroUp(int page) {
        AirbnbEventLogger.track(EVENT_APP_INTRO, makeParams(EVENT_APP_INTRO, "up_click").mo11637kv(PAGE_NUM, page));
    }

    public static void trackAppIntroSignin(int page) {
        AirbnbEventLogger.track(EVENT_APP_INTRO, makeParams(EVENT_APP_INTRO, "signin_click").mo11637kv(PAGE_NUM, page));
    }

    public static void trackAppIntroSignup(int page) {
        AirbnbEventLogger.track(EVENT_APP_INTRO, makeParams(EVENT_APP_INTRO, "signup_click").mo11637kv(PAGE_NUM, page));
    }

    public static void trackWhyHostImpression() {
        AirbnbEventLogger.track(EVENT_WHY_HOST, makeParams(EVENT_WHY_HOST, "impression"));
    }

    public static void trackWhyHostLYS() {
        AirbnbEventLogger.track(EVENT_WHY_HOST, makeParams(EVENT_WHY_HOST, "list_your_space_click"));
    }

    public static void trackWhyHostUp(int page) {
        AirbnbEventLogger.track(EVENT_WHY_HOST, makeParams(EVENT_WHY_HOST, "up_click").mo11637kv(PAGE_NUM, page));
    }
}
