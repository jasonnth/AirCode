package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SupportPhoneNumberAnalytics extends BaseAnalytics {
    public static final String EVENT_SUPPORT_PHONE_NUMBER = "support_phone_numbers";
    public static final String PAGE_GUEST_CANCELLATION = "guest_cancel";
    public static final String PAGE_HELP_CENTER = "help_center";
    public static final String PAGE_HOST_CANCELLATION = "host_cancel";

    @Retention(RetentionPolicy.SOURCE)
    public @interface SupportPhoneNumberPage {
    }

    public static void trackImpression(String page) {
        AirbnbEventLogger.event().name(EVENT_SUPPORT_PHONE_NUMBER).mo8238kv("page", page).mo8238kv(BaseAnalytics.OPERATION, "impression").track();
    }
}
