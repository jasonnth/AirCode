package com.airbnb.android.sms;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;

public class SMSMonitorAnalytics extends BaseAnalytics {
    private static final String EVENT_NAME = "china";
    private static final String OPERATION_CODE_EXTRACTED = "code_extracted";
    private static final String OPERATION_PERMISSION_DENIED = "permission_denied";
    private static final String OPERATION_PERMISSION_GRANTED = "permission_granted";
    private static final String OPERATION_PERMISSION_GRANT_FAILED = "permission_grant_failed";
    private static final String OPERATION_PERMISSION_GRANT_SUCCESS = "permission_grant_success";
    private static final String OPERATION_SMS_RECEIVED = "sms_received";
    private static final String OPERATION_START_LISTENING = "start_listening";
    private static final String SUBEVENT_NAME = "sms_monitor";

    public static void trackStart(String fragmentName) {
        trackSMSEvent(OPERATION_START_LISTENING, fragmentName);
    }

    public static void trackPermissionGranted(String fragmentName) {
        trackSMSEvent(OPERATION_PERMISSION_GRANTED, fragmentName);
    }

    public static void trackPermissionDenied(String fragmentName) {
        trackSMSEvent(OPERATION_PERMISSION_DENIED, fragmentName);
    }

    public static void trackPermissionGrantSuccess(String fragmentName) {
        trackSMSEvent(OPERATION_PERMISSION_GRANT_SUCCESS, fragmentName);
    }

    public static void trackPermissionGrantFailed(String fragmentName) {
        trackSMSEvent(OPERATION_PERMISSION_GRANT_FAILED, fragmentName);
    }

    public static void trackSMSReceived(String fragmentName) {
        trackSMSEvent(OPERATION_SMS_RECEIVED, fragmentName);
    }

    public static void trackCodeExtracted(String fragmentName) {
        trackSMSEvent(OPERATION_CODE_EXTRACTED, fragmentName);
    }

    private static void trackSMSEvent(String operation, String fragmentName) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.SUBEVENT, SUBEVENT_NAME).mo11639kv("page", fragmentName).mo11639kv(BaseAnalytics.OPERATION, operation));
    }
}
