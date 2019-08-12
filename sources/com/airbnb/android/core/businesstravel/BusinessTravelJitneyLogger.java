package com.airbnb.android.core.businesstravel;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.BizTravelReferrer.p041v1.C1805BizTravelReferrer;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelBtrFilterImpressionEvent.Builder;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelBtrFilterNumListingsEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelBtrFilterToggleEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelDeepLinkEmailVerifiedStartTravelingClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p043v1.BusinessTravelMobileP5PromotionImpressionEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelAccountProfileTravelForWorkClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelAccountProfileTravelForWorkImpressionEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailBlurEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailFocusEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailSubmitErrorEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailSubmitEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailSubmitSuccessEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileAddEmailVerificationGotItClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileP5PromotionAddEmailClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileP5PromotionSkipClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobilePendingEmailVerificationGotItClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileViewProfileAddWorkEmailClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.BusinessTravelMobileViewProfileVerifyWorkEmailClickEvent;
import com.airbnb.jitney.event.logging.BusinessTravel.p044v2.C1867x9a334ed7;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;

public class BusinessTravelJitneyLogger extends BaseLogger {
    public BusinessTravelJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logBTRFilterImpression() {
        publish(new Builder(context()));
    }

    public void logBTRFilterToggle(boolean isToggledOn) {
        publish(new BusinessTravelBtrFilterToggleEvent.Builder(context(), isToggledOn ? C2759ToggleMethod.Toggle : C2759ToggleMethod.Untoggle));
    }

    public void logBTRFilterNumListings(long numBTRListings) {
        publish(new BusinessTravelBtrFilterNumListingsEvent.Builder(context(), Long.valueOf(numBTRListings)));
    }

    public void logAccountProfileTravelForWorkImpression() {
        publish(new BusinessTravelAccountProfileTravelForWorkImpressionEvent.Builder(context()));
    }

    public void logAccountProfileTravelForWorkRowClicked() {
        publish(new BusinessTravelAccountProfileTravelForWorkClickEvent.Builder(context()));
    }

    public void logMobileP5PromoImpression(String confirmationCode) {
        publish(new BusinessTravelMobileP5PromotionImpressionEvent.Builder(context(), confirmationCode));
    }

    public void logMobileP5PromoClickAddEmail(String confirmationCode) {
        publish(new BusinessTravelMobileP5PromotionAddEmailClickEvent.Builder(context(), confirmationCode));
    }

    public void logMobileP5PromoClickSkip(String confirmationCode) {
        publish(new BusinessTravelMobileP5PromotionSkipClickEvent.Builder(context(), confirmationCode));
    }

    public void logWorkEmailInputFocus(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobileAddEmailFocusEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logUpdateWorkEmailError(WorkEmailLaunchSource source, String errorMessage, String email) {
        publish(new BusinessTravelMobileAddEmailSubmitErrorEvent.Builder(context(), errorMessage, email, fromWorkEmailLaunchSource(source)));
    }

    public void logUpdateWorkEmailSuccess(WorkEmailLaunchSource source, String email) {
        publish(new BusinessTravelMobileAddEmailSubmitSuccessEvent.Builder(context(), email, fromWorkEmailLaunchSource(source)));
    }

    public void logClickAddWorkEmail(WorkEmailLaunchSource source, String email) {
        publish(new BusinessTravelMobileAddEmailSubmitEvent.Builder(context(), email, fromWorkEmailLaunchSource(source)));
    }

    public void logExitAddWorkEmail(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobileAddEmailBlurEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logEmailVerificationClickGotIt(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobileAddEmailVerificationGotItClickEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logPendingEmailVerificationClickGotIt(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobilePendingEmailVerificationGotItClickEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logPendingEmailVerificationClickResend(WorkEmailLaunchSource source) {
        publish(new C1867x9a334ed7.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logViewProfileAddWorkEmailClickAdd(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobileViewProfileAddWorkEmailClickEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logViewProfileVerifyWorkEmailClickVerify(WorkEmailLaunchSource source) {
        publish(new BusinessTravelMobileViewProfileVerifyWorkEmailClickEvent.Builder(context(), fromWorkEmailLaunchSource(source)));
    }

    public void logBTEmailVerifiedWelcomeModalImpression(String email) {
        publish(new BusinessTravelDeepLinkEmailVerifiedModalImpressionEvent.Builder(context(), email));
    }

    public void logBTEmailVerifiedWelcomeModalClickGotIt(String email) {
        publish(new BusinessTravelDeepLinkEmailVerifiedStartTravelingClickEvent.Builder(context(), email));
    }

    private C1805BizTravelReferrer fromWorkEmailLaunchSource(WorkEmailLaunchSource source) {
        switch (source) {
            case MobileP5Promo:
                return C1805BizTravelReferrer.P5;
            case AccountPage:
                return C1805BizTravelReferrer.AccountProfile;
            case EditProfile:
                return C1805BizTravelReferrer.EditProfile;
            case ViewProfile:
                return C1805BizTravelReferrer.ViewProfile;
            case DeepLink:
                return C1805BizTravelReferrer.DeepLink;
            default:
                BugsnagWrapper.throwOrNotify(new RuntimeException("Unknown work email launch source."));
                return null;
        }
    }
}
