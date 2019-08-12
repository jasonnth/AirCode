package com.airbnb.android.core.erf;

import android.content.Context;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UpdateMemoryRequest;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;

public final class FeatureToggles {
    private FeatureToggles() {
    }

    public static boolean allow9MonthBookingWindow() {
        if (!Trebuchet.launch(TrebuchetKeys.ALLOW_NINE_MONTH_BOOKING_WINDOW, false)) {
            return false;
        }
        return Experiments.allow9MonthBookingWindow();
    }

    public static boolean showListingExpectationsSettings() {
        if (!Trebuchet.launch(TrebuchetKeys.ANDROID_SHOW_LISTING_EXPECTATIONS_HOST)) {
            return false;
        }
        if (Experiments.showListingExpectations() || (Experiments.isInListingExpectationsControl() && Experiments.showListingExpectationsV2())) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowDecisionCriteriaMessage() {
        return false;
    }

    public static boolean showHostSideBedDetails() {
        if (!BuildHelper.isDebugFeaturesEnabled() || !DebugSettings.BED_DETAILS.isEnabled()) {
            return Experiments.showHostSideBedDetails();
        }
        return true;
    }

    public static boolean enableSelectML() {
        if (!BuildHelper.isDebugFeaturesEnabled() || !DebugSettings.ENABLE_SELECT_ML.isEnabled()) {
            return Trebuchet.launch(TrebuchetKeys.ENABLE_SELECT_ML_PRE_RELEASE);
        }
        return true;
    }

    public static boolean showFixItTool() {
        return BuildHelper.isFuture() || Trebuchet.launch(TrebuchetKeys.FIX_IT_TOOL_PRE_RELEASE_ENABLED);
    }

    public static boolean showCloseToPassModal() {
        return BuildHelper.isFuture() || (showFixItTool() && Trebuchet.launch(TrebuchetKeys.FIX_IT_CLOSE_TO_PASS_MODAL_ENABLED));
    }

    public static boolean showHostCheckinGuideTool() {
        if (!BuildHelper.isDebugFeaturesEnabled() || !DebugSettings.CHECK_IN_GUIDE.isEnabled()) {
            return Experiments.showHostMlCheckInGuideTool();
        }
        return true;
    }

    public static boolean isGuestCheckInGuideOfflineEnabled() {
        if (!BuildHelper.isDebugFeaturesEnabled() || !DebugSettings.CHECK_IN_GUIDE_OFFLINE_SUPPORT.isEnabled()) {
            return Trebuchet.launch(TrebuchetKeys.GUEST_CHECK_IN_GUIDE_OFFLINE_SUPPORT);
        }
        return true;
    }

    public static boolean isSuperHeroEnabled() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_SUPERHERO);
    }

    public static boolean atlantisEnabled() {
        return BuildHelper.isFuture() && Trebuchet.launch(TrebuchetKeys.ATLANTIS);
    }

    public static boolean isInMiTekVerificationFlow() {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            return BuildHelper.isFuture();
        }
        if (Trebuchet.launch(TrebuchetKeys.DISABLE_JUMIO)) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.ANDROID_MITEK_VERIFICATION_FLOW)) {
            return false;
        }
        return Experiments.requireVerificationsWithMitek();
    }

    public static boolean useAirbnbSelfie() {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            return BuildHelper.isFuture();
        }
        return Trebuchet.launch(TrebuchetKeys.USE_AIRBNB_SELFIE_CAM) && Experiments.useAirbnbForSelfie();
    }

    public static boolean showJumioIdTypeCountrySelector() {
        return Trebuchet.launch(TrebuchetKeys.IDENTITY_SHOW_JUMIO_ID_TYPE_COUNTRY_SELECTOR) && !isInMiTekVerificationFlow() && Experiments.showJumioIdTypeCountrySelector();
    }

    public static boolean replaceVerifiedIdWithIdentity(User verifiedUser) {
        if (DebugSettings.IDENTITY_FLOW.isEnabled()) {
            return true;
        }
        if (DebugSettings.DISABLE_IDENTITY_FLOW.isEnabled()) {
            return false;
        }
        if (!Trebuchet.launch(TrebuchetKeys.REPLACE_VERIFICED_ID_WITH_IDENTITY)) {
            return false;
        }
        if (verifiedUser == null) {
            return false;
        }
        if (verifiedUser.isForceIdentityFlow()) {
            return true;
        }
        if (verifiedUser.isVerifiedIDReplacementExempt()) {
            return false;
        }
        if (!Experiments.replaceVerifiedIdWithIdentity()) {
            return false;
        }
        UpdateMemoryRequest.forVerifiedIDReplacement().execute(NetworkUtil.singleFireExecutor());
        return true;
    }

    public static boolean replaceVerifiedIdWithIdentityOnP4() {
        return Trebuchet.launch(TrebuchetKeys.IDENTITY_ON_P4) && Experiments.identityOnP4();
    }

    public static boolean isTripAssistantEnabled() {
        return BuildHelper.isFuture() || Trebuchet.launch(TrebuchetKeys.ENABLE_TRIP_ASSISTANT);
    }

    public static boolean showMTNovemberTripsTab() {
        return isInNovemberLaunch() && Trebuchet.launch(TrebuchetKeys.SHOW_ITINERARY);
    }

    public static boolean shouldShowFirstMessageSuggestions() {
        return Trebuchet.launch(TrebuchetKeys.FIRST_MESSAGE_SUGGESTIONS) && Experiments.showFirstMessageSuggestions();
    }

    public static boolean showP4Redesign(Context context) {
        DebugSettings debugSettings = CoreApplication.instance(context).component().debugSettings();
        if (BuildHelper.isDebugFeaturesEnabled() && DebugSettings.P4_REDESIGN.isEnabled()) {
            return true;
        }
        if (ChinaUtils.isUserInChinaOrPrefersChineseLanguage() || !Trebuchet.launch(TrebuchetKeys.P4_REDESIGN) || !Experiments.showP4Redesign()) {
            return false;
        }
        return true;
    }

    public static boolean showP4UrgencyMessage() {
        return Trebuchet.launch(TrebuchetKeys.P4_URGENCY_MESSAGE) && Experiments.showP4UrgencyMessage();
    }

    public static boolean showP4CancellationPolicy() {
        return Trebuchet.launch(TrebuchetKeys.P4_CANCELLATION_MESSAGE) && Experiments.showP4CancellationPolicy();
    }

    public static boolean useForP4CheckoutFormat() {
        return Trebuchet.launch(TrebuchetKeys.P4_SKIP_PAYMENT_OPTIONS_FETCH) && Experiments.skipPaymentOptionsFetchOnP4();
    }

    public static boolean canSkipIdentity() {
        return Trebuchet.launch(TrebuchetKeys.P4_ALLOW_IDENTITY_SKIP) && Experiments.canSkipIdentity();
    }

    public static boolean enableRejectionRecovery() {
        return Trebuchet.launch(TrebuchetKeys.GUEST_RECOVERY) && Experiments.showRejectionCancellationRecovery();
    }

    public static boolean isUpsellIBAfterAcceptanceEnabled() {
        return Trebuchet.launch(TrebuchetKeys.UPSELL_IB_ON_ACCEPT) && Experiments.upsellInstantBookOnAcceptExperiment();
    }

    public static boolean shouldApplyExtendedSmartPricing(Listing listing) {
        return Trebuchet.launch(TrebuchetKeys.SMART_PRICING_EXTENDED, false) && listing.isSmartPricingExtended();
    }

    public static boolean shouldApplyExtendedSmartPricingCopy(Listing listing) {
        return shouldApplyExtendedSmartPricingCopy(listing.isSmartPricingExtended());
    }

    public static boolean shouldApplyExtendedSmartPricingCopy(boolean smartPricingExtended) {
        if (Trebuchet.launch(TrebuchetKeys.SMART_PRICING_EXTENDED, false)) {
            return smartPricingExtended || Experiments.useSmartPricingFixedPriceCopy();
        }
        return false;
    }

    public static boolean showHomesNotHotels() {
        return Experiments.showHomesNotHotelsHarderCopy() || Experiments.showHomesNotHotelsSofterCopy();
    }

    public static boolean isInNovemberLaunch() {
        return Trebuchet.launch(TrebuchetKeys.NOVEMBER_LAUNCH);
    }

    public static boolean preapproveBlockDates() {
        return Trebuchet.launch(TrebuchetKeys.PREAPPROVAL_BLOCK_CAL, false);
    }

    public static boolean isListingRegistrationEnabled() {
        return Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_ANDROID, false);
    }

    public static boolean showListingRegistrationChicago() {
        return Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_CHICAGO, false);
    }

    public static boolean showListingRegistrationNOLA() {
        return Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_NOLA, false);
    }

    public static boolean showListingRegistrationSF() {
        return Trebuchet.launch(TrebuchetKeys.LISTING_REGISTRATION_SF, false);
    }

    public static boolean showFeedbackWebVersion() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_FEEDBACK_WEB_VERSION, false);
    }

    public static boolean showTripAssistantInHelpLink() {
        Experiments.showTripAssistantInHelpLinkGroup();
        return Experiments.showTripAssistantInHelpLink();
    }

    public static boolean addBlankItemsToCarouselToEnableFullScroll() {
        return true;
    }

    public static boolean showTripHostStatsTab() {
        return Trebuchet.launch(TrebuchetKeys.TRIP_HOST_STATS_TAB);
    }

    public static void showTripsNuxIfNeeded(Context context, SharedPrefsHelper sharedPrefsHelper, boolean isSignUp) {
        if (!sharedPrefsHelper.hasSeenMagicalTripsNux()) {
            if (isInNovemberLaunch() && Trebuchet.launch(TrebuchetKeys.SHOW_NOVEMBER_NUX) && !CoreApplication.instance().isTestApplication()) {
                sharedPrefsHelper.setHasSeenMagicalTripsNux(true);
                context.startActivity(ReactNativeIntents.intentForMagicalTripsNux(context, isSignUp));
            }
        }
    }

    public static boolean showHostSuspensionDlsBanner() {
        return Trebuchet.launch(TrebuchetKeys.HOST_SUSPENSION) && Experiments.showDlsHostSuspensionBanner();
    }

    public static boolean shouldShowUnfinishedListingOnProfile() {
        if (DebugSettings.UNFINISHED_LISTING_PROFILE.isEnabled()) {
            return true;
        }
        return Experiments.shouldShowUnfinishedListingOnProfile();
    }

    public static boolean shouldShowFamilyAmenitiesML() {
        if (DebugSettings.FAMILY_AMENITIES.isEnabled()) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.ML_FAMILY_FRIENDLY_AMENITIES) || !Experiments.shouldShowFamilyAmenitiesML()) {
            return false;
        }
        return true;
    }

    public static boolean shouldShowLocationAmenitiesML() {
        if (DebugSettings.LOCATION_AMENITIES_ML.isEnabled()) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.ML_LOCATION_AMENITIES) || !Experiments.shouldShowLocationAmenitiesML()) {
            return false;
        }
        return true;
    }

    public static boolean shouldDefaultHomesTabForFamilies() {
        if (DebugSettings.DEFAULT_HOME_TAB_FOR_FAMILIES.isEnabled()) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.DEFAULT_TO_HOME_TAB_FOR_FAMILIES) || !Experiments.shouldDefaultHomeTabForFamilies()) {
            return false;
        }
        return true;
    }

    public static boolean isUpsellTripsAfterReservationEnabled() {
        return Trebuchet.launch(TrebuchetKeys.TRIPS_UPSELL_ON_P5) && Experiments.showTripsUpsellOnP5();
    }

    public static boolean showResolutionCenterNewCopy() {
        Experiments.showResolutionCenterNewCopyGroup();
        return Experiments.showResolutionCenterNewCopy();
    }

    public static boolean showResolutionCenterDeeplink() {
        Experiments.showResolutionCenterDeeplinkGroup();
        return Experiments.showResolutionCenterDeeplink();
    }

    public static boolean shouldEnableSwipeToArchive(InboxType inboxType) {
        if (DebugSettings.SWIPE_TO_ARCHIVE.isEnabled()) {
            return true;
        }
        switch (inboxType) {
            case Guest:
            case GuestArchived:
                return Experiments.shouldEnableSwipeToArchiveForGuest();
            case Host:
            case HostArchived:
                return Experiments.shouldEnableSwipeToArchiveForHost();
            default:
                return false;
        }
    }

    public static boolean enableTripSupportReactNative() {
        if (!Trebuchet.launch("android.trip_support_react_native", false) || !Experiments.enableTripSupportReactNative()) {
            return false;
        }
        return true;
    }

    public static boolean showNativeItinerary() {
        return Trebuchet.launch(TrebuchetKeys.NATIVE_ITINERARY_ENABLED) && Experiments.shouldShowNativeItinerary();
    }

    public static boolean showSuggestionsInNativeItinerary() {
        return Trebuchet.launch(TrebuchetKeys.NATIVE_ITINERARY_SUGGESTIONS_ENABLED) && showNativeItinerary() && Experiments.shouldShowItinerarySuggestions();
    }

    public static boolean showFlightEntryPoint(SharedPrefsHelper sharedPrefsHelper) {
        return Trebuchet.launch(TrebuchetKeys.ITINERARY_FLIGHT_ENTRY_POINT_ENABLED) && sharedPrefsHelper.shouldShowFlightEntryPoint();
    }

    public static boolean showNestedListings() {
        return DebugSettings.NESTED_LISTINGS.isEnabled() || Trebuchet.launch(TrebuchetKeys.BOOKING_SHOW_NESTED_LISTINGS_FORCE_IN) || Trebuchet.launch(TrebuchetKeys.BOOKING_SHOW_NESTED_LISTINGS);
    }

    public static boolean isNestedListingEnabled(boolean eligibleForNestedListings) {
        if (DebugSettings.NESTED_LISTINGS.isEnabled() || Trebuchet.launch(TrebuchetKeys.BOOKING_SHOW_NESTED_LISTINGS_FORCE_IN)) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.BOOKING_SHOW_NESTED_LISTINGS) || !eligibleForNestedListings || !Experiments.isNestedListingsEnabled()) {
            return false;
        }
        return true;
    }

    public static boolean showCohostingStandards() {
        return DebugSettings.COHOSTING_STATS.isEnabled();
    }

    public static boolean shouldShowForYouV3() {
        if (BuildHelper.isFuture()) {
            return true;
        }
        if (MiscUtils.isTabletScreen(CoreApplication.instance().application().getApplicationContext())) {
            return false;
        }
        return Trebuchet.launch(TrebuchetKeys.FOR_YOU_V3_ENABLED);
    }

    public static boolean shouldEnableInsightsCardToOpenListingPhotosAndAmenities() {
        return !Trebuchet.launch(TrebuchetKeys.INSIGHTS_CARD_OPEN_LISTING_PHOTO_AND_AMENITIES_KILL_SWITCH);
    }

    public static boolean showFilterRemovalSuggestions() {
        return Trebuchet.launch(TrebuchetKeys.FILTER_REMOVAL_SUGGESTION_FORCE) || Trebuchet.launch(TrebuchetKeys.ANDROID_FILTER_REMOVAL_SUGGESTION);
    }

    public static boolean showFilterSuggestions() {
        return Trebuchet.launch(TrebuchetKeys.FILTER_SUGGESTION_FORCE) || Trebuchet.launch(TrebuchetKeys.ANDROID_FILTER_SUGGESTION);
    }

    public static boolean showAutocompleteVerticalOptions() {
        return Experiments.showAutocompleteVerticals();
    }

    public static boolean showReviewModalContent(boolean instantBookable) {
        if (Trebuchet.launch(TrebuchetKeys.REVIEW_MODAL_CONTENT_FORCE_ENABLED) && !instantBookable) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.REVIEW_MODAL_CONTENT) || instantBookable) {
            return false;
        }
        return Experiments.showReviewModalContent();
    }

    public static boolean hideGuestProfilePhoto(ReservationStatus status) {
        return status.matchesAny(ReservationStatus.Inquiry, ReservationStatus.Pending, ReservationStatus.Denied, ReservationStatus.Timedout) && inNoProfilePhotoV2Experiment();
    }

    public static boolean hideGuestProfilePhoto(PushNotificationType type) {
        return type.matchesAny(PushNotificationType.ReservationHostRequest, PushNotificationType.MessageWithTextOnly) && inNoProfilePhotoV2Experiment();
    }

    public static boolean hideGuestProfilePhotoOnProfile(ReservationStatus status) {
        return status.matchesAny(ReservationStatus.Inquiry, ReservationStatus.Pending, ReservationStatus.Denied, ReservationStatus.Timedout) && inNoProfilePhotoV2ExperimentForProfilePage();
    }

    private static boolean inNoProfilePhotoV2ExperimentForProfilePage() {
        return Trebuchet.launch(TrebuchetKeys.NO_PROFILE_PHOTO_V2_FORCE_IN) || Experiments.showNoProfilePhotoOnMajorTouchpointsAndProfilePage();
    }

    private static boolean inNoProfilePhotoV2Experiment() {
        return Trebuchet.launch(TrebuchetKeys.NO_PROFILE_PHOTO_V2_FORCE_IN) || Experiments.showNoProfilePhotoOnMajorTouchpoints() || Experiments.showNoProfilePhotoOnMajorTouchpointsAndProfilePage();
    }

    public static boolean areActionNotificationsEnabled() {
        return BuildHelper.isFuture();
    }

    public static boolean showKoreanCancellationPolicy() {
        return Trebuchet.launch(TrebuchetKeys.KOREAN_CANCELLATION_POLICY);
    }

    public static boolean arePlaylistsEnabled() {
        if (MiscUtils.isTabletScreen(CoreApplication.instance().application().getApplicationContext())) {
            return false;
        }
        if (BuildHelper.isFuture() || Trebuchet.launch(TrebuchetKeys.MT_BANNERS_ENABLED)) {
            return true;
        }
        return false;
    }

    public static boolean isAddPaymentBrazilEnabled() {
        return Trebuchet.launch(TrebuchetKeys.ADD_PAYMENT_BRAZIL_ENABLED);
    }

    public static boolean showImageAnnotationsInMessageThread() {
        return Trebuchet.launch(TrebuchetKeys.IMAGE_ANNOTATIONS_MESSAGE_THREAD) && Experiments.showImageAnnotationsInMessageThread();
    }

    public static boolean enableCohostInvitationAlertsAndPushNotifications() {
        return !Trebuchet.launch(TrebuchetKeys.COHOST_INVITE_FLOW_KILL_SWITCH);
    }

    public static boolean showGuestReviewRatings() {
        if (DebugSettings.GUEST_REVIEW_RATINGS.isEnabled() || Trebuchet.launch(TrebuchetKeys.RESERVATION_SHOW_GUEST_RATINGS_FORCE_IN)) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.RESERVATION_SHOW_GUEST_RATINGS_ANDROID)) {
            return false;
        }
        if (!Trebuchet.launch(TrebuchetKeys.RESERVATION_SHOW_GUEST_RATINGS) || !Experiments.showGuestReviewRatings()) {
            return false;
        }
        return true;
    }

    public static boolean canShowExperiencesUrgencyMessage(UrgencyMessageType messageType) {
        if (BuildHelper.isFuture()) {
            return true;
        }
        switch (messageType) {
            case ExperienceViews:
                return Experiments.shouldShowExperienceViewsUrgencyMessage();
            case FriendsOnExperiences:
                return Experiments.shouldShowFriendsOnExperiencesUrgencyMessage();
            default:
                return false;
        }
    }

    public static boolean showMessageFieldOnCohostInvite() {
        return BuildHelper.isFuture() || Experiments.showMessageFieldOnInvitePage();
    }

    public static boolean areVideosEnabledOnPlaylists() {
        return false;
    }

    public static boolean showBetterFirstMessage() {
        if (Trebuchet.launch(TrebuchetKeys.BETTER_FIRST_MESSAGE_FORCE_IN)) {
            return true;
        }
        if (!BuildHelper.isFuture() || !Trebuchet.launch(TrebuchetKeys.BETTER_FIRST_MESSAGE_ALL) || !Trebuchet.launch(TrebuchetKeys.BETTER_FIRST_MESSAGE) || !Experiments.showBetterFirstMessage()) {
            return false;
        }
        return true;
    }

    public static boolean showCohostingDashboard() {
        return DebugSettings.ENABLE_COHOSTING_MOBILE_DASHBOARD.isEnabled();
    }

    public static boolean showHostPricingRules() {
        return DebugSettings.HOST_PRICE_RULES.isEnabled();
    }

    public static boolean shouldShowFlightReservations() {
        return BuildHelper.isFuture() && DebugSettings.DISPLAY_FLIGHT_RESERVATIONS.isEnabled();
    }

    public static boolean showCohostingNotification() {
        return DebugSettings.ENABLE_COHOSTING_NOTIFICATION.isEnabled() || Experiments.enableListingLevelNotification();
    }
}
