package com.airbnb.android.core.analytics;

import android.content.Context;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;
import com.appboy.Appboy;
import com.appboy.models.outgoing.AppboyProperties;

public class AppboyAnalytics {
    public static final String LOGOUT = "logout";
    public static final String LOG_IN = "log_in";
    public static final String P2_IMPRESSION_WITH_DATES = "p2_impression_with_dates";
    public static final String P3_IMPRESSION = "p3_impression";
    public static final String P4_IMPRESSION = "p4_impression";
    public static final String SIGN_UP = "sign_up";
    public static final String TRAVEL_COUPON = "travel_coupon";

    private AppboyAnalytics() {
    }

    public static void logEvent(Context context, String eventName, Strap info) {
        if (!isKillSwitched()) {
            AppboyProperties eventProperties = new AppboyProperties();
            eventProperties.addProperty("key", "value");
            for (String key : info.keySet()) {
                eventProperties.addProperty(key, info.getString(key));
            }
            Appboy.getInstance(context).logCustomEvent(eventName, eventProperties);
        }
    }

    private static boolean isKillSwitched() {
        return Trebuchet.launch(TrebuchetKeys.DISABLE_APPBOY_TREBUCHET, false);
    }
}
