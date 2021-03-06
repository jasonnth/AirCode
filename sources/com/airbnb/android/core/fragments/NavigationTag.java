package com.airbnb.android.core.fragments;

import android.text.TextUtils;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.analytics.NavigationModeType;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.lib.fragments.alerts.AlertsFragment;
import com.airbnb.android.sharing.referral.SharingManager;
import com.jumio.p311nv.data.NVStrings;

public enum NavigationTag {
    Unknown("unknown", NavigationModeType.Unknown),
    Ignore(null, NavigationModeType.Shared),
    FindDatepicker("datepicker", NavigationModeType.GuestOnly),
    FindGuestSheet("guest_sheet", NavigationModeType.GuestOnly),
    P3("p3", NavigationModeType.GuestOnly),
    P4(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, NavigationModeType.GuestOnly),
    Booking(BookingAnalytics.EVENT_NAME, NavigationModeType.GuestOnly),
    BookingGuestSheet("booking_guest_sheet", NavigationModeType.GuestOnly),
    BookingDatepicker("booking_date_picker", NavigationModeType.GuestOnly),
    BookingSummary("booking_summary", NavigationModeType.GuestOnly),
    BookingHouseRules("booking_house_rules", NavigationModeType.GuestOnly),
    BookingFirstMessage("booking_first_message", NavigationModeType.GuestOnly),
    BookingQuickpay("booking_quickpay", NavigationModeType.GuestOnly),
    PriceBreakdown("price_breakdown", NavigationModeType.GuestOnly),
    BookingTripPurpose("homes_booking_trip_purpose", NavigationModeType.GuestOnly),
    BookingCheckinTime("booking_checkin_time", NavigationModeType.GuestOnly),
    RejectionRecovery("rejection", NavigationModeType.GuestOnly),
    ChinaGuestProfiles("china_guest_profiles", NavigationModeType.GuestOnly),
    ChinaGuestProfileSelection("guest_profile_selection", NavigationModeType.GuestOnly),
    ChinaGuestName("guest_profile_entry_name", NavigationModeType.GuestOnly),
    ChinaGuestNationality("guest_profile_entry_nationality", NavigationModeType.GuestOnly),
    ChinaGuestIdType("guest_profile_entry_id_type", NavigationModeType.GuestOnly),
    ChinaGuestGovernmentIdNumber("guest_profile_entry_government_id", NavigationModeType.GuestOnly),
    ChinaGuestPassportExpiryDate("guest_profile_entry_passport_expiration_date", NavigationModeType.GuestOnly),
    FilterSuggestion("filter_suggestion", NavigationModeType.GuestOnly),
    Listing("listing", NavigationModeType.GuestOnly),
    ListingDetails("listing_details", NavigationModeType.GuestOnly),
    ListingAmenities("listing_amenities", NavigationModeType.GuestOnly),
    ListingMap("listing_map", NavigationModeType.GuestOnly),
    ListingCancellationPolicy("listing_cancellation_policy", NavigationModeType.GuestOnly),
    ListingAdditionalPrices("listing_additional_prices", NavigationModeType.GuestOnly),
    ListingReviews("listing_reviews", NavigationModeType.GuestOnly),
    Alert("specific_alert", NavigationModeType.Shared),
    Alerts(AlertsFragment.RESULT_UPDATED_ALERTS, NavigationModeType.Shared),
    InboxContainer(null, NavigationModeType.Shared),
    Inbox("inbox", NavigationModeType.Shared),
    InboxSearch("inbox_search", NavigationModeType.Shared),
    InboxSearchResults("inbox_search_results", NavigationModeType.Shared),
    SavedMessages(MessagingJitneyLogger.SAVED_MESSAGES_LOGGING_TAG, NavigationModeType.HostOnly),
    SavedMessagesNew("new_saved_messages", NavigationModeType.HostOnly),
    SavedMessagesEdit("edit_saved_messages", NavigationModeType.HostOnly),
    Search(NVStrings.SEARCH, NavigationModeType.GuestOnly),
    MessageThread("message_thread", NavigationModeType.Shared),
    SpecialOffer("special_offer", NavigationModeType.Shared),
    SpecialOfferSheet("special_offer_sheet", NavigationModeType.HostOnly),
    RegistrationLanding("registration_landing", NavigationModeType.Shared),
    RegistrationLandingNoNewImpression(null, NavigationModeType.Shared),
    RegistrationLogin("registration_login", NavigationModeType.Shared),
    RegistrationSignup("registration_signup", NavigationModeType.Shared),
    RegistraionMoreOptions("registration_more_options", NavigationModeType.Shared),
    RegistrationName("registration_name", NavigationModeType.Shared),
    RegistrationEmail("registration_email", NavigationModeType.Shared),
    RegistrationPhone("registation_phone", NavigationModeType.Shared),
    RegistrationConfirmPhone("registration_confirm_phone", NavigationModeType.Shared),
    RegistrationPassword("registration_create_password", NavigationModeType.Shared),
    RegistrationBirthday("registration_birthday", NavigationModeType.Shared),
    RegistrationConfirmDetails("registration_confirm_details", NavigationModeType.Shared),
    RegistrationForgotPasswordEmail("forgot_password_email_sign_in", NavigationModeType.Shared),
    RegistrationForgotPasswordPhone("forgot_password_phone_sign_in", NavigationModeType.Shared),
    RegistrationResetPasswordPhone("reset_password_phone_sign_in", NavigationModeType.Shared),
    EmailAccountExist("email_account_exists", NavigationModeType.Shared),
    SocialAccountExist("social_account_exists", NavigationModeType.Shared),
    EmailResetPassword("email_reset_password", NavigationModeType.Shared),
    AppRaterDialog("app_rater_dialog", NavigationModeType.Shared),
    ContactHost("contact_host", NavigationModeType.GuestOnly),
    CouponCode("coupon_code", NavigationModeType.GuestOnly),
    GuestsDetails("guests_details", NavigationModeType.GuestOnly),
    PaymentBreakdown("payment_breakdown", NavigationModeType.GuestOnly),
    TextInput("text_input", NavigationModeType.Shared),
    UserProfile("user_profile", NavigationModeType.Shared),
    VerificationContactHostStart("verification_contact_host_start", NavigationModeType.GuestOnly),
    VerificationSignUpStart("verification_sign_up_start", NavigationModeType.GuestOnly),
    VerificationChecklistStart("verification_checklist_start", NavigationModeType.GuestOnly),
    VerificationSimplifiedIntro("verification_simplified_intro", NavigationModeType.GuestOnly),
    VerificationNoCameraDetected("verification_no_camera_detected", NavigationModeType.GuestOnly),
    VerificationNoJumioSupport("verification_no_jumio_support", NavigationModeType.GuestOnly),
    VerificationProfilePhoto("verification_add_profile_photo", NavigationModeType.GuestOnly),
    VerificationProfilePhotoConfirm("verification_add_profile_photo_confirm", NavigationModeType.GuestOnly),
    VerificationProfilePhotoError("verification_add_profile_photo_error", NavigationModeType.GuestOnly),
    VerificationCheckEmail("verification_check_email", NavigationModeType.GuestOnly),
    VerificationConfirmEmail("verification_confirm_email", NavigationModeType.GuestOnly),
    VerificationConfirmPhoneCode("verification_confirm_phone_code", NavigationModeType.GuestOnly),
    VerificationConfirmPhone("verification_confirm_phone", NavigationModeType.GuestOnly),
    VerificationPhoneFB("verification_phone_fb", NavigationModeType.GuestOnly),
    VerificationScanIdInfo("verification_scan_id_info", NavigationModeType.GuestOnly),
    VerificationScanIdIntro("verification_scan_id_intro", NavigationModeType.GuestOnly),
    VerificationScanId("verification_scan_id", NavigationModeType.GuestOnly),
    VerificationScanIdErrorExpired("verification_scan_id_error_expired", NavigationModeType.GuestOnly),
    VerificationSelfie("verification_selfie", NavigationModeType.GuestOnly),
    VerificationSelfieCameraAirbnb("verification_selfie_camera_airbnb", NavigationModeType.GuestOnly),
    VerificationSelfieCameraMisnap("verification_selfie_camera_misnap", NavigationModeType.GuestOnly),
    VerificationPhotoReview("verification_photo_double_check", NavigationModeType.GuestOnly),
    VerificationComplete("verification_complete", NavigationModeType.GuestOnly),
    VerificationScanIdWithMiSnap("verification_scan_id_with_misnap", NavigationModeType.GuestOnly),
    PaymentSelect("select_payment", NavigationModeType.GuestOnly),
    AddPaymentCountry("add_payment_country", NavigationModeType.GuestOnly),
    AddPaymentMethod("add_payment_method", NavigationModeType.GuestOnly),
    AddPaymentCardNumber("add_payment_card_number", NavigationModeType.GuestOnly),
    AddPaymentExpiration("add_payment_expiration", NavigationModeType.GuestOnly),
    AddPaymentPostalCode("add_payment_postal_code", NavigationModeType.GuestOnly),
    AddPaymentSecurityCode("add_payment_security_code", NavigationModeType.GuestOnly),
    PaymentManagerFragment(null, NavigationModeType.GuestOnly),
    BookingLandingPage("booking_landing_page", NavigationModeType.GuestOnly),
    P5TripsUpsellPage("p5_trips_upsell", NavigationModeType.GuestOnly),
    ExplorePage("explore_page", NavigationModeType.GuestOnly),
    ExploreTab("explore_tab", NavigationModeType.GuestOnly),
    ExploreFilters("explore_filters", NavigationModeType.GuestOnly),
    ExploreMap("explore_map", NavigationModeType.GuestOnly),
    Playlists("playlist_page", NavigationModeType.GuestOnly),
    WebView("web_view", NavigationModeType.Shared),
    WishList("wishlist", NavigationModeType.GuestOnly),
    WishListCollections("wishlist_collections", NavigationModeType.GuestOnly),
    WishListFilters("wishlist_filters", NavigationModeType.GuestOnly),
    WishListFriends("wishlist_friends", NavigationModeType.GuestOnly),
    Settings("settings", NavigationModeType.Shared),
    Referrals(SharingManager.REFERRALS, NavigationModeType.Shared),
    SentReferrals("sent_referrals", NavigationModeType.Shared),
    HostReferrals("host_referrals", NavigationModeType.Shared),
    SentHostReferrals("sent_host_referrals", NavigationModeType.Shared),
    ShareSheet("sharesheet", NavigationModeType.Shared),
    PayoutBreakdown("payout_breakdown", NavigationModeType.HostOnly),
    CheckinGuideGuestView("checkin_guide_guest_view", NavigationModeType.GuestOnly),
    CheckinGuideGuestEndAction("checkin_guide_guest_end_action", NavigationModeType.GuestOnly),
    CheckinGuideGuestInstructionsInvisible("checkin_guide_guest_instructions_invisible", NavigationModeType.GuestOnly),
    CheckinGuideGuestInstructionsExpired("checkin_guide_guest_instructions_expired", NavigationModeType.GuestOnly),
    CheckinGuideGuestViewStepIndex("checkin_guide_guest_view_step_index", NavigationModeType.GuestOnly),
    ManageListingPicker("manage_listing_picker", NavigationModeType.HostOnly),
    ManageListingDetailsSettings("manage_listing_details_settings", NavigationModeType.HostOnly),
    ManageListingBookingSettings("manage_listing_booking_settings", NavigationModeType.HostOnly),
    ManageListingTitle("manage_listing_title", NavigationModeType.HostOnly),
    ManageListingDescriptionSettings("manage_listing_description_settings", NavigationModeType.HostOnly),
    ManageListingSummary("manage_listing_summary", NavigationModeType.HostOnly),
    ManageListingSelectSummary("manage_listing_select_summary", NavigationModeType.HostOnly),
    ManageListingTheSpace("manage_listing_the_space", NavigationModeType.HostOnly),
    ManageListingGuestAccess("manage_listing_guest_access", NavigationModeType.HostOnly),
    ManageListingGuestInteraction("manage_listing_guest_interaction", NavigationModeType.HostOnly),
    ManageListingOtherThingsToNote("manage_listing_other_things_to_note", NavigationModeType.HostOnly),
    ManageListingNeighborhoodOverview("manage_listing_neighborhood_overview", NavigationModeType.HostOnly),
    ManageListingGettingAround("manage_listing_getting_around", NavigationModeType.HostOnly),
    ManageListingPrebookingMessage("manage_listing_prebooking_message", NavigationModeType.HostOnly),
    ManageListingPrebookingAddGreeting("manage_listing_prebooking_add_greeting", NavigationModeType.HostOnly),
    ManageListingPrebookingAddCustomQuestion("manage_listing_prebooking_add_custom_question", NavigationModeType.HostOnly),
    ManageListingAmenities("manage_listing_amenities", NavigationModeType.HostOnly),
    ManageListingHouseManual("manage_listing_house_manual", NavigationModeType.HostOnly),
    ManageListingDirections("manage_listing_directions", NavigationModeType.HostOnly),
    ManageListingSelfCheckInMethod("manage_listing_self_check_in_method", NavigationModeType.HostOnly),
    ManageListingAllCheckInMethods("select_entry_method", NavigationModeType.HostOnly),
    ManageListingCheckinGuide("checkin_instructions", NavigationModeType.HostOnly),
    ManageListingCheckinGuidePublishConfirmation("guide_is_live", NavigationModeType.HostOnly),
    ManageListingCheckinGuidePreview("checkin_guide_preview", NavigationModeType.HostOnly),
    ManageListingCheckinGuideReorderSteps("checkin_guide_reorder_steps", NavigationModeType.HostOnly),
    ManageListingCheckinGuideExample("checkin_guide_example", NavigationModeType.HostOnly),
    ManageListingEditCheckinMethod("manage_listing_edit_checkin_method", NavigationModeType.HostOnly),
    ManageListingEditCheckinGuideNote("manage_listing_edit_checkinguide_note", NavigationModeType.HostOnly),
    ManageListingAdditionalRules("manage_listing_additional_rules", NavigationModeType.HostOnly),
    ManageListingInstantBooking("manage_listing_instant_booking", NavigationModeType.HostOnly),
    ManageListingBookingTip("manage_listing_booking_tip", NavigationModeType.HostOnly),
    ManageListingSmartPricing("manage_listing_smart_pricing", NavigationModeType.HostOnly),
    ManageListingSmartPricingTip("manage_listing_smart_pricing_tip", NavigationModeType.HostOnly),
    ManageListingPricingDisclaimer("manage_listing_pricing_disclaimer", NavigationModeType.HostOnly),
    ManageListingAboutLengthOfStayDiscounts("manage_listing_about_length_of_stay_discounts", NavigationModeType.HostOnly),
    ManageListingCalendarSettings("manage_listing_calendar_settings", NavigationModeType.HostOnly),
    ManageListingAvailability("manage_listing_availability", NavigationModeType.HostOnly),
    ManageListingStatus("manage_listing_status", NavigationModeType.HostOnly),
    ManageListingUnlistReasons("manage_listing_unlist_reasons", NavigationModeType.HostOnly),
    ManageListingUnlistOtherReason("manage_listing_unlist_other_reason", NavigationModeType.HostOnly),
    ManageListingCalendarTip("manage_listing_calendar_tip", NavigationModeType.HostOnly),
    ManageListingDiscounts("manage_listing_long_term_discounts", NavigationModeType.HostOnly),
    ManageListingLastMinuteDiscounts("manage_listing_last_minute_discounts", NavigationModeType.HostOnly),
    ManageListingEarlyBirdDiscounts("manage_listing_early_bird_discounts", NavigationModeType.HostOnly),
    ManageListingHouseRules("manage_listing_house_rules", NavigationModeType.HostOnly),
    ManageListingCancellationPolicy("manage_listing_cancellation_policy", NavigationModeType.HostOnly),
    ManageListingGuestRequirements("manage_listing_guest_requirements", NavigationModeType.HostOnly),
    ManageListingGuestAdditionalRequirements("manage_listing_guest_additional_requirements", NavigationModeType.HostOnly),
    ManageListingHostingFrequency("manage_listing_hosting_frequency", NavigationModeType.HostOnly),
    ManageListingFees("manage_listing_fees", NavigationModeType.HostOnly),
    ManageListingRoomsAndGuests("manage_listing_rooms_and_guests", NavigationModeType.HostOnly),
    ManageListingBedDetails("manage_listing_bed_details", NavigationModeType.HostOnly),
    ManageListingRoomBedDetails("manage_listing_room_bed_details", NavigationModeType.HostOnly),
    ManageListingCurrency("manage_listing_currency", NavigationModeType.HostOnly),
    ManageListingWirelessInfo("manage_listing_wireless_info", NavigationModeType.HostOnly),
    ManageListingTripLength("manage_listing_trip_length", NavigationModeType.HostOnly),
    ManageListingSeasonalSettings("manage_listing_seasonal_requirement_settings", NavigationModeType.HostOnly),
    ManageListingSeasonalDatePicker("manage_listing_seasonal_date_picker", NavigationModeType.HostOnly),
    ManageListingCheckInOut("manage_listing_check_in_out", NavigationModeType.HostOnly),
    ManageListingSnoozeStatus("manage_listing_snooze_status_setting", NavigationModeType.HostOnly),
    ManageListingLicenseOrRegistrationNumber("manage_listing_license_or_registration_number", NavigationModeType.HostOnly),
    ManageListingLocation("manage_listing_location", NavigationModeType.HostOnly),
    ManageListingEditAddress("manage_listing_edit_address", NavigationModeType.HostOnly),
    ManageListingEditAddressAutoComplete("manage_listing_edit_address_auto_complete", NavigationModeType.HostOnly),
    ManageListingExactLocation("manage_listing_exact_location", NavigationModeType.HostOnly),
    ManageListingLocalLaws("manage_listing_local_laws", NavigationModeType.HostOnly),
    NestedListingsOverview("link_calendars_step0", NavigationModeType.HostOnly),
    NestedListingsChooseParent("link_calendars_step1", NavigationModeType.HostOnly),
    NestedListingsChooseChildren("link_calendars_step2", NavigationModeType.HostOnly),
    LYSLanding("lys_landing", NavigationModeType.HostOnly),
    LYSRoomType("lys_room_type", NavigationModeType.HostOnly),
    LYSRoomTypeTip("lys_room_type_tip", NavigationModeType.HostOnly),
    LYSBedrooms("lys_bedrooms", NavigationModeType.HostOnly),
    LYSBedDetails("lys_bed_details", NavigationModeType.HostOnly),
    LYSRoomBedDetails("lys_room_bed_details", NavigationModeType.HostOnly),
    LYSBathrooms("lys_bathrooms", NavigationModeType.HostOnly),
    LYSLocationAddress("lys_location_address", NavigationModeType.HostOnly),
    LYSLocationAddressAutoComplete("lys_location_address_auto_complete", NavigationModeType.HostOnly),
    LYSLocationAddressTip("lys_location_address_tip", NavigationModeType.HostOnly),
    LYSLocationCountry("lys_location_country", NavigationModeType.HostOnly),
    LYSExactLocation("lys_exact_location", NavigationModeType.HostOnly),
    LYSAmenities("lys_amenities", NavigationModeType.HostOnly),
    LYSAmenitiesTip("lys_amenities_tip", NavigationModeType.HostOnly),
    LYSSpaces("lys_spaces", NavigationModeType.HostOnly),
    LYSSpacesTip("lys_spaces_tip", NavigationModeType.HostOnly),
    LYSAddPhotos("lys_add_photos", NavigationModeType.HostOnly),
    LYSAddPhotosTip("lys_add_photos_tip", NavigationModeType.HostOnly),
    LYSPhotos("lys_photos", NavigationModeType.HostOnly),
    LYSPhotoDetails("lys_photo_details", NavigationModeType.HostOnly),
    LYSPhotoDetailsTip("lys_photo_details_tip", NavigationModeType.HostOnly),
    LYSSummary("lys_summary", NavigationModeType.HostOnly),
    LYSSummaryTip("lys_summary_tip", NavigationModeType.HostOnly),
    LYSTitle("lys_title", NavigationModeType.HostOnly),
    LYSTitleTip("lys_title_tip", NavigationModeType.HostOnly),
    LYSGuestRequirements("lys_guest_requirements", NavigationModeType.HostOnly),
    LYSGuestAdditionalRequirements("lys_guest_additional_requirements", NavigationModeType.HostOnly),
    LYSHowGuestsBook("lys_how_guests_book", NavigationModeType.HostOnly),
    LYSRequestToBookCheckList("lys_request_to_book_check_list", NavigationModeType.HostOnly),
    LYSHouseRules("lys_house_rules", NavigationModeType.HostOnly),
    LYSAdditionalHouseRules("lys_additional_house_rules", NavigationModeType.HostOnly),
    LYSAvailability("lys_availability", NavigationModeType.HostOnly),
    LYSAvailabilityTip("lys_availability_tip", NavigationModeType.HostOnly),
    LYSHostingFrequency("lys_hosting_frequency", NavigationModeType.HostOnly),
    LYSRentHistory("lys_rent_history", NavigationModeType.HostOnly),
    LYSCalendar("lys_calendar", NavigationModeType.HostOnly),
    LYSChoosePricingMode("lys_choose_pricing_mode", NavigationModeType.HostOnly),
    LYSChoosePricingModeTip("lys_choose_pricing_mode_tip", NavigationModeType.HostOnly),
    LYSCurrency("lys_currency", NavigationModeType.HostOnly),
    LYSBasePrice("lys_base_price", NavigationModeType.HostOnly),
    LYSSmartPricing("lys_smart_pricing", NavigationModeType.HostOnly),
    LYSNewHostDiscount("lys_new_host_discount", NavigationModeType.HostOnly),
    LYSDiscounts("lys_discounts", NavigationModeType.HostOnly),
    LYSDiscountsTip("lys_discounts_tip", NavigationModeType.HostOnly),
    LYSReviewGuestRequirements("lys_review_guest_requirements", NavigationModeType.HostOnly),
    LYSLocalLaws("lys_local_laws", NavigationModeType.HostOnly),
    LYSPublish("lys_publish", NavigationModeType.HostOnly),
    LYSPublishTip("lys_publish_tip", NavigationModeType.HostOnly),
    WhatsMyPlaceWorth("whats_my_place_worth", NavigationModeType.HostOnly),
    WhatsMyPlaceWorthAddress("whats_my_place_worth_address", NavigationModeType.HostOnly),
    HouseRules("additional_house_rules", NavigationModeType.HostOnly),
    EnforcableHostPreferences("set_house_rules", NavigationModeType.HostOnly),
    WhoCanBookInstantly("booking_settings", NavigationModeType.HostOnly),
    InstantBookSettings("instant_book_settings", NavigationModeType.HostOnly),
    ShareTrip("share_trip", NavigationModeType.GuestOnly),
    CalendarNoListings("calendar_no_listings", NavigationModeType.HostOnly),
    CalendarMultiListingAgenda("calendar_multi_listing_agenda", NavigationModeType.HostOnly),
    CalendarSingleListingAgenda("calendar_single_listing_agenda", NavigationModeType.HostOnly),
    CalendarSingleListingMonth("calendar_single_listing_month", NavigationModeType.HostOnly),
    CalendarEditSheet("calendar_edit_sheet", NavigationModeType.HostOnly),
    CalendarNoteView("calendar_note", NavigationModeType.HostOnly),
    CalendarNestedListing("calendar_nested_listing_blocked_date", NavigationModeType.HostOnly),
    HostReservationObject("reservation_object", NavigationModeType.HostOnly),
    HostReservationReviews("reservation_reviews", NavigationModeType.HostOnly),
    HostReservationGuestReviewRatingsUpsell("reservation_review_guest_rating_rtb_upsell", NavigationModeType.HostOnly),
    HostReservationGuestReviewRatings("reservation_guest_review_ratings", NavigationModeType.HostOnly),
    ReservationRejectionIntro("reservation_decision", NavigationModeType.HostOnly),
    ReservationRejectionDeclineIntro("decline_intro", NavigationModeType.HostOnly),
    ReservationRejectionDeclineReason("decline_flow", NavigationModeType.HostOnly),
    ReservationRejectionEditMessage("decline_flow_edit", NavigationModeType.HostOnly),
    ReservationRejectionConfirmation("decline_flow_confirmation", NavigationModeType.HostOnly),
    ReservationRejectionTips("decline_flow_tip", NavigationModeType.HostOnly),
    HostCancellationPenalties("cancellation_penalties", NavigationModeType.HostOnly),
    HostCancellationReasonSelector("cancellation_reason_selector", NavigationModeType.HostOnly),
    HostCancellationDatesUnavailable("cancellation_dates_unavailable", NavigationModeType.HostOnly),
    HostCancellationExtenuatingCircumstances("cancellation_extenuating_circumstances", NavigationModeType.HostOnly),
    HostCancellationAlterReservation("cancellation_alter_reservation", NavigationModeType.HostOnly),
    HostCancellationUndergoingMaintenance("cancellation_undergoing_maintenance", NavigationModeType.HostOnly),
    HostCancellationGuestNeedsToCancel("cancellation_guest_needs_to_cancel", NavigationModeType.HostOnly),
    HostCancellationUncomfortableGuest("cancellation_uncomfortable_guest_behavior", NavigationModeType.HostOnly),
    HostCancellationOtherReason("cancellation_other_reason", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostNotAvailable("cancel_flow_host_not_available", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostDifferentPrice("cancel_flow_host_different_price", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostGuestCancel("cancel_flow_host_guest_cancel", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostExtenuatingCircumstance("cancel_flow_host_extenuating_circumstance", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostNoPenaltyMax3("cancel_flow_host_no_penalty_max_3", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostAntiDiscrimination("cancel_flow_host_anti_discrimination", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostUncomfortable("cancel_flow_host_uncomfortable", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostPenaltyFreeTrial("cancel_flow_host_penalty_free_trial", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostNoPenalty("cancel_flow_host_no_penalty", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostReviewPenalties("cancel_flow_host_review_penalties", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostMissedEarnings("cancel_flow_host_missed_earnings", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostGuestEmpathy("cancel_flow_host_guest_empathy", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostFollowupHelp("cancel_flow_host_followup_help", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostShareConcernsNote("cancel_flow_host_share_concerns_note", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostOther("cancel_flow_host_other", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostPersonalNote("cancel_flow_host_personal_note", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostConfirmCancel("cancel_flow_host_confirm_cancel", NavigationModeType.HostOnly),
    HostReservationCancelFlowHostCancelComplete("cancel_flow_host_cancel_complete", NavigationModeType.HostOnly),
    HostStatsTopLevel("stats_home", NavigationModeType.HostOnly),
    HostStatsRatings(HostStatsJitneyLogger.PAGE_REVIEW_DETAILS, NavigationModeType.HostOnly),
    HostStatsListingPicker("stats_listing_picker", NavigationModeType.HostOnly),
    HostStatsViewsDetail("stats_views_detail", NavigationModeType.HostOnly),
    CommunityCommitmentIntroScreen("commitment_intro_screen", NavigationModeType.Shared),
    CommunityCommitmentCancelScreen("commitment_cancel_screen", NavigationModeType.Shared),
    CommunityCommitmentLearnMoreScreen("commitment_learn_more_screen", NavigationModeType.Shared),
    CommunityCommitmentFeedbackIntroScreen("commitment_feedback_screen", NavigationModeType.Shared),
    CommunityCommitmentFeedbackSubmissionScreen("commitment_feedback_submission_screen", NavigationModeType.Shared),
    MobileWebAutoAuthentication("mobile_web_auth_landing_screen", NavigationModeType.Shared),
    CityRegistrationOverview("city_registration_initial_screen", NavigationModeType.HostOnly),
    CityRegistrationExistingLicense("city_registration_license_step", NavigationModeType.HostOnly),
    CityRegistrationInputGroup("city_registration_input_group", NavigationModeType.HostOnly),
    CityRegistrationInputGroupDocTypeSelection("city_registration_input_group_doc_type_selection", NavigationModeType.HostOnly),
    CityRegistrationInputGroupDocUploadReview("city_registration_input_group_doc_upload_review", NavigationModeType.HostOnly),
    CityRegistrationNameAndEmail("city_registration_basic_info", NavigationModeType.HostOnly),
    ListingCategorizationQuestion("city_registration_categorization_question", NavigationModeType.HostOnly),
    CityRegistrationReviewAndSubmit("city_registration_review_and_submit", NavigationModeType.HostOnly),
    CityRegistrationNextSteps("city_registration_next_steps", NavigationModeType.HostOnly),
    CityRegistrationApplicationStatus("city_registration_application_status", NavigationModeType.HostOnly),
    CityRegistrationAddressAutoComplete("city_registration_address_auto_complete", NavigationModeType.HostOnly),
    CityRegistrationCountryPicker("city_registration_country_picker", NavigationModeType.HostOnly),
    PlaceActivityPDP("place_activity_pdp", NavigationModeType.GuestOnly),
    PlaceResyDatepicker("place_resy_datepicker", NavigationModeType.GuestOnly),
    StoryFeed(ContentFrameworkUtil.STORY_FEED, NavigationModeType.GuestOnly),
    StoryTopTileFeed("story_top_tile_feed", NavigationModeType.GuestOnly),
    StorySearchResult("story_search_result", NavigationModeType.GuestOnly),
    StoryTripPicker("story_trip_picker", NavigationModeType.GuestOnly),
    StoryImagePicker("story_image_picker", NavigationModeType.GuestOnly),
    UserStoryFeed("user_story_feed", NavigationModeType.Shared),
    StoryDetail("article", NavigationModeType.Shared),
    ImageAnnotations("image_annotations", NavigationModeType.Shared),
    ItineraryTimeline("itinerary_timeline", NavigationModeType.GuestOnly),
    ItineraryReservationGroup("itinerary_reservation_group", NavigationModeType.GuestOnly),
    CheckInGuideGuestEndAction("checkin_guide_guest_end_action", NavigationModeType.GuestOnly);
    
    public final NavigationModeType modeType;
    public String trackingName;

    public boolean shouldSkipLogging() {
        return TextUtils.isEmpty(this.trackingName);
    }

    private NavigationTag(String trackingName2, NavigationModeType modeType2) {
        this.trackingName = trackingName2;
        this.modeType = modeType2;
    }
}
