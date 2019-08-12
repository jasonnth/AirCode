package com.airbnb.android.login.smartlock;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;
import com.google.android.gms.auth.api.credentials.Credential;

public class GoogleSmartLockAnalytics {
    private static final String ACTION_START = "start";
    private static final String CREDENTIAL_PARAM = "credential";
    private static final String EVENT_NAME = "google_smart_lock";
    private static final String OPERATION_DELETE_CREDENTIAL = "delete_credential";
    private static final String OPERATION_RESOLVE_CREDENTIAL = "resolve_credential";
    private static final String OPERATION_RESOLVE_SAVE_CREDENTIAL = "resolve_save_credential";
    private static final String OPERATION_SAVE_CREDENTIAL = "save_credential";
    private static final String OPERATON_REQUEST_CREDENTIAL = "request_credential";

    public static void trackRequestCredentialStart() {
        trackSmartLockEvent(OPERATON_REQUEST_CREDENTIAL, ACTION_START, null);
    }

    public static void trackRequestCredentialCancel() {
        trackSmartLockEvent(OPERATON_REQUEST_CREDENTIAL, BaseAnalytics.CANCEL, null);
    }

    public static void trackCredentialRetrieved(Credential credential) {
        trackSmartLockEvent(OPERATON_REQUEST_CREDENTIAL, "success", Strap.make().mo11639kv(CREDENTIAL_PARAM, getLoggingStringForCredential(credential)));
    }

    public static void trackResolveCredentialRequestStart() {
        trackSmartLockEvent(OPERATION_RESOLVE_CREDENTIAL, ACTION_START, null);
    }

    public static void trackResolveCredentialRequestFailed(String errorMessage) {
        trackSmartLockEvent(OPERATION_RESOLVE_CREDENTIAL, BaseAnalytics.FAILURE, Strap.make().mo11639kv("error", errorMessage));
    }

    public static void trackSaveCredentialAttempt(Credential credential) {
        trackSmartLockEvent(OPERATION_SAVE_CREDENTIAL, ACTION_START, Strap.make().mo11639kv(CREDENTIAL_PARAM, getLoggingStringForCredential(credential)));
    }

    public static void trackSaveCredentialSuccess(Credential credential) {
        trackSmartLockEvent(OPERATION_SAVE_CREDENTIAL, "success", Strap.make().mo11639kv(CREDENTIAL_PARAM, getLoggingStringForCredential(credential)));
    }

    public static void trackSaveCredentialFailed(String errorMessage) {
        trackSmartLockEvent(OPERATION_SAVE_CREDENTIAL, BaseAnalytics.FAILURE, Strap.make().mo11639kv("error", errorMessage));
    }

    public static void trackDeleteCredentialSuccess(Credential credential) {
        trackSmartLockEvent(OPERATION_DELETE_CREDENTIAL, "success", Strap.make().mo11639kv(CREDENTIAL_PARAM, getLoggingStringForCredential(credential)));
    }

    public static void trackDeleteCredentialFailed(String errorMessage) {
        trackSmartLockEvent(OPERATION_DELETE_CREDENTIAL, BaseAnalytics.FAILURE, Strap.make().mo11639kv("error", errorMessage));
    }

    public static void trackResolveSaveCredentialStart(Credential credential) {
        trackSmartLockEvent(OPERATION_RESOLVE_SAVE_CREDENTIAL, ACTION_START, Strap.make().mo11639kv(CREDENTIAL_PARAM, getLoggingStringForCredential(credential)));
    }

    public static void trackResolveSaveCredentialSuccess() {
        trackSmartLockEvent(OPERATION_RESOLVE_SAVE_CREDENTIAL, "success", null);
    }

    public static void trackResolveSaveCredentialCancel() {
        trackSmartLockEvent(OPERATION_RESOLVE_SAVE_CREDENTIAL, BaseAnalytics.CANCEL, null);
    }

    public static void trackResolveSaveCredentialFailed(String errorMessage) {
        trackSmartLockEvent(OPERATION_RESOLVE_SAVE_CREDENTIAL, BaseAnalytics.FAILURE, Strap.make().mo11639kv("error", errorMessage));
    }

    private static void trackSmartLockEvent(String operation, String action, Strap extraData) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("action", action).mix(extraData));
    }

    private static String getLoggingStringForCredential(Credential credential) {
        if (credential.getAccountType() == null) {
            return "Email";
        }
        return credential.getAccountType();
    }
}
