package com.facebook.accountkit.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.accountkit.AccountKitError;
import java.security.InvalidParameterException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

final class InternalLogger {
    public static final String EVENT_INVALID_UI_MANAGER = "ak_ui_manager_invalid";
    public static final String EVENT_NAME_ACCOUNT_VERIFIED_VIEW = "ak_account_verified_view";
    public static final String EVENT_NAME_CONFIRMATION_CODE_VIEW = "ak_confirmation_code_view";
    public static final String EVENT_NAME_CONFIRM_ACCOUNT_VERIFIED_VIEW = "ak_confirm_account_verified_view";
    public static final String EVENT_NAME_CONFIRM_SEAMLESS_PENDING = "ak_seamless_pending";
    public static final String EVENT_NAME_COUNTRY_CODE_VIEW = "ak_country_code_view";
    public static final String EVENT_NAME_CUSTOM_VIEW = "ak_custom_view";
    public static final String EVENT_NAME_EMAIL_VERIFY_VIEW = "ak_email_sent_view";
    public static final String EVENT_NAME_EMAIL_VIEW = "ak_email_login_view";
    public static final String EVENT_NAME_ERROR_VIEW = "ak_error_view";
    public static final String EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN = "ak_fetch_seamless_login_token";
    public static final String EVENT_NAME_LOGIN_COMPLETE = "ak_login_complete";
    public static final String EVENT_NAME_LOGIN_START = "ak_login_start";
    public static final String EVENT_NAME_LOGIN_VERIFY = "ak_login_verify";
    public static final String EVENT_NAME_LOG_OUT = "ak_log_out";
    public static final String EVENT_NAME_LOG_OUT_ERROR = "ak_log_out_error";
    public static final String EVENT_NAME_PHONE_NUMBER_VIEW = "ak_phone_login_view";
    public static final String EVENT_NAME_RESEND_VIEW = "ak_resend_view";
    public static final String EVENT_NAME_SDK_START = "ak_sdk_init";
    public static final String EVENT_NAME_SENDING_CODE_VIEW = "ak_sending_code_view";
    public static final String EVENT_NAME_SENT_CODE_VIEW = "ak_sent_code_view";
    public static final String EVENT_NAME_SET_CONFIRMATION_CODE = "ak_confirmation_code_set";
    public static final String EVENT_NAME_UI_MANAGER_VIEW = "ak_ui_manager_view";
    public static final String EVENT_NAME_VERIFIED_CODE_VIEW = "ak_verified_code_view";
    public static final String EVENT_NAME_VERIFYING_CODE_VIEW = "ak_verifying_code_view";
    private static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_logger_ref";
    private static final String EVENT_PARAM_COUNTRY_CODE = "9_country_code";
    private static final String EVENT_PARAM_ERROR_CODE = "5_error_code";
    private static final String EVENT_PARAM_ERROR_MESSAGE = "6_error_message";
    private static final String EVENT_PARAM_EXTRAS = "7_extras";
    public static final String EVENT_PARAM_EXTRAS_BUTTON_CLICKED_TYPE = "button_type";
    public static final String EVENT_PARAM_EXTRAS_COMPLETED = "completed";
    public static final String EVENT_PARAM_EXTRAS_CONFIRMATION_CODE = "confirmation_code";
    public static final String EVENT_PARAM_EXTRAS_COUNTRY_CODE = "country_code";
    public static final String EVENT_PARAM_EXTRAS_COUNTRY_CODE_SOURCE = "country_code_source";
    public static final String EVENT_PARAM_EXTRAS_CUSTOM_VIEW_PROVIDED = "view_provided";
    public static final String EVENT_PARAM_EXTRAS_CUSTOM_VIEW_TYPE = "view_type";
    public static final String EVENT_PARAM_EXTRAS_EMAIL = "submitted_email";
    public static final String EVENT_PARAM_EXTRAS_EMAIL_APP_SUPPLIED_USE = "email_app_supplied_use";
    public static final String EVENT_PARAM_EXTRAS_EMAIL_SELECTED_USE = "email_selected_use";
    public static final String EVENT_PARAM_EXTRAS_EQUALS = "equals";
    public static final String EVENT_PARAM_EXTRAS_ERROR = "error";
    public static final String EVENT_PARAM_EXTRAS_FALSE = "false";
    public static final String EVENT_PARAM_EXTRAS_FETCH_STATUS = "fetch_status";
    public static final String EVENT_PARAM_EXTRAS_GET_ACCOUNTS_PERM = "get_accounts_perm";
    public static final String EVENT_PARAM_EXTRAS_LINK = "link";
    public static final String EVENT_PARAM_EXTRAS_NOT_COMPLETED = "not_completed";
    public static final String EVENT_PARAM_EXTRAS_NOT_EQUALS = "notEquals";
    public static final String EVENT_PARAM_EXTRAS_NOT_SUPPLIED = "notSupplied";
    public static final String EVENT_PARAM_EXTRAS_PHONE_NUMBER_SOURCE = "phone_number_source";
    public static final String EVENT_PARAM_EXTRAS_READ_NUMBER_PERM = "read_phone_number_permission";
    public static final String EVENT_PARAM_EXTRAS_RECEIVE_SMS_PERM = "read_sms_permission";
    public static final String EVENT_PARAM_EXTRAS_RETRY = "retry";
    public static final String EVENT_PARAM_EXTRAS_SIM_LOCALE = "sim_locale";
    public static final String EVENT_PARAM_EXTRAS_SKIN_MANAGER_HAS_BG_IMAGE = "skin_manager_has_background_image";
    public static final String EVENT_PARAM_EXTRAS_SKIN_MANAGER_PRIMARY_COLOR = "skin_manager_primary_color";
    public static final String EVENT_PARAM_EXTRAS_SKIN_MANAGER_TINT = "skin_manager_tint";
    public static final String EVENT_PARAM_EXTRAS_SKIN_MANAGER_TINT_INTENSITY = "skin_manager_tint_intensity";
    public static final String EVENT_PARAM_EXTRAS_SKIN_TYPE = "skin_type";
    public static final String EVENT_PARAM_EXTRAS_STARTED = "started";
    public static final String EVENT_PARAM_EXTRAS_SUBMITTED_PHONE_NUMBER = "submitted_phone_number";
    public static final String EVENT_PARAM_EXTRAS_TRUE = "true";
    public static final String EVENT_PARAM_EXTRAS_UIMANAGER = "ui_manager";
    private static final String EVENT_PARAM_LOGIN_RESULT = "4_result";
    private static final String EVENT_PARAM_LOGIN_TYPE = "3_type";
    public static final String EVENT_PARAM_LOGIN_TYPE_VALUE_EMAIL = "email";
    public static final String EVENT_PARAM_LOGIN_TYPE_VALUE_PHONE = "phone";
    private static final String EVENT_PARAM_SDK = "11_sdk";
    public static final String EVENT_PARAM_SDK_ANDROID = "Android";
    private static final String EVENT_PARAM_STATE = "2_state";
    private static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    public static final String EVENT_PARAM_UIMANAGER_ADVANCED = "AdvancedUIManager";
    public static final String EVENT_PARAM_UIMANAGER_BASE = "BaseUIManager";
    public static final String EVENT_PARAM_UIMANAGER_DEFAULT = "UIManager";
    public static final String EVENT_PARAM_UIMANAGER_SKIN = "SkinManager";
    public static final String EVENT_PARAM_UIMANAGER_THEME = "ThemeUIManager";
    private static final String EVENT_PARAM_VERIFICATION_METHOD = "10_verification_method";
    public static final String EVENT_PARAM_VERIFICATION_METHOD_CONFIRMATION_CODE = "confirmation_code";
    public static final String EVENT_PARAM_VERIFICATION_METHOD_INSTANT_VERIFICATION = "instant_verification";
    private static final String EVENT_PARAM_VIEW_STATE = "8_view_state";
    public static final String EVENT_PARAM_VIEW_STATE_DISMISSED = "dismissed";
    public static final String EVENT_PARAM_VIEW_STATE_PRESENTED = "presented";
    public static final String EVENT_PARAM_VIEW_STATE_VISIBLE = "visible";
    private static final String SAVED_LOGGING_REF = "accountkitLoggingRef";
    private final Context applicationContext;
    private final String applicationId;
    private final boolean facebookAppEventsEnabled;
    private String loggingRef = UUID.randomUUID().toString();

    InternalLogger(Context applicationContext2, String applicationId2, boolean facebookAppEventsEnabled2) {
        this.applicationContext = applicationContext2;
        this.applicationId = applicationId2;
        this.facebookAppEventsEnabled = facebookAppEventsEnabled2;
    }

    /* access modifiers changed from: 0000 */
    public void saveInstanceState(Bundle outState) {
        outState.putString(SAVED_LOGGING_REF, this.loggingRef);
    }

    /* access modifiers changed from: 0000 */
    public void onActivityCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.loggingRef = savedInstanceState.getString(SAVED_LOGGING_REF);
        } else {
            this.loggingRef = UUID.randomUUID().toString();
        }
    }

    public void logEvent(String eventName) {
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, null);
    }

    public void logImpression(String eventName, String loginType, boolean isPresented, JSONObject extras) {
        Bundle bundle = getAuthorizationLoggingBundle();
        bundle.putString(EVENT_PARAM_LOGIN_TYPE, loginType);
        bundle.putString(EVENT_PARAM_VIEW_STATE, isPresented ? EVENT_PARAM_VIEW_STATE_PRESENTED : "dismissed");
        if (extras != null) {
            bundle.putString(EVENT_PARAM_EXTRAS, extras.toString());
        }
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
        if (this.facebookAppEventsEnabled) {
            new FacebookAppEventLogger(this.applicationContext).logImpression(eventName, bundle, isPresented);
        }
    }

    public void logButtonImpression(String eventName, String loginType, JSONObject extras) {
        Bundle bundle = getAuthorizationLoggingBundle();
        bundle.putString(EVENT_PARAM_LOGIN_TYPE, loginType);
        bundle.putString(EVENT_PARAM_VIEW_STATE, EVENT_PARAM_VIEW_STATE_VISIBLE);
        if (extras != null) {
            bundle.putString(EVENT_PARAM_EXTRAS, extras.toString());
        }
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
    }

    public void logUIManager(String eventName, JSONObject extras) {
        Bundle bundle = getAuthorizationLoggingBundle();
        if (extras != null) {
            bundle.putString(EVENT_PARAM_EXTRAS, extras.toString());
        }
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
    }

    public void logCustomUI(String eventName, String loginType, JSONObject extras) {
        Bundle bundle = getAuthorizationLoggingBundle();
        bundle.putString(EVENT_PARAM_LOGIN_TYPE, loginType);
        if (extras != null) {
            bundle.putString(EVENT_PARAM_EXTRAS, extras.toString());
        }
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
    }

    public void logLoginModel(String eventName, LoginModelImpl loginModel) {
        if (loginModel != null) {
            Bundle bundle = getAuthorizationLoggingBundle();
            if (loginModel instanceof PhoneLoginModelImpl) {
                bundle.putString(EVENT_PARAM_LOGIN_TYPE, "phone");
                bundle.putString(EVENT_PARAM_COUNTRY_CODE, ((PhoneLoginModelImpl) loginModel).getPhoneNumber().getCountryCodeIso());
            } else if (loginModel instanceof EmailLoginModelImpl) {
                bundle.putString(EVENT_PARAM_LOGIN_TYPE, "email");
            } else {
                throw new InvalidParameterException("Unexpected loginModel type");
            }
            bundle.putString(EVENT_PARAM_STATE, loginModel.getStatus().toString());
            AccountKitError error = loginModel.getError();
            if (error != null) {
                bundle.putString(EVENT_PARAM_ERROR_CODE, Integer.toString(error.getErrorType().getCode()));
                bundle.putString(EVENT_PARAM_ERROR_MESSAGE, error.getErrorType().getMessage());
            }
            new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
            if (this.facebookAppEventsEnabled) {
                if (eventName.equals(EVENT_NAME_CONFIRM_SEAMLESS_PENDING) || eventName.equals(EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN)) {
                    bundle.putString(EVENT_PARAM_VERIFICATION_METHOD, EVENT_PARAM_VERIFICATION_METHOD_INSTANT_VERIFICATION);
                } else if (eventName.equals(EVENT_NAME_LOGIN_VERIFY) || eventName.equals(EVENT_NAME_LOGIN_COMPLETE)) {
                    bundle.putString(EVENT_PARAM_VERIFICATION_METHOD, "confirmation_code");
                }
                FacebookAppEventLogger facebookAppEventLogger = new FacebookAppEventLogger(this.applicationContext);
                if (eventName.equals(EVENT_NAME_LOGIN_COMPLETE) && (loginModel instanceof EmailLoginModelImpl)) {
                    facebookAppEventLogger.logFacebookAppEvents(EVENT_NAME_LOGIN_VERIFY, null, bundle);
                }
                facebookAppEventLogger.logFacebookAppEvents(eventName, null, bundle);
            }
        }
    }

    public void logFetchEvent(String eventName, String status) {
        logFetchEvent(eventName, status, null);
    }

    public void logFetchEventError(String eventName, InternalAccountKitError error) {
        logFetchEvent(eventName, "error", error);
    }

    private void logFetchEvent(String eventName, String status, InternalAccountKitError error) {
        JSONObject extras = new JSONObject();
        try {
            extras.put(EVENT_PARAM_EXTRAS_FETCH_STATUS, status);
        } catch (JSONException e) {
        }
        Bundle bundle = getAuthorizationLoggingBundle();
        bundle.putString(EVENT_PARAM_EXTRAS, extras.toString());
        if (error != null) {
            bundle.putString(EVENT_PARAM_ERROR_CODE, Integer.toString(error.getCode()));
            bundle.putString(EVENT_PARAM_ERROR_MESSAGE, error.getMessage());
        }
        new AppEventsLogger(this.applicationContext, this.applicationId).logSdkEvent(eventName, null, bundle);
    }

    /* access modifiers changed from: 0000 */
    public String getLoggingRef() {
        return this.loggingRef;
    }

    public boolean getFacebookAppEventsEnabled() {
        return this.facebookAppEventsEnabled && FacebookAppEventLogger.isFacebookSDKInitialized();
    }

    private Bundle getAuthorizationLoggingBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_AUTH_LOGGER_ID, this.loggingRef == null ? "" : this.loggingRef);
        bundle.putString(EVENT_PARAM_STATE, "");
        bundle.putString(EVENT_PARAM_LOGIN_TYPE, "");
        bundle.putString(EVENT_PARAM_LOGIN_RESULT, "");
        bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "");
        bundle.putString(EVENT_PARAM_VIEW_STATE, "");
        bundle.putString(EVENT_PARAM_ERROR_CODE, "");
        bundle.putString(EVENT_PARAM_SDK, EVENT_PARAM_SDK_ANDROID);
        bundle.putString(EVENT_PARAM_EXTRAS, "");
        return bundle;
    }
}
