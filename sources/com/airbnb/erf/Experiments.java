package com.airbnb.erf;

import com.airbnb.android.core.businesstravel.experiments.BusinessTravelCentralPayExperiment;
import com.airbnb.android.core.businesstravel.experiments.BusinessTravelNonVUExperiment;
import com.airbnb.android.core.businesstravel.experiments.BusinessTravelP5PromoExperiment;
import com.airbnb.android.core.businesstravel.experiments.BusinessTravelProfileExperiment;
import com.airbnb.android.core.businesstravel.experiments.BusinessTravelWorkEmailTextExperiment;
import com.airbnb.android.core.erf.experiments.AirbnbGoogleAutoCompleteProxyForChina;
import com.airbnb.android.core.erf.experiments.AlipayDirectExperiment;
import com.airbnb.android.core.erf.experiments.AlipayNonBindingFlowExperiment;
import com.airbnb.android.core.erf.experiments.AndroidInquiryIbUpsell;
import com.airbnb.android.core.erf.experiments.AndroidP4CancellationPolicy;
import com.airbnb.android.core.erf.experiments.AndroidP4SkipIdentity;
import com.airbnb.android.core.erf.experiments.AndroidP4UrgencyMessage;
import com.airbnb.android.core.erf.experiments.AndroidPayExperiment;
import com.airbnb.android.core.erf.experiments.AndroidSkipPaymentOptionsFetchOnP4;
import com.airbnb.android.core.erf.experiments.BoldP4PriceBreakdownCreditsExperiment;
import com.airbnb.android.core.erf.experiments.ChinaDomainExperiment;
import com.airbnb.android.core.erf.experiments.ChinaP3ShowPercentageRecommend;
import com.airbnb.android.core.erf.experiments.ChinaSmartPromotionUrgencyExperiment;
import com.airbnb.android.core.erf.experiments.ChinaSmartPromotionV1Experiment;
import com.airbnb.android.core.erf.experiments.ChinaStoriesEnableStoryTab;
import com.airbnb.android.core.erf.experiments.ChinaStoriesHoldout;
import com.airbnb.android.core.erf.experiments.CohostInviteShowMessageFieldExperiment;
import com.airbnb.android.core.erf.experiments.CohostingEnableListingLevelNotification;
import com.airbnb.android.core.erf.experiments.CohostingNewHostReminderAlertExperiment;
import com.airbnb.android.core.erf.experiments.ColdStartOptimizationExperiment;
import com.airbnb.android.core.erf.experiments.ContactHostButtonExperiment;
import com.airbnb.android.core.erf.experiments.ContactUploadExperiment;
import com.airbnb.android.core.erf.experiments.DLSInsightExperiment;
import com.airbnb.android.core.erf.experiments.DefaultHomeTabForFamiliesExperiment;
import com.airbnb.android.core.erf.experiments.DynamicStringsExperiment;
import com.airbnb.android.core.erf.experiments.ExperienceViewsUrgencyExperiment;
import com.airbnb.android.core.erf.experiments.ExploreEndpointExperiment;
import com.airbnb.android.core.erf.experiments.FBAccountKitPhoneVerificationExperiment;
import com.airbnb.android.core.erf.experiments.FirstMessageSuggestionsExperiment;
import com.airbnb.android.core.erf.experiments.FiveAxiomChinaExperiment;
import com.airbnb.android.core.erf.experiments.FriendsOnExperiencesUrgencyExperiment;
import com.airbnb.android.core.erf.experiments.GuestMessagingSearchExperiment;
import com.airbnb.android.core.erf.experiments.GuestSwipeToArchiveExperiment;
import com.airbnb.android.core.erf.experiments.HelpCenterReactNativeExperiment;
import com.airbnb.android.core.erf.experiments.HomeCardChinaExperiment;
import com.airbnb.android.core.erf.experiments.HomesNotHotelsExperiment;
import com.airbnb.android.core.erf.experiments.HostBedDetailsExperiment;
import com.airbnb.android.core.erf.experiments.HostCheckInGuideExperiment;
import com.airbnb.android.core.erf.experiments.HostReferralsExperiment;
import com.airbnb.android.core.erf.experiments.HostSuspensionExperiment;
import com.airbnb.android.core.erf.experiments.HostSwipeToArchiveExperiment;
import com.airbnb.android.core.erf.experiments.IdentityOnP4Experiment;
import com.airbnb.android.core.erf.experiments.IdentityVerificationFlowIntroVariantExperiment;
import com.airbnb.android.core.erf.experiments.IdentityVerificationFlowWithMitekExperiment;
import com.airbnb.android.core.erf.experiments.IdentityVerificationSelfieWithAirbnbExperiment;
import com.airbnb.android.core.erf.experiments.ImageAnnotationsMessageThreadExperiment;
import com.airbnb.android.core.erf.experiments.ItinerarySuggestionsExperiment;
import com.airbnb.android.core.erf.experiments.JumioIdTypeCountrySelectorExperiment;
import com.airbnb.android.core.erf.experiments.LYSNewHostDiscountExperiment;
import com.airbnb.android.core.erf.experiments.ListYourSpaceEntryPointExperiment;
import com.airbnb.android.core.erf.experiments.ListingExpectationsExperimentV2;
import com.airbnb.android.core.erf.experiments.LogImmediatelyExperiment;
import com.airbnb.android.core.erf.experiments.MTPostHomeBookingExperiment;
import com.airbnb.android.core.erf.experiments.ManageListingFamilyAmenitiesExperiment;
import com.airbnb.android.core.erf.experiments.ManageListingLocationAmenitiesExperiment;
import com.airbnb.android.core.erf.experiments.NativeItineraryExperiment;
import com.airbnb.android.core.erf.experiments.NestedListingExperiment;
import com.airbnb.android.core.erf.experiments.NewMay2017InsightsExperiment;
import com.airbnb.android.core.erf.experiments.NineMonthBookingWindowExperiment;
import com.airbnb.android.core.erf.experiments.NoProfilePhotoV2Experiment;
import com.airbnb.android.core.erf.experiments.OfflinePaymentEducationExperiment;
import com.airbnb.android.core.erf.experiments.OneClickWishListExperiment;
import com.airbnb.android.core.erf.experiments.P2AutocompleteVerticalsExperiment;
import com.airbnb.android.core.erf.experiments.P2PillThreeButtonsExperiment;
import com.airbnb.android.core.erf.experiments.P2PriceRangeButtonsExperiment;
import com.airbnb.android.core.erf.experiments.P2ShowMapCardExperiment;
import com.airbnb.android.core.erf.experiments.P2ShowSpaceTypeOnHomeCardExperiment;
import com.airbnb.android.core.erf.experiments.P3BTRSummaryHighlightExperiment;
import com.airbnb.android.core.erf.experiments.P3BookingRequestCopyExperiment;
import com.airbnb.android.core.erf.experiments.P3DynamicImageSizeExperiment;
import com.airbnb.android.core.erf.experiments.P3MapLocationMessageExperiment;
import com.airbnb.android.core.erf.experiments.P4RedesignExperiment;
import com.airbnb.android.core.erf.experiments.PlacesPickAddToPlans;
import com.airbnb.android.core.erf.experiments.PlacesRelatedItemsCarousel;
import com.airbnb.android.core.erf.experiments.PostLYSCohostingUpsellExperiment;
import com.airbnb.android.core.erf.experiments.PostReviewOneClickExperiment;
import com.airbnb.android.core.erf.experiments.PriceTipCopyExperiment;
import com.airbnb.android.core.erf.experiments.PricingDeepLinkExperiment;
import com.airbnb.android.core.erf.experiments.PricingHoldoutV2Experiment;
import com.airbnb.android.core.erf.experiments.ProfileCompletionNewGuestExperiment;
import com.airbnb.android.core.erf.experiments.ProfileCompletionPastBookerExperiment;
import com.airbnb.android.core.erf.experiments.PromoteProfileLanguageOrderExperiment;
import com.airbnb.android.core.erf.experiments.PxMobileResolutionCenterChangeExperiment;
import com.airbnb.android.core.erf.experiments.PxMobileResolutionCenterChangeGroupExperiment;
import com.airbnb.android.core.erf.experiments.PxMobileResolutionCenterDeeplinkExperiment;
import com.airbnb.android.core.erf.experiments.PxMobileResolutionCenterDeeplinkGroupExperiment;
import com.airbnb.android.core.erf.experiments.PxShowTripAssistantInHelpLink;
import com.airbnb.android.core.erf.experiments.PxShowTripAssistantInHelpLinkGroup;
import com.airbnb.android.core.erf.experiments.RNAOVAndKBAExperiment;
import com.airbnb.android.core.erf.experiments.ReferralsPostReviewButtonExperiment;
import com.airbnb.android.core.erf.experiments.ReferralsPostReviewDetailsExperiment;
import com.airbnb.android.core.erf.experiments.ReferralsPostReviewTitleExperiment;
import com.airbnb.android.core.erf.experiments.RejectionCancellationRecovery;
import com.airbnb.android.core.erf.experiments.RemoveCohostReasonsFlowExperiment;
import com.airbnb.android.core.erf.experiments.ReplaceVerifiedIdWithIdentityExperiment;
import com.airbnb.android.core.erf.experiments.ReservationGuestReviewRatingsExperiment;
import com.airbnb.android.core.erf.experiments.ReviewModalContentExperiment;
import com.airbnb.android.core.erf.experiments.ReviewSearchExperiment;
import com.airbnb.android.core.erf.experiments.SatoriAutocompleteExperiment;
import com.airbnb.android.core.erf.experiments.ScreenshotShareExperiment;
import com.airbnb.android.core.erf.experiments.SearchPageChinaExperiment;
import com.airbnb.android.core.erf.experiments.SearchPlaceholderChinaExperiment;
import com.airbnb.android.core.erf.experiments.SearchSettingsTotalPriceExperiment;
import com.airbnb.android.core.erf.experiments.ShareYourTripToWechatExperiment;
import com.airbnb.android.core.erf.experiments.ShowBetterFirstMessageExperiment;
import com.airbnb.android.core.erf.experiments.ShowDiscountsOnP2Experiment;
import com.airbnb.android.core.erf.experiments.ShowInstallmentsOnQuickpayExperiment;
import com.airbnb.android.core.erf.experiments.ShowUnfinishedListingOnProfileExperiment;
import com.airbnb.android.core.erf.experiments.SmartPricingExtendedExperiment;
import com.airbnb.android.core.erf.experiments.TripSupportReactNativeExperiment;
import com.airbnb.android.core.erf.experiments.TripsUpsellOnP5Experiment;
import com.airbnb.android.core.erf.experiments.UCIconExperiment;
import com.airbnb.android.core.erf.experiments.UpsellIBAfterAcceptanceExperiment;
import com.airbnb.android.core.erf.experiments.VerificationCodeAutofillExperiment;
import com.airbnb.android.core.erf.experiments.VerificationCodeAutofillStage2Experiment;
import com.airbnb.android.core.erf.experiments.VisualComponentDisplayExperiment;
import com.airbnb.android.core.erf.experiments.WhatsMyPlaceWorthExperiment;
import com.airbnb.android.core.erf.experiments.WishListImprovedFiltersUXExperiment;
import com.airbnb.android.core.payments.experiments.LaunchNewAddPaymentMethodFlow;
import com.airbnb.android.core.payments.experiments.ShowPayUFlow;
import com.airbnb.android.core.registration.FacebookGoogleLoginWrongAuthAndroid;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.erf.experiments.LYSAdditionalRequirementsTooltipCopyExperiment;
import com.airbnb.android.utils.erf.experiments.ListingExpectationsExperiment;

public final class Experiments extends _Experiments {
    public static boolean isVisibleToNonVU() {
        String assignedTreatment = getAssignment("business_travel_p3_5_generation_mobile_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("business_travel_p3_5_generation_mobile_v1", new BusinessTravelNonVUExperiment());
        }
        return "p4_checkbox".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useAddWorkEmailPromoText() {
        String assignedTreatment = getAssignment("business_travel_mobile_add_your_email_android_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("business_travel_mobile_add_your_email_android_v1", new BusinessTravelWorkEmailTextExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean allowBusinessBookings() {
        String assignedTreatment = getAssignment("android_business_travel_p4_central_pay");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_business_travel_p4_central_pay", new BusinessTravelCentralPayExperiment());
        }
        return "allow_business_bookings".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showTravelForWorkAndAddOrVerifyWorkEmail() {
        String assignedTreatment = getAssignment("business_travel_account_view_profile_mobile_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("business_travel_account_view_profile_mobile_v1", new BusinessTravelProfileExperiment());
        }
        return "treatment1".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldSendBusinessTravelP5PromoIntentPredictionRequest() {
        String assignedTreatment = getAssignment("business_travel_p5_promotion_v3");
        if (assignedTreatment == null) {
            assignedTreatment = assign("business_travel_p5_promotion_v3", new BusinessTravelP5PromoExperiment());
        }
        return "model".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showP4CancellationPolicy() {
        String assignedTreatment = getAssignment("android_p4_cancellation_policy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_cancellation_policy", new AndroidP4CancellationPolicy());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showBetterFirstMessage() {
        String assignedTreatment = getAssignment(TrebuchetKeys.BETTER_FIRST_MESSAGE_ALL);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.BETTER_FIRST_MESSAGE_ALL, new ShowBetterFirstMessageExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNewListingAllTheTime() {
        String assignedTreatment = getAssignment("android_lys_entry_points");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_lys_entry_points", new ListYourSpaceEntryPointExperiment());
        }
        return "full_throttle".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNewListingBecomeHost() {
        String assignedTreatment = getAssignment("android_lys_entry_points");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_lys_entry_points", new ListYourSpaceEntryPointExperiment());
        }
        return "nice_wording".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showDiscountsOnP2() {
        String assignedTreatment = getAssignment("pricing_lts_show_discounts_on_p2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_lts_show_discounts_on_p2", new ShowDiscountsOnP2Experiment());
        }
        return "show_discounts_on_p2".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showReviewModalContent() {
        String assignedTreatment = getAssignment("ad_inline_reservation_guest_reviews");
        if (assignedTreatment == null) {
            assignedTreatment = assign("ad_inline_reservation_guest_reviews", new ReviewModalContentExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean pillShowThreeButtons() {
        String assignedTreatment = getAssignment("android_p2_pill_three_buttons");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p2_pill_three_buttons", new P2PillThreeButtonsExperiment());
        }
        return "pill_show_three_buttons".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showMessageFieldOnInvitePage() {
        String assignedTreatment = getAssignment("android_add_optional_message_on_invite_friend_page");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_add_optional_message_on_invite_friend_page", new CohostInviteShowMessageFieldExperiment());
        }
        return "show_message_on_invite_page".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowPostReviewDetailsCopy() {
        String assignedTreatment = getAssignment("android_post_review_details_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_post_review_details_copy", new ReferralsPostReviewDetailsExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean sendLogImmediately() {
        String assignedTreatment = getAssignment("android_send_log_immediately_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_send_log_immediately_v2", new LogImmediatelyExperiment());
        }
        return "send_log_immediately".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowFriendsOnExperiencesUrgencyMessage() {
        String assignedTreatment = getAssignment("android_friends_on_experiences_urgency");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_friends_on_experiences_urgency", new FriendsOnExperiencesUrgencyExperiment());
        }
        return "show_urgency_message".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showBoldP4PriceBreakdownCredits() {
        String assignedTreatment = getAssignment("android_bold_p4_price_breakdown");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_bold_p4_price_breakdown", new BoldP4PriceBreakdownCreditsExperiment());
        }
        return "show_bold_p4_price_breakdown_credits".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableColdStartOptimization() {
        String assignedTreatment = getAssignment(ColdStartOptimizationExperiment.EXPERIMENT_NAME);
        if (assignedTreatment == null) {
            assignedTreatment = assign(ColdStartOptimizationExperiment.EXPERIMENT_NAME, new ColdStartOptimizationExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showRemoveFlow() {
        String assignedTreatment = getAssignment(TrebuchetKeys.COHOSTING_REMOVE_FLOW);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.COHOSTING_REMOVE_FLOW, new RemoveCohostReasonsFlowExperiment());
        }
        return "show_remove_flow".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showTripAssistantInHelpLinkGroup() {
        String assignedTreatment = getAssignment("mobile_entry_point_to_trip_assistant_v3");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_entry_point_to_trip_assistant_v3", new PxShowTripAssistantInHelpLinkGroup());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showTripAssistantInHelpLink() {
        String assignedTreatment = getAssignment("mobile_android_help_link_to_trip_assistant_v3");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_android_help_link_to_trip_assistant_v3", new PxShowTripAssistantInHelpLink());
        }
        return "link_to_trip_assistant_on".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldDefaultHomeTabForFamilies() {
        String assignedTreatment = getAssignment("default_home_tab_for_families_android_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("default_home_tab_for_families_android_v2", new DefaultHomeTabForFamiliesExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isAndroidPayEnabled() {
        String assignedTreatment = getAssignment("android_pay");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_pay", new AndroidPayExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showTripsUpsellOnP5() {
        String assignedTreatment = getAssignment(TrebuchetKeys.TRIPS_UPSELL_ON_P5);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.TRIPS_UPSELL_ON_P5, new TripsUpsellOnP5Experiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showRejectionCancellationRecovery() {
        String assignedTreatment = getAssignment("rejection_cancellation_recovery");
        if (assignedTreatment == null) {
            assignedTreatment = assign("rejection_cancellation_recovery", new RejectionCancellationRecovery());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showUhpAdditionalRequirementsTip() {
        String assignedTreatment = getAssignment("lys_additional_requirements_tooltip_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("lys_additional_requirements_tooltip_copy", new LYSAdditionalRequirementsTooltipCopyExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean allow9MonthBookingWindow() {
        String assignedTreatment = getAssignment(TrebuchetKeys.ALLOW_NINE_MONTH_BOOKING_WINDOW);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.ALLOW_NINE_MONTH_BOOKING_WINDOW, new NineMonthBookingWindowExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowPostReviewOneClickReferrals() {
        String assignedTreatment = getAssignment("android_post_review_gray_user_referrals");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_post_review_gray_user_referrals", new PostReviewOneClickExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showHostSideBedDetails() {
        String assignedTreatment = getAssignment("android_host_side_bed_details");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_host_side_bed_details", new HostBedDetailsExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useDLDForPricing() {
        String assignedTreatment = getAssignment("pricing_android_dld_for_web_links");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_android_dld_for_web_links", new PricingDeepLinkExperiment());
        }
        return "use_dld".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isOneClickWishListEnabled() {
        String assignedTreatment = getAssignment("android_one_click_wishlist");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_one_click_wishlist", new OneClickWishListExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowPostReviewButtonCopy() {
        String assignedTreatment = getAssignment("android_post_review_button_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_post_review_button_copy", new ReferralsPostReviewButtonExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableAndroidRelatedActivitiesCarousel() {
        String assignedTreatment = getAssignment("places_android_enable_related_activities_carousel");
        if (assignedTreatment == null) {
            assignedTreatment = assign("places_android_enable_related_activities_carousel", new PlacesRelatedItemsCarousel());
        }
        return "enable_android_related_activities_carousel".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean identityOnP4() {
        String assignedTreatment = getAssignment("android_identity_on_p4_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_identity_on_p4_v2", new IdentityOnP4Experiment());
        }
        return "identity_on_p4".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showWmpwBeforeLysFromAccountPage() {
        String assignedTreatment = getAssignment("android_whats_my_place_worth");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_whats_my_place_worth", new WhatsMyPlaceWorthExperiment());
        }
        return "show_before_lys_from_menu".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showP4UrgencyMessage() {
        String assignedTreatment = getAssignment("android_p4_urgency_message_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_urgency_message_v2", new AndroidP4UrgencyMessage());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useStaticUrgencyIcons() {
        String assignedTreatment = getAssignment("uc_new_icons_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("uc_new_icons_v2", new UCIconExperiment());
        }
        return "static_icons".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useNewUrgencyAnimatedIcons() {
        String assignedTreatment = getAssignment("uc_new_icons_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("uc_new_icons_v2", new UCIconExperiment());
        }
        return "animated_icons".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldEnableSwipeToArchiveForGuest() {
        String assignedTreatment = getAssignment("android_messaging_guest_swipe_archive");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_messaging_guest_swipe_archive", new GuestSwipeToArchiveExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useDynamicStrings() {
        String assignedTreatment = getAssignment("android_dynamic_strings");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_dynamic_strings", new DynamicStringsExperiment());
        }
        return "fetch_and_use_dynamic_strings".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowItinerarySuggestions() {
        String assignedTreatment = getAssignment("android_native_itinerary_suggestions");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_native_itinerary_suggestions", new ItinerarySuggestionsExperiment());
        }
        return "enable_suggestions".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showVariantVerificationIntro() {
        String assignedTreatment = getAssignment("android_identity_verification_flow_intro_variant");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_identity_verification_flow_intro_variant", new IdentityVerificationFlowIntroVariantExperiment());
        }
        return "show_variant_intro".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showDLSInsights() {
        String assignedTreatment = getAssignment("pricing_mobile_performance_insights_new");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_mobile_performance_insights_new", new DLSInsightExperiment());
        }
        return "show_insights".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showGuestReviewRatings() {
        String assignedTreatment = getAssignment(TrebuchetKeys.RESERVATION_SHOW_GUEST_RATINGS);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.RESERVATION_SHOW_GUEST_RATINGS, new ReservationGuestReviewRatingsExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showPricingFeatures() {
        String assignedTreatment = getAssignment("pricing_holdout_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_holdout_v2", new PricingHoldoutV2Experiment());
        }
        return "full_treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableOfflinePaymentEducation() {
        String assignedTreatment = getAssignment("offline_payment_education_android_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("offline_payment_education_android_v2", new OfflinePaymentEducationExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowGuestMessagingSearch() {
        String assignedTreatment = getAssignment("android_messaging_guest_search");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_messaging_guest_search", new GuestMessagingSearchExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useRequestApprovalCopy() {
        String assignedTreatment = getAssignment("android_rtb_cta_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_rtb_cta_copy", new P3BookingRequestCopyExperiment());
        }
        return "request_approval".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useRequestReservationCopy() {
        String assignedTreatment = getAssignment("android_rtb_cta_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_rtb_cta_copy", new P3BookingRequestCopyExperiment());
        }
        return "request_reservation".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showInstallmentsAbove() {
        String assignedTreatment = getAssignment("pricing_show_installments_quickpay");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_show_installments_quickpay", new ShowInstallmentsOnQuickpayExperiment());
        }
        return "show_installments_above".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showInstallmentsBelow() {
        String assignedTreatment = getAssignment("pricing_show_installments_quickpay");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_show_installments_quickpay", new ShowInstallmentsOnQuickpayExperiment());
        }
        return "show_installments_below".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowLocationAmenitiesML() {
        String assignedTreatment = getAssignment("mobile_ml_location_amenities");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_ml_location_amenities", new ManageListingLocationAmenitiesExperiment());
        }
        return "should_show_location_amenities".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowFamilyAmenitiesML() {
        String assignedTreatment = getAssignment("mobile_ml_family_friendly_amenities");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_ml_family_friendly_amenities", new ManageListingFamilyAmenitiesExperiment());
        }
        return "should_see_family_friendly_amenities".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableShareTripToWechat() {
        String assignedTreatment = getAssignment("android_share_trip_to_wechat_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_share_trip_to_wechat_v1", new ShareYourTripToWechatExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showMapCardOnP2() {
        String assignedTreatment = getAssignment("android_p2_show_map_card");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p2_show_map_card", new P2ShowMapCardExperiment());
        }
        return "show_map_card_on_top".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showListingExpectations() {
        String assignedTreatment = getAssignment("listing_expectations");
        if (assignedTreatment == null) {
            assignedTreatment = assign("listing_expectations", new ListingExpectationsExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isInListingExpectationsControl() {
        String assignedTreatment = getAssignment("listing_expectations");
        if (assignedTreatment == null) {
            assignedTreatment = assign("listing_expectations", new ListingExpectationsExperiment());
        }
        return "control".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showAutocompleteVerticals() {
        String assignedTreatment = getAssignment("android_autocomplete_vertical_options_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_autocomplete_vertical_options_v2", new P2AutocompleteVerticalsExperiment());
        }
        return "show_autocomplete_verticals".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isNestedListingsEnabled() {
        String assignedTreatment = getAssignment("booking_show_nested_listings");
        if (assignedTreatment == null) {
            assignedTreatment = assign("booking_show_nested_listings", new NestedListingExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showHomesNotHotelsHarderCopy() {
        String assignedTreatment = getAssignment("booking_homes_not_hotels_v1_1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("booking_homes_not_hotels_v1_1", new HomesNotHotelsExperiment());
        }
        return "treatment1".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showHomesNotHotelsSofterCopy() {
        String assignedTreatment = getAssignment("booking_homes_not_hotels_v1_1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("booking_homes_not_hotels_v1_1", new HomesNotHotelsExperiment());
        }
        return "treatment2".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useSearchPageChina() {
        String assignedTreatment = getAssignment("search_page_china_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("search_page_china_android", new SearchPageChinaExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean replaceVerifiedIdWithIdentity() {
        String assignedTreatment = getAssignment("android_replace_verified_id_with_identity_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_replace_verified_id_with_identity_v2", new ReplaceVerifiedIdWithIdentityExperiment());
        }
        return "replace_verified_id_with_identity".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showImprovedWishListFiltersUx() {
        String assignedTreatment = getAssignment("android_wl_improved_filter_ux");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_wl_improved_filter_ux", new WishListImprovedFiltersUXExperiment());
        }
        return "show_improved_wish_list_filters_ux".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showPercentageRecommend() {
        String assignedTreatment = getAssignment("china_p3_android_percentage_recommend");
        if (assignedTreatment == null) {
            assignedTreatment = assign("china_p3_android_percentage_recommend", new ChinaP3ShowPercentageRecommend());
        }
        return "show_percentage_recommend".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowInquiryIbUpsell() {
        String assignedTreatment = getAssignment("android_inquiry_ib_upsell");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_inquiry_ib_upsell", new AndroidInquiryIbUpsell());
        }
        return "show_upsell".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNewInsights() {
        String assignedTreatment = getAssignment("pricing_mobile_insights_cards_may_2017");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_mobile_insights_cards_may_2017", new NewMay2017InsightsExperiment());
        }
        return "show_new_insights".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowNativeItinerary() {
        String assignedTreatment = getAssignment("android_native_itinerary");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_native_itinerary", new NativeItineraryExperiment());
        }
        return "enable_android_native_itinerary".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableNew5AFlow() {
        String assignedTreatment = getAssignment("five_axiom_china_redesign_android_v3");
        if (assignedTreatment == null) {
            assignedTreatment = assign("five_axiom_china_redesign_android_v3", new FiveAxiomChinaExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableFBAccountKit() {
        String assignedTreatment = getAssignment("android_fb_account_kit_phone_number_verification");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_fb_account_kit_phone_number_verification", new FBAccountKitPhoneVerificationExperiment());
        }
        return "enable".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isInStoriesHoldoutGroup() {
        String assignedTreatment = getAssignment("china_stories_holdout");
        if (assignedTreatment == null) {
            assignedTreatment = assign("china_stories_holdout", new ChinaStoriesHoldout());
        }
        return "control".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isInStoriesStableGroup() {
        String assignedTreatment = getAssignment("china_stories_holdout");
        if (assignedTreatment == null) {
            assignedTreatment = assign("china_stories_holdout", new ChinaStoriesHoldout());
        }
        return "stable".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showExactLocationDisclaimerOnP3Map() {
        String assignedTreatment = getAssignment("mobile_p3_map_show_exact_location_disclaimer");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_p3_map_show_exact_location_disclaimer", new P3MapLocationMessageExperiment());
        }
        return "show_exact_location_disclaimer_on_p3_map".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldEnableSwipeToArchiveForHost() {
        String assignedTreatment = getAssignment("android_messaging_host_swipe_archive");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_messaging_host_swipe_archive", new HostSwipeToArchiveExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowExperienceViewsUrgencyMessage() {
        String assignedTreatment = getAssignment("experience_views_urgency");
        if (assignedTreatment == null) {
            assignedTreatment = assign("experience_views_urgency", new ExperienceViewsUrgencyExperiment());
        }
        return "show_urgency_message".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNewPriceRangeButtons() {
        String assignedTreatment = getAssignment("android_new_price_range_buttons");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_new_price_range_buttons", new P2PriceRangeButtonsExperiment());
        }
        return "show_new_price_range_buttons".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean canSkipIdentity() {
        String assignedTreatment = getAssignment("android_p4_redesign_allow_identity_skip");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_redesign_allow_identity_skip", new AndroidP4SkipIdentity());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean usePropertyTypeAndSpaceTypeInP2HomeCardSubtitle() {
        String assignedTreatment = getAssignment("android_p2_show_space_type_on_home_card");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p2_show_space_type_on_home_card", new P2ShowSpaceTypeOnHomeCardExperiment());
        }
        return "use_property_type_and_space_type_in_p2_home_card_subtitle".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNoProfilePhotoOnMajorTouchpoints() {
        String assignedTreatment = getAssignment("remove_profile_photo_before_accept_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("remove_profile_photo_before_accept_v2", new NoProfilePhotoV2Experiment());
        }
        return "major_touchpoints".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showNoProfilePhotoOnMajorTouchpointsAndProfilePage() {
        String assignedTreatment = getAssignment("remove_profile_photo_before_accept_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("remove_profile_photo_before_accept_v2", new NoProfilePhotoV2Experiment());
        }
        return "major_touchpoints_and_profile_page".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showResolutionCenterNewCopy() {
        String assignedTreatment = getAssignment("px_mobile_resolution_center_change_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_mobile_resolution_center_change_android", new PxMobileResolutionCenterChangeExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isUsingRNAOVAndKBA() {
        String assignedTreatment = getAssignment("mobile_trust_airlock_rn_aov_kba_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_trust_airlock_rn_aov_kba_v1", new RNAOVAndKBAExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableAirbnbChina() {
        String assignedTreatment = getAssignment("android_enable_airbnbchina");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_enable_airbnbchina", new ChinaDomainExperiment());
        }
        return "enable".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showFirstMessageSuggestions() {
        String assignedTreatment = getAssignment("mobile_first_message_suggestions");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_first_message_suggestions", new FirstMessageSuggestionsExperiment());
        }
        return "show_suggestions".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableAndroidAddToPlans() {
        String assignedTreatment = getAssignment("places_android_enable_add_to_plans");
        if (assignedTreatment == null) {
            assignedTreatment = assign("places_android_enable_add_to_plans", new PlacesPickAddToPlans());
        }
        return "enable_android_add_to_plans".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean trackVisualComponentDisplay() {
        String assignedTreatment = getAssignment("android_visual_component_display");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_visual_component_display", new VisualComponentDisplayExperiment());
        }
        return "track_visual_component_display".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isInProfileCompletionPastBookerTreatment() {
        String assignedTreatment = getAssignment("android_profile_completion_v2_1_past");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_profile_completion_v2_1_past", new ProfileCompletionPastBookerExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showImageAnnotationsInMessageThread() {
        String assignedTreatment = getAssignment("android_image_annotations_in_message_thread");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_image_annotations_in_message_thread", new ImageAnnotationsMessageThreadExperiment());
        }
        return "should_allow_image_annotations".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useNewFormat() {
        String assignedTreatment = getAssignment("android_new_explore_api_format");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_new_explore_api_format", new ExploreEndpointExperiment());
        }
        return "use_new_format".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isContactHostButtonVisible() {
        String assignedTreatment = getAssignment("contact_host_button_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("contact_host_button_android", new ContactHostButtonExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableTripSupportReactNative() {
        String assignedTreatment = getAssignment("px_trip_support_react_native_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_trip_support_react_native_v1", new TripSupportReactNativeExperiment());
        }
        return "react_native".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableAirbnbProxyForAutoComplete() {
        String assignedTreatment = getAssignment("android_use_airbnb_autocomplete_proxy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_use_airbnb_autocomplete_proxy", new AirbnbGoogleAutoCompleteProxyForChina());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useSmartPricingFixedPriceCopy() {
        String assignedTreatment = getAssignment("pricing_base_vs_fixed_price");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_base_vs_fixed_price", new SmartPricingExtendedExperiment());
        }
        return "use_fixed_price".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showListingExpectationsV2() {
        String assignedTreatment = getAssignment("listing_expectations_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("listing_expectations_v2", new ListingExpectationsExperimentV2());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showHostReferrals() {
        String assignedTreatment = getAssignment("android_host_referrals");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_host_referrals", new HostReferralsExperiment());
        }
        return "show_host_referrals".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showLYSNewHostDiscount() {
        String assignedTreatment = getAssignment("pricing_mobile_lys_new_hosting_promotion");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_mobile_lys_new_hosting_promotion", new LYSNewHostDiscountExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableAlipayNonBindingFlow() {
        String assignedTreatment = getAssignment(AlipayNonBindingFlowExperiment.EXPERIMENT_NAME);
        if (assignedTreatment == null) {
            assignedTreatment = assign(AlipayNonBindingFlowExperiment.EXPERIMENT_NAME, new AlipayNonBindingFlowExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useSatoriAutocomplete() {
        String assignedTreatment = getAssignment("android_satori_autocomplete");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_satori_autocomplete", new SatoriAutocompleteExperiment());
        }
        return "use_satori_autocomplete".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableVerificationCodeAutofill() {
        String assignedTreatment = getAssignment("verification_code_autofill_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("verification_code_autofill_android", new VerificationCodeAutofillExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useDynamicImageSizeOnP3() {
        String assignedTreatment = getAssignment("android_p3_dynamic_image_size");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p3_dynamic_image_size", new P3DynamicImageSizeExperiment());
        }
        return "use_dynamic_image_size_on_p3".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableListingLevelNotification() {
        String assignedTreatment = getAssignment("cohosting_enable_listing_level_notification");
        if (assignedTreatment == null) {
            assignedTreatment = assign("cohosting_enable_listing_level_notification", new CohostingEnableListingLevelNotification());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showResolutionCenterDeeplinkGroup() {
        String assignedTreatment = getAssignment("px_mobile_resolution_center_url_change");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_mobile_resolution_center_url_change", new PxMobileResolutionCenterDeeplinkGroupExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowPostReviewTitleCopy() {
        String assignedTreatment = getAssignment("android_post_review_title_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_post_review_title_copy", new ReferralsPostReviewTitleExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean requireVerificationsWithMitek() {
        String assignedTreatment = getAssignment("android_identity_verification_flow_with_mitek");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_identity_verification_flow_with_mitek", new IdentityVerificationFlowWithMitekExperiment());
        }
        return "use_mitek_for_id".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showHostMlCheckInGuideTool() {
        String assignedTreatment = getAssignment("android_host_enable_checkin_guide_tool");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_host_enable_checkin_guide_tool", new HostCheckInGuideExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useAirbnbForSelfie() {
        String assignedTreatment = getAssignment("android_identity_verification_selfie_with_airbnb_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_identity_verification_selfie_with_airbnb_v2", new IdentityVerificationSelfieWithAirbnbExperiment());
        }
        return "use_airbnb_for_selfie".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableSearchPlaceholderOptimization() {
        String assignedTreatment = getAssignment("search_placeholder_china_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("search_placeholder_china_android", new SearchPlaceholderChinaExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean checkSmartPromotionExperimentGroupForCN() {
        String assignedTreatment = getAssignment("cn_smart_promotion_upsell_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("cn_smart_promotion_upsell_v1", new ChinaSmartPromotionV1Experiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showCohostingNewHostReminderAlert() {
        String assignedTreatment = getAssignment("android_cohosting_new_host_reminder_dashboard_alert");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_cohosting_new_host_reminder_dashboard_alert", new CohostingNewHostReminderAlertExperiment());
        }
        return "show_alert".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableStoryTab() {
        String assignedTreatment = getAssignment("china_stories_v0_android_new_format_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("china_stories_v0_android_new_format_v1", new ChinaStoriesEnableStoryTab());
        }
        return "show_story_tab".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean removeTripsTab() {
        String assignedTreatment = getAssignment("china_stories_v0_android_new_format_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("china_stories_v0_android_new_format_v1", new ChinaStoriesEnableStoryTab());
        }
        return "remove_trips_tab".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowContactUploadRequest() {
        String assignedTreatment = getAssignment("android_contact_upload");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_contact_upload", new ContactUploadExperiment());
        }
        return "show_contact_upload".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showDlsHostSuspensionBanner() {
        String assignedTreatment = getAssignment(TrebuchetKeys.HOST_SUSPENSION);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.HOST_SUSPENSION, new HostSuspensionExperiment());
        }
        return "show_suspended".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean enableHelpCenterReactNative() {
        String assignedTreatment = getAssignment("px_help_center_react_native_v1");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_help_center_react_native_v1", new HelpCenterReactNativeExperiment());
        }
        return "react_native".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showP4Redesign() {
        String assignedTreatment = getAssignment("android_p4_redesign_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_redesign_v2", new P4RedesignExperiment());
        }
        return "p4_redesign".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showReviewsSearch() {
        String assignedTreatment = getAssignment(TrebuchetKeys.SHOW_P3_REVIEWS_SEARCH);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.SHOW_P3_REVIEWS_SEARCH, new ReviewSearchExperiment());
        }
        return "show_reviews_search".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showResolutionCenterDeeplink() {
        String assignedTreatment = getAssignment("px_mobile_resolution_center_url_change_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_mobile_resolution_center_url_change_android", new PxMobileResolutionCenterDeeplinkExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showPostLYSCohostingUpsell() {
        String assignedTreatment = getAssignment(TrebuchetKeys.COHOST_POST_LYS_UPSELL);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.COHOST_POST_LYS_UPSELL, new PostLYSCohostingUpsellExperiment());
        }
        return "show_upsell".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showSmartPromotionUrgencyForChina() {
        String assignedTreatment = getAssignment("cn_smart_promotion_upsell_v1_uc_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("cn_smart_promotion_upsell_v1_uc_android", new ChinaSmartPromotionUrgencyExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isTotalPriceSettingEnabled() {
        String assignedTreatment = getAssignment("search_settings_total_price");
        if (assignedTreatment == null) {
            assignedTreatment = assign("search_settings_total_price", new SearchSettingsTotalPriceExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldPromoteLanguage() {
        String assignedTreatment = getAssignment("android_promote_profile_language_order_experiment");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_promote_profile_language_order_experiment", new PromoteProfileLanguageOrderExperiment());
        }
        return "reorder".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowSplashPHB() {
        String assignedTreatment = getAssignment("mobile_post_home_booking");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_post_home_booking", new MTPostHomeBookingExperiment());
        }
        return "editorial".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowListPHB() {
        String assignedTreatment = getAssignment("mobile_post_home_booking");
        if (assignedTreatment == null) {
            assignedTreatment = assign("mobile_post_home_booking", new MTPostHomeBookingExperiment());
        }
        return "non_editorial".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showUseTipCopy() {
        String assignedTreatment = getAssignment("pricing_mobile_tips_copy");
        if (assignedTreatment == null) {
            assignedTreatment = assign("pricing_mobile_tips_copy", new PriceTipCopyExperiment());
        }
        return "show_use_tip".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean shouldShowUnfinishedListingOnProfile() {
        String assignedTreatment = getAssignment(TrebuchetKeys.SHOW_UNFINISHED_LISTING_ON_PROFILE);
        if (assignedTreatment == null) {
            assignedTreatment = assign(TrebuchetKeys.SHOW_UNFINISHED_LISTING_ON_PROFILE, new ShowUnfinishedListingOnProfileExperiment());
        }
        return "experiment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showBusinessTravelInP3Summary() {
        String assignedTreatment = getAssignment("android_p3_btr_summary_highlight");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p3_btr_summary_highlight", new P3BTRSummaryHighlightExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean useHomeCardChina() {
        String assignedTreatment = getAssignment("home_card_china_android_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("home_card_china_android_v2", new HomeCardChinaExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isAlipayDirectEnabled() {
        String assignedTreatment = getAssignment("alipay_direct_launch_android_v2");
        if (assignedTreatment == null) {
            assignedTreatment = assign("alipay_direct_launch_android_v2", new AlipayDirectExperiment());
        }
        return "alipay_direct_enabled".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean upsellInstantBookOnAcceptExperiment() {
        String assignedTreatment = getAssignment("android_ib_upsell_on_accept_experiment");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_ib_upsell_on_accept_experiment", new UpsellIBAfterAcceptanceExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showResolutionCenterNewCopyGroup() {
        String assignedTreatment = getAssignment("px_mobile_resolution_center_change");
        if (assignedTreatment == null) {
            assignedTreatment = assign("px_mobile_resolution_center_change", new PxMobileResolutionCenterChangeGroupExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showShareSheetForScreenshot() {
        String assignedTreatment = getAssignment("android_screenshot_share");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_screenshot_share", new ScreenshotShareExperiment());
        }
        return "show_share_sheet".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean autofillExtractedCode() {
        String assignedTreatment = getAssignment("verification_code_autofill_s2_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("verification_code_autofill_s2_android", new VerificationCodeAutofillStage2Experiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean isInProfileCompletionNewGuestTreatment() {
        String assignedTreatment = getAssignment("android_profile_completion_v2_1_new");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_profile_completion_v2_1_new", new ProfileCompletionNewGuestExperiment());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean skipPaymentOptionsFetchOnP4() {
        String assignedTreatment = getAssignment("android_skip_payment_options_fetch_on_p4");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_skip_payment_options_fetch_on_p4", new AndroidSkipPaymentOptionsFetchOnP4());
        }
        return "treatment".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showJumioIdTypeCountrySelector() {
        String assignedTreatment = getAssignment("android_identity_jumio_id_type_country_selector");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_identity_jumio_id_type_country_selector", new JumioIdTypeCountrySelectorExperiment());
        }
        return "show_jumio_id_type_country_selector".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showPayUFlow() {
        String assignedTreatment = getAssignment("android_p4_refresh_india_17_17");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_refresh_india_17_17", new ShowPayUFlow());
        }
        return "show_pay_u_flow".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean launchNewAddPaymentMethodFlow() {
        String assignedTreatment = getAssignment("android_p4_refresh_paymentmethods_add_17_16");
        if (assignedTreatment == null) {
            assignedTreatment = assign("android_p4_refresh_paymentmethods_add_17_16", new LaunchNewAddPaymentMethodFlow());
        }
        return "launch_new_add_payment_method_flow".equalsIgnoreCase(assignedTreatment);
    }

    public static boolean showFacebookGoogleWrongAuthLoginSheet() {
        String assignedTreatment = getAssignment("facebook_google_login_wrong_auth_android");
        if (assignedTreatment == null) {
            assignedTreatment = assign("facebook_google_login_wrong_auth_android", new FacebookGoogleLoginWrongAuthAndroid());
        }
        return "show_login_sheet".equalsIgnoreCase(assignedTreatment);
    }
}
