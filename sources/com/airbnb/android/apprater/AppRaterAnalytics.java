package com.airbnb.android.apprater;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppRaterAnalytics extends BaseAnalytics {
    public static final String DISMISS = "dismiss";
    private static final String EVENT_NAME = "china";
    public static final String RATE_APP = "rate_app";
    public static final String REJECT = "reject";
    public static final String REMIND_LATER = "remind_later";
    private static final String SECTION_NAME = "android_app_rater";

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.airbnb.android.apprater.AppRaterAnalytics$Operation */
    public @interface C0703Operation {
    }

    public static void trackDialogAction(String operation) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SECTION, SECTION_NAME).mo11639kv(BaseAnalytics.OPERATION, operation));
    }
}
