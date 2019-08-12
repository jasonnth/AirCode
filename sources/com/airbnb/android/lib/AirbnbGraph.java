package com.airbnb.android.lib;

import com.airbnb.android.apprater.AppRaterBindings;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.contentframework.ContentFrameworkBindings;
import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment;
import com.airbnb.android.core.utils.GCMHelper;
import com.airbnb.android.core.utils.JPushHelper;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.guestrecovery.GuestRecoveryBindings;
import com.airbnb.android.host_referrals.HostReferralsGraph;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.identity.IdentityBindings;
import com.airbnb.android.identity.IdentityGraph;
import com.airbnb.android.insights.InsightsGraph;
import com.airbnb.android.internal.InternalGraph;
import com.airbnb.android.itinerary.ItineraryGraph;
import com.airbnb.android.lib.activities.DebugMenuActivity;
import com.airbnb.android.lib.activities.EntryActivity;
import com.airbnb.android.lib.activities.InboxActivity;
import com.airbnb.android.lib.activities.PayWithAlipayActivity;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.activities.SearchIntentActivity;
import com.airbnb.android.lib.activities.SpecialOfferActivity;
import com.airbnb.android.lib.activities.SplashScreenActivity;
import com.airbnb.android.lib.activities.TrebuchetOverrideActivity;
import com.airbnb.android.lib.activities.UserProfileActivity;
import com.airbnb.android.lib.adapters.EditProfileDetailsAdapter;
import com.airbnb.android.lib.adapters.HHBaseAdapter;
import com.airbnb.android.lib.adapters.HostReservationObjectAdapter;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter;
import com.airbnb.android.lib.adapters.SearchCalendarAdapter;
import com.airbnb.android.lib.adapters.ThreadAdapter;
import com.airbnb.android.lib.adapters.settings.AdvancedSettingsEpoxyController;
import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialFragment;
import com.airbnb.android.lib.businesstravel.BusinessTravelWelcomeFragment;
import com.airbnb.android.lib.businesstravel.VerifyWorkEmailFragment;
import com.airbnb.android.lib.businesstravel.WorkEmailActivity;
import com.airbnb.android.lib.businesstravel.WorkEmailFragment;
import com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment;
import com.airbnb.android.lib.china5a.photo.PhotoVerificationPresenter;
import com.airbnb.android.lib.coldstart.PreloadExecutor;
import com.airbnb.android.lib.coldstart.graph.AirActivityPreloadGraph;
import com.airbnb.android.lib.coldstart.graph.AppInitGraph;
import com.airbnb.android.lib.coldstart.graph.EntryActivityPreloadGraph;
import com.airbnb.android.lib.coldstart.graph.ExperimentPreloadGraph;
import com.airbnb.android.lib.coldstart.graph.HomeActivityPreloadGraph;
import com.airbnb.android.lib.contentproviders.HostHomeWidgetProvider;
import com.airbnb.android.lib.fragments.AccountPageFragment;
import com.airbnb.android.lib.fragments.AccountSettingsFragment;
import com.airbnb.android.lib.fragments.AdvancedSettingsFragment;
import com.airbnb.android.lib.fragments.AppUpgradeDialogFragment;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment;
import com.airbnb.android.lib.fragments.EditProfileFragment;
import com.airbnb.android.lib.fragments.EndpointSelectorDialogFragment.EndpointAdapter;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment;
import com.airbnb.android.lib.fragments.InboxContainerFragment;
import com.airbnb.android.lib.fragments.PayoutSelectFragment;
import com.airbnb.android.lib.fragments.PreapproveInquiryFragment;
import com.airbnb.android.lib.fragments.ReasonPickerFragment;
import com.airbnb.android.lib.fragments.RemovePreapprovalFragment;
import com.airbnb.android.lib.fragments.ReservationCanceledFragment;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment;
import com.airbnb.android.lib.fragments.ReservationPickerFragment;
import com.airbnb.android.lib.fragments.SearchSettingsFragment;
import com.airbnb.android.lib.fragments.ShakeFeedbackDialog;
import com.airbnb.android.lib.fragments.TOSDialogFragment;
import com.airbnb.android.lib.fragments.ThreadFragment;
import com.airbnb.android.lib.fragments.UserProfileFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentCancelAccountFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentLearnMoreFragment;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentWriteFeedbackFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneChildFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneCodeChildFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment;
import com.airbnb.android.lib.fragments.inbox.BottomBarBadgeInboxHandler;
import com.airbnb.android.lib.fragments.inbox.InboxAdapter;
import com.airbnb.android.lib.fragments.inbox.InboxFragment;
import com.airbnb.android.lib.fragments.inbox.saved_messages.CreateNewSavedMessageFragment;
import com.airbnb.android.lib.fragments.inbox.saved_messages.SavedMessagesFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLandingFragment;
import com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdCountryFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdErrorFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdTypeFragment;
import com.airbnb.android.lib.fragments.verifiedid.OfflineIdChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.OnlineIdChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationChildFragment;
import com.airbnb.android.lib.fragments.verifiedid.SesameVerificationConnectFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdCompletedFragment;
import com.airbnb.android.lib.fragments.verifiedid.WelcomeScreenFragment;
import com.airbnb.android.lib.host.stats.HostDemandsDetailFragment;
import com.airbnb.android.lib.host.stats.HostListingSelectorFragment;
import com.airbnb.android.lib.host.stats.HostReviewDetailAdapter;
import com.airbnb.android.lib.host.stats.HostReviewDetailsFragment;
import com.airbnb.android.lib.host.stats.HostStatsFragment;
import com.airbnb.android.lib.identity.psb.CreateIdentificationActivity;
import com.airbnb.android.lib.identity.psb.GuestIdentificationAdapter;
import com.airbnb.android.lib.identity.psb.IdentificationNameFragment;
import com.airbnb.android.lib.paidamenities.fragments.create.BaseCreateAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.purchase.BasePurchaseAmenityFragment;
import com.airbnb.android.lib.payments.activities.QuickPayActivity;
import com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment;
import com.airbnb.android.lib.payments.creditcard.brazil.fragments.BrazilCreditCardDetailsFragment;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment;
import com.airbnb.android.lib.payments.fragments.CreditCardDetailsFragment;
import com.airbnb.android.lib.payments.fragments.PaymentOptionsFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.HomesQuickPayFragment;
import com.airbnb.android.lib.payments.quickpay.fragments.QuickPayFragment;
import com.airbnb.android.lib.postbooking.PostBookingActivity;
import com.airbnb.android.lib.postbooking.PostBookingBusinessTravelPromoFragment;
import com.airbnb.android.lib.receivers.AppUpgradeReceiver;
import com.airbnb.android.lib.receivers.LocaleChangedReceiver;
import com.airbnb.android.lib.receivers.WifiAlarmReceiver;
import com.airbnb.android.lib.reservationresponse.AcceptReservationConfirmationFragment;
import com.airbnb.android.lib.reservationresponse.AcceptReservationFragment;
import com.airbnb.android.lib.reviews.fragments.FeedbackIntroFragment;
import com.airbnb.android.lib.reviews.fragments.FeedbackSummaryFragment;
import com.airbnb.android.lib.services.HHListRemoteViewsFactory;
import com.airbnb.android.lib.services.OfficialIdIntentService;
import com.airbnb.android.lib.services.PushIntentService;
import com.airbnb.android.lib.services.TripsReservationsSyncService;
import com.airbnb.android.lib.services.ViewedListingsPersistenceService;
import com.airbnb.android.lib.services.push_notifications.MessagePushNotification;
import com.airbnb.android.lib.tasks.LocalBitmapForDisplayScalingTask;
import com.airbnb.android.lib.tripassistant.HelpThreadDialogActivity;
import com.airbnb.android.lib.tripassistant.HelpThreadFragment;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity;
import com.airbnb.android.lib.utils.webintent.WebIntentDispatch;
import com.airbnb.android.lib.views.DateAndGuestCountView;
import com.airbnb.android.lib.views.EditableCell;
import com.airbnb.android.lib.views.EmptyResultsCardView;
import com.airbnb.android.lib.views.PriceGroupedCell;
import com.airbnb.android.lib.views.PricingQuotePricingDetails;
import com.airbnb.android.lib.wishlists.WLDetailsDeeplinkInterceptorActivity;
import com.airbnb.android.listing.ListingGraph;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.login.LoginBindings;
import com.airbnb.android.login.LoginGraph;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.misnap.MiSnapGraph;
import com.airbnb.android.p011p3.P3AdditionalPriceFragment;
import com.airbnb.android.p011p3.P3Bindings;
import com.airbnb.android.payout.PayoutGraph;
import com.airbnb.android.photouploadmanager.PhotoUploadManagerGraph;
import com.airbnb.android.pickwishlist.PickWishListBindings;
import com.airbnb.android.places.PlaceGraph;
import com.airbnb.android.profile_completion.ProfileCompletionGraph;
import com.airbnb.android.react.ReactGraph;
import com.airbnb.android.referrals.ReferralsBindings;
import com.airbnb.android.registration.RegistrationBindings;
import com.airbnb.android.sharing.SharingGraph;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.superhero.SuperHeroGraph;
import com.airbnb.android.wishlists.WishListDetailsGraph;
import com.airbnb.android.wishlists.WishListIndexFragment;

public interface AirbnbGraph extends AppRaterBindings, BookingGraph, CheckInDataBindings, CohostingGraph, ContentFrameworkBindings, BaseGraph, CoreGraph, GuestRecoveryBindings, HostReferralsGraph, HostCalendarGraph, IdentityBindings, IdentityGraph, InsightsGraph, InternalGraph, ItineraryGraph, ExploreBindings, LibBindings, RiskGraph, AirActivityPreloadGraph, AppInitGraph, EntryActivityPreloadGraph, ExperimentPreloadGraph, HomeActivityPreloadGraph, ListingGraph, ListYourSpaceDLSGraph, LoginBindings, LoginGraph, ManageListingGraph, MiSnapGraph, P3Bindings, PayoutGraph, PhotoUploadManagerGraph, PickWishListBindings, PlaceGraph, ProfileCompletionGraph, ReactGraph, ReferralsBindings, RegistrationBindings, SharingGraph, SuperHeroGraph, WishListDetailsGraph {
    void inject(ButtonPartnership buttonPartnership);

    void inject(AirActivity airActivity);

    void inject(AirFragment airFragment);

    void inject(GuestPickerFragment guestPickerFragment);

    void inject(GCMHelper gCMHelper);

    void inject(JPushHelper jPushHelper);

    void inject(AirWebView airWebView);

    void inject(AirbnbApplication airbnbApplication);

    void inject(ReferralBroadcastReceiver referralBroadcastReceiver);

    void inject(DebugMenuActivity debugMenuActivity);

    void inject(EntryActivity entryActivity);

    void inject(InboxActivity inboxActivity);

    void inject(PayWithAlipayActivity payWithAlipayActivity);

    void inject(ReservationResponseActivity reservationResponseActivity);

    void inject(SearchIntentActivity searchIntentActivity);

    void inject(SpecialOfferActivity specialOfferActivity);

    void inject(SplashScreenActivity splashScreenActivity);

    void inject(TrebuchetOverrideActivity trebuchetOverrideActivity);

    void inject(UserProfileActivity userProfileActivity);

    void inject(EditProfileDetailsAdapter editProfileDetailsAdapter);

    void inject(HHBaseAdapter hHBaseAdapter);

    void inject(HostReservationObjectAdapter hostReservationObjectAdapter);

    void inject(ReservationObjectAdapter reservationObjectAdapter);

    void inject(SearchCalendarAdapter searchCalendarAdapter);

    void inject(ThreadAdapter threadAdapter);

    void inject(AdvancedSettingsEpoxyController advancedSettingsEpoxyController);

    void inject(BusinessTravelInterstitialFragment businessTravelInterstitialFragment);

    void inject(BusinessTravelWelcomeFragment businessTravelWelcomeFragment);

    void inject(VerifyWorkEmailFragment verifyWorkEmailFragment);

    void inject(WorkEmailActivity workEmailActivity);

    void inject(WorkEmailFragment workEmailFragment);

    void inject(PhotoVerificationFragment photoVerificationFragment);

    void inject(PhotoVerificationPresenter photoVerificationPresenter);

    void inject(PreloadExecutor preloadExecutor);

    void inject(HostHomeWidgetProvider hostHomeWidgetProvider);

    void inject(AccountPageFragment accountPageFragment);

    void inject(AccountSettingsFragment accountSettingsFragment);

    void inject(AdvancedSettingsFragment advancedSettingsFragment);

    void inject(AppUpgradeDialogFragment appUpgradeDialogFragment);

    void inject(DLSReservationObjectFragment dLSReservationObjectFragment);

    void inject(EditProfileFragment editProfileFragment);

    void inject(EndpointAdapter endpointAdapter);

    void inject(HostReservationObjectFragment hostReservationObjectFragment);

    void inject(InboxContainerFragment inboxContainerFragment);

    void inject(PayoutSelectFragment payoutSelectFragment);

    void inject(PreapproveInquiryFragment preapproveInquiryFragment);

    void inject(ReasonPickerFragment reasonPickerFragment);

    void inject(RemovePreapprovalFragment removePreapprovalFragment);

    void inject(ReservationCanceledFragment reservationCanceledFragment);

    void inject(ReservationCancellationWithUserInputFragment reservationCancellationWithUserInputFragment);

    void inject(ReservationPickerFragment reservationPickerFragment);

    void inject(SearchSettingsFragment searchSettingsFragment);

    void inject(ShakeFeedbackDialog shakeFeedbackDialog);

    void inject(TOSDialogFragment tOSDialogFragment);

    void inject(ThreadFragment threadFragment);

    void inject(UserProfileFragment userProfileFragment);

    void inject(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment);

    void inject(CommunityCommitmentFragment communityCommitmentFragment);

    void inject(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment);

    void inject(CommunityCommitmentWriteFeedbackFragment communityCommitmentWriteFeedbackFragment);

    void inject(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment);

    void inject(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment);

    void inject(CompleteProfilePhotoFragment completeProfilePhotoFragment);

    void inject(BottomBarBadgeInboxHandler bottomBarBadgeInboxHandler);

    void inject(InboxAdapter inboxAdapter);

    void inject(InboxFragment inboxFragment);

    void inject(CreateNewSavedMessageFragment createNewSavedMessageFragment);

    void inject(SavedMessagesFragment savedMessagesFragment);

    void inject(ReservationResponseLandingFragment reservationResponseLandingFragment);

    void inject(PhoneVerificationFragment phoneVerificationFragment);

    void inject(com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment photoVerificationFragment);

    void inject(OfficialIdCountryFragment officialIdCountryFragment);

    void inject(OfficialIdErrorFragment officialIdErrorFragment);

    void inject(OfficialIdPhotoSelectionFragment officialIdPhotoSelectionFragment);

    void inject(OfficialIdTypeFragment officialIdTypeFragment);

    void inject(OfflineIdChildFragment offlineIdChildFragment);

    void inject(OnlineIdChildFragment onlineIdChildFragment);

    void inject(SesameVerificationChildFragment sesameVerificationChildFragment);

    void inject(SesameVerificationConnectFragment sesameVerificationConnectFragment);

    void inject(VerifiedIdCompletedFragment verifiedIdCompletedFragment);

    void inject(WelcomeScreenFragment welcomeScreenFragment);

    void inject(HostDemandsDetailFragment hostDemandsDetailFragment);

    void inject(HostListingSelectorFragment hostListingSelectorFragment);

    void inject(HostReviewDetailAdapter hostReviewDetailAdapter);

    void inject(HostReviewDetailsFragment hostReviewDetailsFragment);

    void inject(HostStatsFragment hostStatsFragment);

    void inject(CreateIdentificationActivity createIdentificationActivity);

    void inject(GuestIdentificationAdapter guestIdentificationAdapter);

    void inject(IdentificationNameFragment identificationNameFragment);

    void inject(BaseCreateAmenityFragment baseCreateAmenityFragment);

    void inject(HostAmenityListFragment hostAmenityListFragment);

    void inject(BasePendingAmenityFragment basePendingAmenityFragment);

    void inject(BasePurchaseAmenityFragment basePurchaseAmenityFragment);

    void inject(QuickPayActivity quickPayActivity);

    void inject(AddPaymentMethodFragment addPaymentMethodFragment);

    void inject(SelectBillingCountryFragment selectBillingCountryFragment);

    void inject(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment);

    void inject(AddCouponCodeFragment addCouponCodeFragment);

    void inject(CreditCardDetailsFragment creditCardDetailsFragment);

    void inject(PaymentOptionsFragment paymentOptionsFragment);

    void inject(HomesQuickPayFragment homesQuickPayFragment);

    void inject(QuickPayFragment quickPayFragment);

    void inject(PostBookingActivity postBookingActivity);

    void inject(PostBookingBusinessTravelPromoFragment postBookingBusinessTravelPromoFragment);

    void inject(AppUpgradeReceiver appUpgradeReceiver);

    void inject(LocaleChangedReceiver localeChangedReceiver);

    void inject(WifiAlarmReceiver wifiAlarmReceiver);

    void inject(AcceptReservationConfirmationFragment acceptReservationConfirmationFragment);

    void inject(AcceptReservationFragment acceptReservationFragment);

    void inject(FeedbackIntroFragment feedbackIntroFragment);

    void inject(FeedbackSummaryFragment feedbackSummaryFragment);

    void inject(HHListRemoteViewsFactory hHListRemoteViewsFactory);

    void inject(OfficialIdIntentService officialIdIntentService);

    void inject(PushIntentService pushIntentService);

    void inject(TripsReservationsSyncService tripsReservationsSyncService);

    void inject(ViewedListingsPersistenceService viewedListingsPersistenceService);

    void inject(MessagePushNotification messagePushNotification);

    void inject(LocalBitmapForDisplayScalingTask localBitmapForDisplayScalingTask);

    void inject(HelpThreadDialogActivity helpThreadDialogActivity);

    void inject(HelpThreadFragment helpThreadFragment);

    void inject(ErfOverrideActivity erfOverrideActivity);

    void inject(WebIntentDispatch webIntentDispatch);

    void inject(DateAndGuestCountView dateAndGuestCountView);

    void inject(EditableCell editableCell);

    void inject(EmptyResultsCardView emptyResultsCardView);

    void inject(PriceGroupedCell priceGroupedCell);

    void inject(PricingQuotePricingDetails pricingQuotePricingDetails);

    void inject(WLDetailsDeeplinkInterceptorActivity wLDetailsDeeplinkInterceptorActivity);

    void inject(P3AdditionalPriceFragment p3AdditionalPriceFragment);

    void inject(SharingManager sharingManager);

    void inject(WishListIndexFragment wishListIndexFragment);
}
