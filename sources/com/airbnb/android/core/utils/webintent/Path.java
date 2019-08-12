package com.airbnb.android.core.utils.webintent;

import android.net.Uri;
import java.util.regex.Pattern;

public enum Path {
    Itinerary1("/home/itinerary"),
    Listings2("/listings/\\d+"),
    ReservationCreate("/payments/book"),
    ReservationMoweb("/reservations/\\d+/.*"),
    ReservationWithCode("/reservations/[a-zA-Z0-9]{6,}\\.?"),
    ReservationChange("/reservation/change"),
    ReservationAlteration("/reservation_alterations.*"),
    ReservationApprove("/reservation/approve"),
    Itinerary2("/reservation/itinerary"),
    Itinerary3("/reservations/guest"),
    Listings4("/reservations/create/\\d+"),
    Experiences("/experiences/\\d+"),
    ReservationReceipt("/reservation/receipt"),
    Referrals("/invite"),
    ListingDeactivated("/rooms/deactivated"),
    NewListing("/rooms/new"),
    Listings3("/rooms/overview/\\d+"),
    ListingDeactivated2("/rooms/reactivate_listings"),
    ListingsInvalid("/rooms/show"),
    Listings1("/rooms/\\d+"),
    YourListings("/rooms"),
    Reviews3("/reviews/edit/\\d+"),
    Reviews4("/reviews/\\d+/edit"),
    CancelReservation("/alterations/[a-zA-Z0-9]{6,}\\.?/cancel/guest/review"),
    SearchBlank("/s"),
    SearchBlank2("/listings/search"),
    Search1("/listings/search/[^/]+"),
    Search2("/s/[^/]+"),
    Search3("/s/[^/]+/[^/]+"),
    InboxThread("/threads/\\d+"),
    Inbox("/threads"),
    InquiryCreate("/threads/create/\\d+"),
    ReviewCreate("/users/reviews_new"),
    Payouts("/users/change_default_payout"),
    VerifiedId1("/users/edit_verification"),
    ProfileEdit("/users/edit"),
    ProfileEdit2("/users/edit/\\d+"),
    TaxSendToWeb("/users/payout_preferences/taxes/.*"),
    Payouts2("/users/payout_methods"),
    Payouts3("/users/payout_preferences"),
    Payouts4("/users/payout_preferences/\\d+"),
    PaymentMethods("/users/payment_methods.*"),
    UserProfileOrWriteReview1("/users/show/\\d+"),
    ViewOwnProfileIfLoggedIn("/users/show"),
    ResetPassword("/users/set_password"),
    UsersTosUnknown("/users/tos_confirm"),
    NotificationsSendToWeb("/users/notifications"),
    ReferencesSendToWeb("/users/references"),
    ForgotPassword("/users/forgot_password"),
    HostTransactionHistorySendToWeb("/users/transaction_history"),
    SettingsSendToWeb("/users/settings"),
    ZendeskSendToWeb("/users/zendesk_login_jwt"),
    ReactivateHostMaybe("/users/reactivate"),
    Reviews1("/users/reputation"),
    Reviews2("/users/reviews"),
    UserProfileOrWriteReview2("/users/\\d+"),
    VerifiedId2("/verify"),
    VerifyEmail("/confirm_email.*"),
    InboxThread2("/z/q/\\d+"),
    Wishlists("/wishlists/\\d+"),
    WishlistJoin("/wishlists/\\d+/join"),
    WishlistsMine("/wishlists/my"),
    ManageListing("/manage-listing"),
    ManageListingForId("/manage-listing/\\d+"),
    ManageListingForId2("/manage_listing/\\d+"),
    ManageListingInstant("/manage-listing/\\d+/instant-book"),
    ManageListingCalendar("/manage-listing/\\d+/calendar"),
    ManageListingPricing("/manage-listing/\\d+/pricing"),
    ManageListingRegistration("/manage-listing/\\d+/city-registration"),
    ManageListingCohosts("/manage-listing/\\d+/co-hosts"),
    ManageListingGeneric("/manage-listing/\\d+/.*"),
    Homepage("/"),
    OneAirbnb("/thePathForOneAirbnbDoesntMatter"),
    ClaimWebLinkGiftCard("/gift-credit/accept"),
    ContentFrameworkArticle("/content/articles/\\d+"),
    ContentFrameworkStory("/content/stories/\\d+"),
    StoryFeed("/content"),
    TripAssistant("/inbox/help/\\d+"),
    CollectionsEarlyAccess("/earlyaccess"),
    CollectionsEarlyAccessForId("/earlyaccess/\\d+"),
    CollectionsEarlyAccessForIdGeneric("/earlyaccess/\\d+/.*"),
    CohostingInvitationForCode("/co-hosting/r/invitations"),
    ReviewYourAccount("/review_your_account"),
    CheckInGuide("/reservation/check-in-guide/\\d+");
    
    private final Integer idIndex;
    private final Pattern pattern;

    private static class Constants {

        /* renamed from: ID */
        public static final String f8502ID = "\\d+";
        public static final String MATCH_ALL = ".*";
        public static final String RESERVATION_CODE = "[a-zA-Z0-9]{6,}\\.?";
        public static final String SEARCH = "[^/]+";

        private Constants() {
        }
    }

    private Path(String pathRegex) {
        if (pathRegex.endsWith("/")) {
            pathRegex = pathRegex.substring(0, pathRegex.length() - 1);
        }
        this.pattern = Pattern.compile(pathRegex);
        this.idIndex = getId(pathRegex);
    }

    private static Integer getId(String pathRegex) {
        if (pathRegex.equals("") || pathRegex.startsWith("/")) {
            String[] segments = pathRegex.split("/");
            for (int i = 0; i < segments.length; i++) {
                if (Constants.f8502ID.equals(segments[i])) {
                    return Integer.valueOf(i - 1);
                }
            }
            return null;
        }
        throw new IllegalArgumentException("the path must always start with a /");
    }

    public static Path fromUri(Uri uri) {
        Path[] values;
        if (uri.getHost().equals("one.airbnb.com")) {
            return OneAirbnb;
        }
        for (Path path : values()) {
            if (path.matches(uri.getPath())) {
                return path;
            }
        }
        return null;
    }

    private boolean matches(String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return this.pattern.matcher(path).matches();
    }

    public Long getId(Uri uri) {
        if (this.idIndex != null) {
            return WebIntentUtil.getIdFromPath(uri, this.idIndex.intValue());
        }
        return null;
    }
}
