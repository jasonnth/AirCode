package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;
import com.facebook.share.internal.ShareConstants;

public class HHWidgetAnalytics {
    private static final String ACTION_CLICK = "CLICK";
    private static final String ACTION_INSTALL = "INSTALL";
    private static final String ACTION_UNINSTALL = "UNINSTALL";
    private static final String ACTION_UPDATE = "UPDATE";
    private static final String PAGE = "HOST_HOME_WIDGET";
    private static final String PARAM_NUM_RESERVATIONS = "NUMBER_OF_RESERVATIONS";
    private static final String SECTION_CONFIRMED_RESERVATIONS = "CONFIRMED_RESERVATIONS";
    private static final String SECTION_GENERAL = "GENERAL";
    private static final String SECTION_REFRESH = "REFRESH";

    private static Strap makeParams(String page, String section, String action, Strap additionalParams) {
        Strap params = Strap.make().mo11639kv("PAGE", page).mo11639kv("SECTION", section).mo11639kv(ShareConstants.ACTION, action);
        if (additionalParams != null) {
            for (String key : additionalParams.keySet()) {
                if (additionalParams.get(key) != null) {
                    params.mo11639kv(key, (String) additionalParams.get(key));
                }
            }
        }
        return params;
    }

    public static void trackReservationClick() {
        AirbnbEventLogger.track(PAGE, makeParams(PAGE, SECTION_CONFIRMED_RESERVATIONS, ACTION_CLICK, null));
    }

    public static void trackRefreshClick() {
        AirbnbEventLogger.track(PAGE, makeParams(PAGE, SECTION_REFRESH, ACTION_CLICK, null));
    }

    public static void trackWidgetInstall() {
        AirbnbEventLogger.track(PAGE, makeParams(PAGE, SECTION_GENERAL, ACTION_INSTALL, null));
    }

    public static void trackWidgetUninstall() {
        AirbnbEventLogger.track(PAGE, makeParams(PAGE, SECTION_GENERAL, ACTION_UNINSTALL, null));
    }

    public static void trackWidgetUpdate(int numReservations) {
        AirbnbEventLogger.track(PAGE, makeParams(PAGE, SECTION_GENERAL, ACTION_UPDATE, new Strap().mo11639kv(PARAM_NUM_RESERVATIONS, String.valueOf(numReservations))));
    }
}
