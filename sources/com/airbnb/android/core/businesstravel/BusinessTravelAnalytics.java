package com.airbnb.android.core.businesstravel;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.ParcelStrap;
import p005cn.jpush.android.JPushConstants.PushService;

public class BusinessTravelAnalytics extends BaseAnalytics {
    private static final String ERROR_MESSAGE = "error_displayed_message";
    private static final String EVENT_BT_AUTO_ENROLL = "biz_travel_auto_enroll";
    private static final String EVENT_BT_SIGN_UP = "biz_sign_up";
    private static final String EVENT_BUSINESS_TRAVEL = "biz_travel_mobile";
    private static final String PAGE_ADD_WORK_EMAIL = "add_work_email";
    private static final String PAGE_AUTO_ENROLL = "dashboard";
    private static final String PAGE_USER_PROFILE_EDIT = "user_profile_edit";
    private static final String SECTION_RECORD_REMOVE = "verified_record__remove";
    private static final String USER_ID = "user_id";
    private static final String WORK_EMAIL = "work_email";

    public class AdditionalParams {
        final ParcelStrap additionalParams = ParcelStrap.make();

        public AdditionalParams() {
        }

        public AdditionalParams user(AirbnbAccountManager airbnbAccountManager) {
            this.additionalParams.mo9946kv("user_id", String.valueOf(airbnbAccountManager.getCurrentUserId()));
            return this;
        }

        public AdditionalParams email(String email) {
            this.additionalParams.mo9946kv(BusinessTravelAnalytics.WORK_EMAIL, email);
            return this;
        }

        public AdditionalParams errorCode(int error) {
            this.additionalParams.mo9945kv("error", error);
            return this;
        }

        public AdditionalParams errorMessage(String errorMessage) {
            this.additionalParams.mo9946kv(BusinessTravelAnalytics.ERROR_MESSAGE, errorMessage);
            return this;
        }

        public ParcelStrap create() {
            return this.additionalParams;
        }
    }

    public static void trackEvent(String page, String section, String action, String target, ParcelStrap additionalValues) {
        AirbnbEventLogger.event().name(EVENT_BUSINESS_TRAVEL).mo8238kv("page", page).mo8238kv(BaseAnalytics.SECTION, section).mo8238kv("action", action).mo8238kv(BaseAnalytics.TARGET, target).mix(additionalValues).track();
    }

    public static void trackAddWorkEmailEvent(String action, String target, ParcelStrap additionalValues) {
        AirbnbEventLogger.event().name(EVENT_BUSINESS_TRAVEL).mo8238kv("page", PAGE_ADD_WORK_EMAIL).mo8238kv(BaseAnalytics.SECTION, PAGE_ADD_WORK_EMAIL).mo8238kv("action", action).mo8238kv(BaseAnalytics.TARGET, target).mix(additionalValues).track();
    }

    public static void trackRemoveWorkEmailEvent(String operation, ParcelStrap additionalValues) {
        AirbnbEventLogger.event().name(EVENT_BT_SIGN_UP).mo8238kv("page", PAGE_USER_PROFILE_EDIT).mo8238kv(BaseAnalytics.SECTION, SECTION_RECORD_REMOVE).mo8238kv(BaseAnalytics.OPERATION, operation).mo8238kv("jsapp", "business_travel.quick_enroll").mo8238kv(PushService.PARAM_PLATFORM, "mobile").mix(additionalValues).track();
    }

    public static void trackAutoEnrollEvent(String operation) {
        AirbnbEventLogger.event().name(EVENT_BT_AUTO_ENROLL).mo8238kv("page", PAGE_AUTO_ENROLL).mo8238kv(BaseAnalytics.OPERATION, operation).track();
    }

    public static AdditionalParams additionalParams() {
        BusinessTravelAnalytics businessTravelAnalytics = new BusinessTravelAnalytics();
        businessTravelAnalytics.getClass();
        return new AdditionalParams();
    }
}
