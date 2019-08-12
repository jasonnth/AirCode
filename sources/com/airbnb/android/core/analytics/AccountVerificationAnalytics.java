package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.FreezeDetails;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.utils.Strap;

public class AccountVerificationAnalytics extends BaseAnalytics {
    private static final String EVENT_NAME = "identity_verification";
    public static final String HELP_BUTTON = "help";

    public enum CaptureMode {
        Mitek,
        Jumio,
        Airbnb;

        public NavigationTag getSelfieNavigationTag() {
            switch (this) {
                case Airbnb:
                    return NavigationTag.VerificationSelfieCameraAirbnb;
                case Mitek:
                    return NavigationTag.VerificationSelfieCameraMisnap;
                default:
                    return null;
            }
        }
    }

    public static void trackNonNavImpression(NavigationTag pageTag, String verificationStatus) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", pageTag.trackingName).mo11639kv(BaseAnalytics.OPERATION, "impression").mo11639kv("verification_status", verificationStatus));
    }

    public static void trackWebIntentDeepLink(boolean replaceVerifiedIdWithIdentity) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "verify_endpoint").mo11639kv(ManageListingAnalytics.FLOW, replaceVerifiedIdWithIdentity ? InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY : "verified_id"));
    }

    public static void trackDeepLink(String verificationStep) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "verification_deep_link").mo11639kv("verification_step", verificationStep));
    }

    public static void trackProfileEdit(boolean replaceVerifiedIdWithIdentity) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, EditProfileAnalytics.EDIT_PROFILE).mo11639kv(ManageListingAnalytics.FLOW, replaceVerifiedIdWithIdentity ? InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY : "verified_id"));
    }

    public static void trackProfilePhotoType(String type) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "profile_photo_upload").mo11639kv("type", type));
    }

    public static void trackP4VerificationBlock(GovernmentIdResult result) {
        trackP4Verification(result, "p4_verification_block");
    }

    public static void trackP4VerificationInstantBookDenied(GovernmentIdResult result) {
        trackP4Verification(result, "p4_verification_instant_book_denied");
    }

    public static void trackP4VerificationInstantBookPending(GovernmentIdResult result) {
        trackP4Verification(result, "p4_verification_instant_book_pending");
    }

    public static void trackP4VerificationInstantBookDeniedRTB(GovernmentIdResult result) {
        trackP4Verification(result, "p4_verification_instant_book_denied_rtb");
    }

    private static void trackP4Verification(GovernmentIdResult result, String operation) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("status", result.getStatus()).mo11638kv(CancellationAnalytics.VALUE_PAGE_REASON, result.getRejectReason()).mo11638kv("userId", result.getUserId()).mo11639kv("venderReference", result.getVendorReference()).mo11638kv("verderStatus", result.getVendorStatus()));
    }

    public static void trackSelfieUpload(boolean success, Integer errorCode, String failureReason, VerificationFlow flow, long uploadTime) {
        Strap params = Strap.make().mo11639kv(BaseAnalytics.OPERATION, success ? "success" : BaseAnalytics.FAILURE).mo11639kv(BaseAnalytics.TARGET, "upload_selfie_photo").mo11639kv("page", "verification_selfie").mo11639kv("verification_flow", flow.toString()).mo11638kv("upload_time", uploadTime);
        if (!success) {
            params = params.mo11637kv("error_code", errorCode.intValue()).mo11639kv("failure_reason", failureReason);
        }
        AirbnbEventLogger.track(EVENT_NAME, params);
    }

    public static void trackButtonClick(NavigationTag pageTag, String tartget) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(pageTag.trackingName, "click", tartget));
    }

    public static void trackRequestStart(NavigationTag pageTag, String target, Strap extraParams) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(pageTag.trackingName, "view", target).mix(extraParams));
    }

    public static void trackRequestSuccess(NavigationTag pageTag, String target) {
        trackRequestSuccess(pageTag, target, null);
    }

    public static void trackRequestFailure(NavigationTag pageTag, String target) {
        trackRequestFailure(pageTag, target, null);
    }

    public static void trackRequestSuccess(NavigationTag pageTag, String target, Strap extraParams) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(pageTag.trackingName, "success", target).mix(extraParams));
    }

    public static void trackRequestFailure(NavigationTag pageTag, String target, Strap extraParams) {
        AirbnbEventLogger.track(EVENT_NAME, makeParams(pageTag.trackingName, BaseAnalytics.FAILURE, target).mix(extraParams));
    }

    public static void trackP4VerifiedID(String flow, FreezeDetails freezeDetails, long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "p4_verification_required").mo11639kv(ManageListingAnalytics.FLOW, flow).mo11638kv("listing_id", listingId).mo11639kv("frozen_reason", freezeDetails.getReason()));
    }

    public static void trackMobileHandOffCompletedStep(String verificationStep) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "mobile_handoff").mo11639kv("verification", verificationStep));
    }

    private static Strap makeParams(String page, String operation, String target) {
        return Strap.make().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.TARGET, target);
    }

    public static void trackFBAccountKitPhoneNumberVerificationActions(String operation) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", NavigationTag.VerificationPhoneFB.trackingName).mo11639kv(BaseAnalytics.OPERATION, operation));
    }
}
