package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.utils.Strap;

public class VerifiedIdAnalytics {
    private static final String COMPLETE_CATEGORY = "COMPLETE";
    private static final String EMAIL_CATEGORY = "Email";
    private static final String GLOBAL_CATEGORY = "GLOBAL";
    public static final String ID_CAPTURED_METHOD = "id_capture_method";
    private static final String OFFLINE_CATEGORY = "Offline";
    private static final String ONLINE_CATEGORY = "Online";
    private static final String PHONE_CATEGORY = "Phone";
    private static final String PHOTO_CATEGORY = "Photo";
    public static final String RESERVATION_ID_KEY = "reservation_id";
    private static final String SESAME_CATEGORY = "Sesame";
    public static final String VERIFIED_ID_EVENT = "verified_id";
    private static final String WELCOME_CATEGORY = "WELCOME";

    public interface VerifiedIdStrapper {
        Strap getVerifiedIdAnalyticsStrap();
    }

    public static void trackWelcomeStartView() {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(WELCOME_CATEGORY, "START", "VIEW", null));
    }

    public static void trackWelcomeStartVerifyMe(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(WELCOME_CATEGORY, "START", "VERIFY_ME", additionalParams));
    }

    public static void trackPhotoStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackPhotoStartTakePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "START", "TAKE_PHOTO", additionalParams));
    }

    public static void trackPhotoStartChoosePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "START", "CHOOSE_PHOTO", additionalParams));
    }

    public static void trackPhotoApprovalView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "APPROVAL", "VIEW", additionalParams));
    }

    public static void trackPhotoApprovalChangePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "APPROVAL", "CHANGE_PHOTO", additionalParams));
    }

    public static void trackPhotoApprovalApprovePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "APPROVAL", "APPROVE_PHOTO", additionalParams));
    }

    public static void trackPhotoConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHOTO_CATEGORY, "CONFIRM", "VIEW", additionalParams));
    }

    public static void trackEmailStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(EMAIL_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackEmailStartChange(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(EMAIL_CATEGORY, "START", "CHANGE", additionalParams));
    }

    public static void trackEmailStartSend(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(EMAIL_CATEGORY, "START", "SEND", additionalParams));
    }

    public static void trackEmailConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(EMAIL_CATEGORY, "CONFIRM", "VIEW", additionalParams));
    }

    public static void trackPhoneStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackPhoneStartSelectField(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "START", "SELECT_FIELD", additionalParams));
    }

    public static void trackPhoneStartDidType(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "START", "DID_TYPE", additionalParams));
    }

    public static void trackPhoneStartSend(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "START", "SEND", additionalParams));
    }

    public static void trackPhonePendingView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "PENDING", "VIEW", additionalParams));
    }

    public static void trackPhonePendingDidSelectField(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "PENDING", "DID_SELECT_FIELD", additionalParams));
    }

    public static void trackPhonePendingDidType(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "PENDING", "DID_TYPE", additionalParams));
    }

    public static void trackPhonePendingSend(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "PENDING", "SEND", additionalParams));
    }

    public static void trackPhoneConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(PHONE_CATEGORY, "CONFIRM", "VIEW", additionalParams));
    }

    public static void trackSesameStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(SESAME_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackSesameStartEnterInfo(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(SESAME_CATEGORY, "START", "ENTER_INFO", additionalParams));
    }

    public static void trackSesameStartVerifyWithSesame(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(SESAME_CATEGORY, "START", "VERIFY_SESAME", additionalParams));
    }

    public static void trackSesameVerifyResult(Strap additionalParams, String result) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(SESAME_CATEGORY, "SESAME_VERIFY", result, additionalParams));
    }

    public static void trackOfflineVerificationMethodSwitch(Strap additionalParams, String method) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "CHANGE_METHOD", method, additionalParams));
    }

    public static void trackOfflineStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackOfflineStartScanId(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "START", "SCAN_ID", additionalParams));
    }

    public static void trackOfflineCountryView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "COUNTRY", "VIEW", additionalParams));
    }

    public static void trackOfflineCountryChange(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "COUNTRY", "CHANGE", additionalParams));
    }

    public static void trackOfflineCountryNext(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "COUNTRY", "NEXT", additionalParams));
    }

    public static void trackOfflineIdTypeView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "ID_TYPE", "VIEW", additionalParams));
    }

    public static void trackOfflineIdTypePassport(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "ID_TYPE", "PASSPORT", additionalParams));
    }

    public static void trackOfflineIdTypeIdCard(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "ID_TYPE", OfficialIdActivity.ID_TYPE, additionalParams));
    }

    public static void trackOfflineIdTypeDriversLicense(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "ID_TYPE", "DRIVERS_LICENSE", additionalParams));
    }

    public static void trackOfflinePhotoFrontView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT", "VIEW", additionalParams));
    }

    public static void trackOfflinePhotoFrontTakePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT", "TAKE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoFrontChoosePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT", "CHOOSE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoFrontConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT_CONFIRM", "VIEW", additionalParams));
    }

    public static void trackOfflinePhotoFrontConfirmChangePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT_CONFIRM", "CHANGE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoFrontConfirmApprovePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_FRONT_CONFIRM", "APPROVE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoBackView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK", "VIEW", additionalParams));
    }

    public static void trackOfflinePhotoBackTakePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK", "TAKE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoBackChoosePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK", "CHOOSE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoBackConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK_CONFIRM", "VIEW", additionalParams));
    }

    public static void trackOfflinePhotoBackConfirmChangePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK_CONFIRM", "CHANGE_PHOTO", additionalParams));
    }

    public static void trackOfflinePhotoBackConfirmApprovePhoto(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "PHOTO_BACK_CONFIRM", "APPROVE_PHOTO", additionalParams));
    }

    public static void trackOfflineUploadPhotoView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "UPLOAD_PHOTO", "VIEW", additionalParams));
    }

    public static void trackOfflineConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, "CONFIRM", "VIEW", additionalParams));
    }

    public static void trackOfflineErrorView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, OfficialIdStatusResponse.ERROR, "VIEW", additionalParams));
    }

    public static void trackOfflineErrorTryAgain(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(OFFLINE_CATEGORY, OfficialIdStatusResponse.ERROR, "TRY_AGAIN", additionalParams));
    }

    public static void trackOnlineStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackOnlineStartFacebook(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "FACEBOOK", additionalParams));
    }

    public static void trackOnlineStartLinkedIn(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "LINKEDIN", additionalParams));
    }

    public static void trackOnlineStartWeibo(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "WEIBO", additionalParams));
    }

    public static void trackOnlineStartGoogle(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "GOOGLE", additionalParams));
    }

    public static void trackOnlineStartLearnMore(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "LEARN_MORE", additionalParams));
    }

    public static void trackOnlineStartConfirmed(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "CONFIRMED", additionalParams));
    }

    public static void trackOnlineStartDenied(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "START", "DENIED", additionalParams));
    }

    public static void trackOnlineConfirmView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(ONLINE_CATEGORY, "CONFIRM", "VIEW", additionalParams));
    }

    public static void trackCompleteStartView(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(COMPLETE_CATEGORY, "START", "VIEW", additionalParams));
    }

    public static void trackCompleteStartDone(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(COMPLETE_CATEGORY, "START", "DONE", additionalParams));
    }

    public static void trackBackPressed(Strap additionalParams) {
        AirbnbEventLogger.track("verified_id", makeVerifiedIdParams(GLOBAL_CATEGORY, "NONE", "BACK", additionalParams));
    }

    public static void trackHealth(String page, String operation) {
        AirbnbEventLogger.track("verified_id_health", Strap.make().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation), true);
    }

    public static void trackPhotoRetake(boolean isAutoMode, String provider) {
        AirbnbEventLogger.track("verified_id_photo_retake_by_user", Strap.make().mo11640kv("is_auto_mode", isAutoMode).mo11639kv("provider", provider));
    }

    private static Strap makeVerifiedIdParams(String category, String view, String action, Strap additionalParams) {
        Strap params = Strap.make().mo11639kv(TripEventModel.CATEGORY, category).mo11639kv("view", view).mo11639kv("action", action);
        if (additionalParams != null) {
            for (String key : additionalParams.keySet()) {
                if (additionalParams.get(key) != null) {
                    params.mo11639kv(key, (String) additionalParams.get(key));
                }
            }
        }
        return params;
    }
}
