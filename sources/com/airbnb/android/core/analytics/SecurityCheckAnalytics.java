package com.airbnb.android.core.analytics;

import com.airbnb.android.apprater.AppRaterAnalytics;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class SecurityCheckAnalytics {
    public static final String EVENT = "security_check";
    public static final String PAGE = "page";
    public static final String PAGE_ADD_PAYOUT = "add_payout";
    public static final String PAGE_CONTACT_ENTER_CODE = "enter_code";
    public static final String PAGE_CONTACT_IMPRESSION = "contact";
    public static final String PAGE_CONTACT_SELECT_PHONE = "select_phone";
    public static final String PAGE_PRELIST = "prelist";
    public static final String PAGE_SELECT_ENTER_CODE = "enter_code";
    public static final String PAGE_SELECT_PHONE = "select_phone";

    private static Strap makeParams(String page, String operation, String section, Strap additionalParams) {
        Strap strap = new Strap().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.SECTION, section);
        if (additionalParams != null) {
            strap.mix(additionalParams);
        }
        return strap;
    }

    public static void trackPrelistPresent(Strap additionalParams) {
        trackAction(PAGE_PRELIST, "present", EVENT, additionalParams);
    }

    public static void trackPrelistDismiss(Strap additionalParams) {
        trackAction(PAGE_PRELIST, AppRaterAnalytics.DISMISS, EVENT, additionalParams);
    }

    public static void trackPayoutPresent(Strap additionalParams) {
        trackAction(PAGE_ADD_PAYOUT, "present", EVENT, additionalParams);
    }

    public static void trackPayoutDismiss(Strap additionalParams) {
        trackAction(PAGE_ADD_PAYOUT, AppRaterAnalytics.DISMISS, EVENT, additionalParams);
    }

    public static void trackSelectPhoneImpression(Strap additionalParams) {
        trackAction("select_phone", "impression", "", additionalParams);
    }

    public static void trackEnterCodeImpression(Strap additionalParams) {
        trackAction("enter_code", "impression", "", additionalParams);
    }

    public static void trackContactImpression(Strap additionalParams) {
        trackAction(PAGE_CONTACT_IMPRESSION, "impression", "", additionalParams);
    }

    public static void trackSelectPhoneCall(long id) {
        trackAction("select_phone", "click", "verify_via_call", Strap.make().mo11638kv("id", id));
    }

    public static void trackSelectSMS(long id) {
        trackAction("select_phone", "click", "verify_via_sms", Strap.make().mo11638kv("id", id));
    }

    public static void trackSubmitCode(String code) {
        trackAction("enter_code", "click", BaseAnalytics.SUBMIT, Strap.make().mo11637kv("code_length", code.length()));
    }

    public static void trackCodeResponse(boolean satisfied) {
        trackAction("enter_code", "response", "", Strap.make().mo11637kv("satisfied", satisfied ? 1 : 0));
    }

    private static void trackAction(String page, String operation, String section, Strap additionalParams) {
        AirbnbEventLogger.track(EVENT, makeParams(page, operation, section, additionalParams));
    }
}
