package com.airbnb.android.core.analytics;

import android.content.Context;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.Strap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public final class RegistrationAnalytics extends BaseAnalytics {
    public static final String ALIPAY = "alipay";
    public static final String CONFIRM_DETAILS_BUTTON = "confirm_social_details_button";
    public static final String CONTINUE_BUTTON = "continue_button";
    public static final String CREATE_ACCOUNT_BUTTON = "create_account_button";
    public static final String DIRECT = "direct";
    public static final String EMAIL = "email";
    public static final String EMAIL_RESET_PASSWORD_LOGIN = "email_reset_password_login";
    public static final String EMAIL_RESET_PASSWORD_RESET = "email_reset_password_reset";
    public static final String EMAIL_RESET_PASSWORD_RESET_BUTTON = "email_reset_password_reset_button";
    public static final String EMAIL_RESET_PASSWORD_VERIFY_SECRET = "email_reset_password_verify_secret";
    private static final String EVENT_NAME = "registration";
    public static final String FACEBOOK = "facebook";
    public static final String FORGOT_PASSWORD_BUTTON = "forgot_password_button";
    public static final String FORGOT_PASSWORD_EMAIL_RESPONSE = "forgot_password_email_response";
    public static final String FORGOT_PASSWORD_PHONE_RESPONSE = "forgot_password_phone_response";
    public static final String FORGOT_PASSWORD_REQUEST_EMAIL_BUTTON = "forgot_password_request_email_button";
    public static final String FORGOT_PASSWORD_REQUEST_EMAIL_BUTTON_WRONG_AUTH = "forgot_password_request_email_button_wrong_auth";
    public static final String FORGOT_PASSWORD_REQUEST_PHONE_BUTTON = "forgot_password_request_phone_button";
    public static final String GPLUS = "gplus";
    public static final String HIDE_PASSWORD_BUTTON = "hide_password_button";
    private static final String IS_INPUT_VALID_PASSWORD = "is_input_valid_password";
    public static final String LOG_IN_BUTTON = "log_in_button";
    public static final String LOG_IN_REQUEST_BUTTON = "log_in_request_button";
    public static final String LOG_IN_RESPONSE = "log_in_response";
    public static final String MORE_OPTIONS_BUTTON = "more_options_button";
    public static final String MOWEB = "mobile_web";
    public static final String MOWEB_AUTH_TOKEN_BROADCAST_ENABLED_FLAG_RECEIVED = "moweb_auth_broadcast_enabled_flag_recieved";
    public static final String MOWEB_AUTH_TOKEN_BROADCAST_NAME_RECEIVED = "moweb_auth_broadcast_name_recieved";
    public static final String MOWEB_AUTH_TOKEN_BROADCAST_USER_ID_RECEIVED = "moweb_auth_broadcast_user_id_recieved";
    public static final String MOWEB_AUTO_AUTH_SIGN_IN_RESPONSE = "moweb_auth_auto_signin_response";
    public static final String MOWEB_LANDING_CONTINUE_BUTTON = "moweb_landing_continue_button";
    public static final String MOWEB_LANDING_SWITCH_ACCOUNT_BUTTON = "moweb_landing_switch_account_button";
    public static final String NEXT_BUTTON = "next_button";
    public static final String PHONE = "phone";
    static final String PHONE_EMAIL_TOGGLE = "phone_email_toggle";
    public static final String PROMO_OPTION_SWITCH = "sign_up_promo_option_switch";
    public static final String SERVICE_PARAM = "service";
    public static final String SHOW_PASSWORD_BUTTON = "show_password_button";
    public static final String SIGN_IN_EXISTING_EMAIL_ACCOUNT_FOR_SOCIAL_LOGIN = "sign_in_existing_email_account_for_social_login";
    public static final String SIGN_UP_REQUEST_BUTTON = "sign_up_request_button";
    public static final String SIGN_UP_RESPONSE = "sign_up_response";
    private static final String VALUE_PARAM = "value";
    public static final String VERIFY_EMAIL_RESPNOSE = "verify_email_response";
    public static final String VERIFY_PHONE_RESPONSE = "verify_phone_response";
    public static final String WECHAT = "wechat";
    public static final String WEIBO = "weibo";

    @Retention(RetentionPolicy.SOURCE)
    public @interface Response {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Target {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Service {
    }

    public static void trackClickEvent(String clickTarget, NavigationTag page) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams("click", page, clickTarget));
    }

    public static void trackClickEvent(String clickTarget, String service, NavigationTag page) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams("click", page, clickTarget).mo11639kv("service", service));
    }

    public static void trackClickEvent(String clickTarget, String service, NavigationTag page, Strap extra) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams("click", page, clickTarget).mo11639kv("service", service).mix(extra));
    }

    public static void trackRegistrationPasswordPageClickEvent(String clickTarget, String service, NavigationTag page, boolean isValidPassword) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams("click", page, clickTarget).mo11639kv("service", service).mo11640kv(IS_INPUT_VALID_PASSWORD, isValidPassword));
    }

    public static void trackEmailPhoneToggleEvent(NavigationTag page, String serviceSelected) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams("click", page, PHONE_EMAIL_TOGGLE).mo11639kv("value", serviceSelected));
    }

    public static void trackRequestResponseSuccess(String response, String service, NavigationTag page, Context context) {
        trackRequestResponseResult(response, service, page, "success", Strap.make());
        logToMParticleIfNeeded(response, service, context);
    }

    public static void trackRequestResponseFailure(String response, String service, NavigationTag page, NetworkException e) {
        trackRequestResponseResult(response, service, page, BaseAnalytics.FAILURE, NetworkUtil.getNetworkErrorLoggingData(e));
    }

    public static void trackRequestResponseFailure(String response, String service, NavigationTag page, Strap extra) {
        trackRequestResponseResult(response, service, page, BaseAnalytics.FAILURE, extra);
    }

    private static void trackRequestResponseResult(String response, String service, NavigationTag page, String operation, Map<String, String> extraParams) {
        AirbnbEventLogger.track(EVENT_NAME, getCommonParams(operation, page, response).mo11639kv("service", service).mix(extraParams));
    }

    public static void trackMowebAuthTokenRetrieval(String userId, String userName, String isEnabled) {
        String str = EVENT_NAME;
        new Strap();
        AirbnbEventLogger.track(str, Strap.make().mo11639kv(MOWEB_AUTH_TOKEN_BROADCAST_USER_ID_RECEIVED, userId).mo11639kv(MOWEB_AUTH_TOKEN_BROADCAST_NAME_RECEIVED, userName).mo11639kv(MOWEB_AUTH_TOKEN_BROADCAST_ENABLED_FLAG_RECEIVED, isEnabled).mo11639kv("service", MOWEB));
    }

    private static Strap getCommonParams(String operation, NavigationTag page, String target) {
        return Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("page", page.trackingName).mo11639kv(BaseAnalytics.TARGET, target);
    }

    private static void logToMParticleIfNeeded(String response, String service, Context context) {
        char c = 65535;
        switch (response.hashCode()) {
            case -1546162784:
                if (response.equals(LOG_IN_RESPONSE)) {
                    c = 0;
                    break;
                }
                break;
            case 1153301987:
                if (response.equals(SIGN_UP_RESPONSE)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                MParticleAnalytics.logEvent("log_in", Strap.make().mo11639kv("service", service));
                AppboyAnalytics.logEvent(context, "log_in", Strap.make().mo11639kv("service", service));
                return;
            case 1:
                MParticleAnalytics.logEvent("sign_up", Strap.make().mo11639kv("service", service));
                AppboyAnalytics.logEvent(context, "sign_up", Strap.make().mo11639kv("service", service));
                return;
            default:
                return;
        }
    }
}
