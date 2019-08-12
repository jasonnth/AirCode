package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class ProPhotoAnalytics {
    public static final String CANCEL_CLICK = "cancel_photography_click";
    public static final String CANCEL_CONFIRM = "cancel_request_click";
    public static final String CANCEL_REQ = "cancel_request";
    public static final String CANCEL_SUCCESS = "request_cancelled";
    public static final String CANCEL_VIEW = "cancel_request";
    public static final String CONFIRM_REQ = "confirm_request";
    public static final String FROM_EDIT_LISTING = "edit_listing";
    public static final String FROM_HOSPITALITY = "hospitality";
    public static final String GET_PHOTO = "get_photography";
    public static final String IMPRESSION = "impressions";
    public static final String LIMIT_VIEW = "limit_reached";
    public static final String MANAGE_PHOTO_REQUEST = "get_photography_click";
    public static final String MANAGE_PHOTO_VIEW = "photos_home";
    public static final String PHOTOS_HOME = "photos_home";
    public static final String PHOTO_ACT_VIEW = "get_photography";
    public static final String PRO_PHOTO_EVENT = "pro_photography";
    public static final String REQUEST_CLICK = "apply_now_click";
    public static final String REQUEST_CONFIRM = "submit_click";
    public static final String REQUEST_SUCCESS = "application_submitted";
    public static final String REQUEST_VIEW = "confirm_request";
    public static final String SEL_LISTING = "select_listing";
    public static final String SEL_LIST_CLICK = "select_listing_click";
    public static final String SEL_LIST_VIEW = "select_listing";

    public enum Origin {
        HOSPITALITY,
        EDIT_LISTING
    }

    public static void trackImpression(Origin origin, String impressionString) {
        AirbnbEventLogger.track(PRO_PHOTO_EVENT, makeStrap(origin).mo11639kv("c3", "impressions").mo11639kv(origin == Origin.HOSPITALITY ? "c4" : "c5", impressionString));
    }

    public static void trackClick(Origin origin, String typeString, String clickString) {
        AirbnbEventLogger.track(PRO_PHOTO_EVENT, makeStrap(origin).mo11639kv("c3", typeString).mo11639kv(origin == Origin.HOSPITALITY ? "c4" : "c5", clickString));
    }

    private static Strap makeStrap(Origin origin) {
        return Strap.make().mo11639kv("c1", PRO_PHOTO_EVENT).mo11639kv("c2", origin == Origin.EDIT_LISTING ? "edit_listing" : FROM_HOSPITALITY);
    }
}
