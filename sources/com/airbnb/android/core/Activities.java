package com.airbnb.android.core;

import android.app.Activity;

public final class Activities extends ClassRegistry {
    private Activities() {
    }

    private static Class<? extends Activity> maybeLoadActivityClass(String className) {
        return maybeLoadClass(className);
    }

    /* renamed from: p3 */
    public static Class<? extends Activity> m1188p3() {
        return maybeLoadActivityClass("com.airbnb.android.p3.P3Activity");
    }

    public static Class<? extends Activity> placeActivityPDP() {
        return maybeLoadActivityClass("com.airbnb.android.places.activities.PlaceActivityPDPActivity");
    }

    public static Class<? extends Activity> placePickAddToPlans() {
        return maybeLoadActivityClass("com.airbnb.android.places.activities.PickAddToPlansActivity");
    }

    public static Class<? extends Activity> searchIntent() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.SearchIntentActivity");
    }

    public static Class<? extends Activity> helpCenter() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.HelpCenterActivity");
    }

    public static Class<? extends Activity> checkAndLaunchVerification() {
        return maybeLoadActivityClass("com.airbnb.android.lib.mt.activities.CheckAndLaunchVerificationActivity");
    }

    public static Class<? extends Activity> userProfile() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.UserProfileActivity");
    }

    public static Class<? extends Activity> manageGuests() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.InviteGuestsActivity");
    }

    public static Class<? extends Activity> articles() {
        return maybeLoadActivityClass("com.airbnb.android.core.activities.TransparentActionBarActivity");
    }

    public static Class<? extends Activity> quickPay() {
        return maybeLoadActivityClass("com.airbnb.android.lib.payments.activities.QuickPayActivity");
    }

    public static Class<? extends Activity> reactAuthenticatedWebView() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.ReactAuthenticatedWebViewActivity");
    }

    public static Class<? extends Activity> login() {
        return maybeLoadActivityClass("com.airbnb.android.login.ui.LoginActivity");
    }

    public static Class<? extends Activity> reactNative() {
        return maybeLoadActivityClass("com.airbnb.android.react.ReactNativeActivity");
    }

    public static Class<? extends Activity> reactNativePortrait() {
        return maybeLoadActivityClass("com.airbnb.android.react.ReactNativePortraitActivity");
    }

    public static Class<? extends Activity> reactNativeModal() {
        return maybeLoadActivityClass("com.airbnb.android.react.ReactNativeModalActivity");
    }

    public static Class<? extends Activity> webView() {
        return maybeLoadActivityClass("com.airbnb.android.core.activities.WebViewActivity");
    }

    public static Class<? extends Activity> airlock() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.AirlockActivity");
    }

    public static Class<? extends Activity> home() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.HomeActivity");
    }

    public static Class<? extends Activity> inbox() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.InboxActivity");
    }

    public static Class<? extends Activity> reservationObjectDeepLink() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.ReservationObjectDeepLinkActivity");
    }

    public static Class<? extends Activity> expiredOAuthToken() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.ExpiredOauthTokenActivity");
    }

    public static Class<? extends Activity> airbnbTakeSelfie() {
        return maybeLoadActivityClass("com.airbnb.android.identity.AirbnbTakeSelfieActivity");
    }

    public static Class<? extends Activity> fiveAxiom() {
        return maybeLoadActivityClass("com.airbnb.android.lib.china5a.FiveAxiomActivity");
    }

    public static Class<? extends Activity> webIntentDispatch() {
        return maybeLoadActivityClass("com.airbnb.android.lib.utils.webintent.WebIntentDispatch");
    }

    public static Class<? extends Activity> entry() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.EntryActivity");
    }

    public static Class<? extends Activity> pickWishList() {
        return maybeLoadActivityClass("com.airbnb.android.pickwishlist.PickWishListActivity");
    }

    public static Class<? extends Activity> verifyEmail() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.VerifyEmailActivity");
    }

    public static Class<? extends Activity> accountVerification() {
        return maybeLoadActivityClass("com.airbnb.android.identity.AccountVerificationActivity");
    }

    public static Class<? extends Activity> accountVerificationStart() {
        return maybeLoadActivityClass("com.airbnb.android.identity.AccountVerificationStartActivity");
    }

    public static Class<? extends Activity> verifiedId() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.VerifiedIdActivity");
    }

    public static Class<? extends Activity> addPaymentMethod() {
        return maybeLoadActivityClass("com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity");
    }

    public static Class<? extends Activity> bookingV2() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.booking.BookingV2Activity");
    }

    public static Class<? extends Activity> postBooking() {
        return maybeLoadActivityClass("com.airbnb.android.lib.postbooking.PostBookingActivity");
    }

    public static Class<? extends Activity> booking() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.booking.BookingActivity");
    }

    public static Class<? extends Activity> guestRecovery() {
        return maybeLoadActivityClass("com.airbnb.android.guestrecovery.activities.GuestRecoveryActivity");
    }

    public static Class<? extends Activity> share() {
        return maybeLoadActivityClass("com.airbnb.android.sharing.ui.ShareActivity");
    }

    public static Class<? extends Activity> shareTripToWeChat() {
        return maybeLoadActivityClass("com.airbnb.android.lib.share.ShareYourTripActivity");
    }

    public static Class<? extends Activity> searchStoriesResult() {
        return maybeLoadActivityClass("com.airbnb.android.contentframework.activities.StorySearchResultActivity");
    }

    public static Class<? extends Activity> dlsCancelReservation() {
        return maybeLoadActivityClass("com.airbnb.android.lib.cancellation.DLSCancelReservationActivity");
    }

    public static Class<? extends Activity> hostCancelReservation() {
        return maybeLoadActivityClass("com.airbnb.android.lib.cancellation.host.HostCancellationActivity");
    }

    public static Class<? extends Activity> helpThread() {
        return maybeLoadActivityClass("com.airbnb.android.lib.tripassistant.HelpThreadActivity");
    }

    public static Class<? extends Activity> paidAmenityRouting() {
        return maybeLoadActivityClass("com.airbnb.android.lib.paidamenities.activities.PaidAmenityRoutingActivity");
    }

    public static Class<? extends Activity> cityRegistration() {
        return maybeLoadActivityClass("com.airbnb.android.cityregistration.activities.CityRegistrationActivity");
    }

    public static Class<? extends Activity> dlsManageListing() {
        return maybeLoadActivityClass("com.airbnb.android.managelisting.settings.DlsManageListingActivity");
    }

    public static Class<? extends Activity> unlist() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.UnlistActivity");
    }

    public static Class<? extends Activity> hostAmenities() {
        return maybeLoadActivityClass("com.airbnb.android.lib.paidamenities.activities.HostAmenityActivity");
    }

    public static Class<? extends Activity> createAmenities() {
        return maybeLoadActivityClass("com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity");
    }

    public static Class<? extends Activity> purchaseAmenities() {
        return maybeLoadActivityClass("com.airbnb.android.lib.paidamenities.activities.PurchaseAmenityActivity");
    }

    public static Class<? extends Activity> hostSingleCalendar() {
        return maybeLoadActivityClass("com.airbnb.android.hostcalendar.activities.HostSingleCalendarActivity");
    }

    public static Class<? extends Activity> hostReservationObject() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.HostReservationObjectActivity");
    }

    public static Class<? extends Activity> listYourSpaceDLS() {
        return maybeLoadActivityClass("com.airbnb.android.listyourspacedls.LYSHomeActivity");
    }

    public static Class<? extends Activity> selectContact() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.SelectContactActivity");
    }

    public static Class<? extends Activity> viewCheckin() {
        return maybeLoadActivityClass("com.airbnb.android.checkin.ViewCheckinActivity");
    }

    public static Class<? extends Activity> referrals() {
        return maybeLoadActivityClass("com.airbnb.android.referrals.ReferralsActivity");
    }

    public static Class<? extends Activity> hostReferrals() {
        return maybeLoadActivityClass("com.airbnb.android.host_referrals.activities.HostReferralsActivity");
    }

    public static Class<? extends Activity> debugMenu() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.DebugMenuActivity");
    }

    public static Class<? extends Activity> cohostManagement() {
        return maybeLoadActivityClass("com.airbnb.android.cohosting.activities.CohostManagementActivity");
    }

    public static Class<? extends Activity> cohostUpsell() {
        return maybeLoadActivityClass("com.airbnb.android.cohosting.activities.CohostUpsellActivity");
    }

    public static Class<? extends Activity> acceptCohostInvitation() {
        return maybeLoadActivityClass("com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity");
    }

    public static Class<? extends Activity> cohostReasons() {
        return maybeLoadActivityClass("com.airbnb.android.cohosting.activities.CohostReasonSelectionActivity");
    }

    public static Class<? extends Activity> workEmail() {
        return maybeLoadActivityClass("com.airbnb.android.lib.businesstravel.WorkEmailActivity");
    }

    public static Class<? extends Activity> businessTravelDeepLink() {
        return maybeLoadActivityClass("com.airbnb.android.lib.businesstravel.BusinessTravelDeepLinkActivity");
    }

    public static Class<? extends Activity> addPayoutMethod() {
        return maybeLoadActivityClass("com.airbnb.android.payout.create.AddPayoutMethodActivity");
    }

    public static Class<? extends Activity> legacyPayout() {
        return maybeLoadActivityClass("com.airbnb.android.lib.activities.LegacyAddPayoutActivity");
    }

    public static Class<? extends Activity> managePayoutMethods() {
        return maybeLoadActivityClass("com.airbnb.android.payout.manage.ManagePayoutActivity");
    }

    public static Class<? extends Activity> selectPayoutCountry() {
        return maybeLoadActivityClass("com.airbnb.android.payout.manage.SelectPayoutCountryActivity");
    }

    public static Class<? extends Activity> payoutRedirectWebview() {
        return maybeLoadActivityClass("com.airbnb.android.payout.create.PayoutRedirectWebviewActivity");
    }

    public static Class<? extends Activity> fixItReport() {
        return maybeLoadActivityClass("com.airbnb.android.fixit.activities.FixItReportActivity");
    }

    public static Class<? extends Activity> nestedListings() {
        return maybeLoadActivityClass("com.airbnb.android.nestedlistings.NestedListingsActivity");
    }

    public static Class<? extends Activity> mythbusters() {
        return maybeLoadActivityClass("com.airbnb.android.mythbusters.MythbustersActivity");
    }

    public static Class<? extends Activity> threadBlock() {
        return maybeLoadActivityClass("com.airbnb.android.thread.activities.ThreadBlockActivity");
    }
}
