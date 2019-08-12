package com.airbnb.android.lib.cancellation;

import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CancellationAnalytics extends BaseAnalytics {
    public static final String EVENT_CANCELLATION = "android_reservation_cancellation";
    private static final String PARAM_CANCELLATION_REASON = "cancellation_reason";
    private static final String PARAM_ELEMENT = "element";
    private static final String PARAM_ELEMENT_SUBTYPE = "element_subtype";
    private static final String PARAM_POLICY = "policy";
    private static final String PARAM_RESERVATION_CDOE = "reservation_code";
    private static final String PARAM_USER_TYPE = "user_type";
    public static final String VALUE_ELEMENT_ASK_HOST_TO_CANCEL_BUTTON = "ask_host_to_cancel_button";
    public static final String VALUE_ELEMENT_CHANGE_RESERVATION_BUTTON = "alteration_button";
    public static final String VALUE_ELEMENT_NON_REFUNDABLE_ITEM = "non_refundable";
    public static final String VALUE_ELEMENT_POLICY_LINK = "policy_link";
    private static final String VALUE_GUEST = "guest";
    private static final String VALUE_HOST = "host";
    public static final String VALUE_PAGE_ASKED = "asked";
    public static final String VALUE_PAGE_DATES = "dates";
    public static final String VALUE_PAGE_EMERGENCY = "emergency";
    public static final String VALUE_PAGE_OTHER = "other";
    public static final String VALUE_PAGE_OTHER_INPUT = "other_input";
    public static final String VALUE_PAGE_REASON = "reason";
    public static final String VALUE_PAGE_SUMMARY = "summary";

    @Retention(RetentionPolicy.SOURCE)
    public @interface CancallationElement {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CancellationPage {
    }

    private static Strap makeParams(String page, CancellationData cancellationData) {
        Strap strap = Strap.make();
        strap.mo11639kv("page", page).mo11639kv(PARAM_RESERVATION_CDOE, cancellationData.confirmationCode()).mo11639kv(PARAM_USER_TYPE, cancellationData.isHost() ? "host" : "guest").mo11639kv(PARAM_POLICY, cancellationData.policyKey());
        if (cancellationData.cancellationReason() != null) {
            strap.mo11637kv(PARAM_CANCELLATION_REASON, cancellationData.cancellationReason().getReasonId());
        }
        return strap;
    }

    private static void track(Strap strap) {
        AirbnbEventLogger.track(EVENT_CANCELLATION, strap);
    }

    public static void trackPage(String page, CancellationData cancellationData) {
        track(makeParams(page, cancellationData));
    }

    public static void trackElementClick(String page, CancellationData cancellationData, String element, String elementSubtype) {
        Strap strap = makeParams(page, cancellationData);
        strap.mo11639kv("action", "click").mo11639kv(PARAM_ELEMENT, element);
        if (!TextUtils.isEmpty(elementSubtype)) {
            strap.mo11639kv(PARAM_ELEMENT_SUBTYPE, elementSubtype);
        }
        track(strap);
    }
}
