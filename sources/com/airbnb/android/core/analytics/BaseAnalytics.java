package com.airbnb.android.core.analytics;

import android.content.Context;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BaseAnalytics {
    public static final String ACTION = "action";
    public static final String CANCEL = "cancel";
    public static final String CLICK = "click";
    public static final String ERROR = "error";
    public static final String EVENT_DATA = "event_data";
    public static final String FAILURE = "failure";
    public static final String FROM = "from";
    public static final String IMPRESSION = "impression";
    private static final String IS_DLS = "is_dls";
    public static final String OPERATION = "operation";
    public static final String PAGE = "page";
    public static final String SECTION = "section";
    public static final String SELECT = "select";
    public static final String SUBEVENT = "subevent";
    public static final String SUBMIT = "submit";
    public static final String SUCCESS = "success";
    public static final String SWIPE_LEFT = "swipe_left";
    public static final String SWIPE_RIGHT = "swipe_right";
    public static final String TARGET = "target";
    public static final String UPDATE = "update";
    public static final String VALUE = "value";
    public static final String VIEW = "view";

    @Retention(RetentionPolicy.SOURCE)
    public @interface SwipeDirection {
    }

    public static String formatAirDateForLogging(AirDate date) {
        if (date == null) {
            return null;
        }
        return date.getIsoDateString();
    }

    public static Strap addDeviceType(Context context, Strap eventData) {
        String str = "device_type";
        if (context == null) {
            context = CoreApplication.appContext();
        }
        return eventData.mo11639kv(str, MiscUtils.isTabletScreen(context) ? "tablet" : "phone");
    }

    public static void addAppropriateDlsLogging(Strap parameters, NavigationModeType modeType) {
        if (modeType == NavigationModeType.GuestOnly) {
            parameters.mo11640kv(IS_DLS, true);
        }
    }
}
