package com.airbnb.android.lib.utils.webintent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.android.cohosting.activities.AcceptCohostInvitationActivity;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.contentframework.activities.ArticleRoutingActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.identity.ReplaceVerifiedIdWithIdentityActivity;
import com.airbnb.android.core.identity.ReviewYourAccountWebViewActivity;
import com.airbnb.android.core.intents.CheckinIntents;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.intents.DLSCancelReservationActivityIntents;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.intents.ReservationObjectDeepLinkActivityIntents;
import com.airbnb.android.core.intents.ResetPasswordIntents;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.intents.VerifyEmailActivityIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.interfaces.WebIntentMatcher;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.utils.webintent.Path;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherConstants;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;
import com.airbnb.android.core.utils.webintent.WebIntentUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.EditProfileActivity;
import com.airbnb.android.lib.activities.MessageThreadWebLinkActivity;
import com.airbnb.android.lib.activities.ReviewsActivity;
import com.airbnb.android.lib.activities.reviews.ReviewFeedbackActivity;
import com.airbnb.android.lib.listyourspace.LYSAnalytics;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.tripassistant.HelpThreadActivity;
import com.airbnb.android.lib.views.JoinWishlistFragment;
import com.airbnb.android.utils.Strap;

public class WebIntentMatcherUtil implements WebIntentMatcher {
    private static final String PARAM_CODE = "code";
    private static final String SECRET = "secret";
    private static final String TAB_ALL = "all";
    private static final String TAB_EXPERIENCES = "experiences";
    private static final String TAB_HOMES = "homes";
    private static final String TAB_PLACES = "places";
    AirbnbAccountManager accountManager;

    public WebIntentMatcherUtil(AirbnbAccountManager accountManager2) {
        this.accountManager = accountManager2;
    }

    public WebIntentMatcherResult forUri(Context context, Uri uri) {
        return getMatch(context, uri, this.accountManager.getCurrentUser());
    }

    public static WebIntentMatcherResult getMatch(Context context, Uri uri, User user) {
        Path path = Path.fromUri(uri);
        Intent intent = null;
        if (path != null) {
            intent = forUriPathAndUser(context, uri, path, user);
        }
        return new WebIntentMatcherResult(uri, path, intent);
    }

    private static Intent forUriPathAndUser(Context context, Uri uri, Path path, User user) {
        Intent i = getRawIntent(context, path, uri, user);
        if (i != null) {
            i.putExtra("uri", uri.toString()).putExtra(WebIntentMatcherConstants.IS_WEB_LINK, true);
        }
        return i;
    }

    private static Intent getRawIntent(Context context, Path matchingPath, Uri uri, User currentUser) {
        switch (matchingPath) {
            case Itinerary1:
            case Itinerary2:
            case Itinerary3:
            case ReservationApprove:
                String code = uri.getQueryParameter("code");
                if (code == null || code.length() < 6) {
                    return HomeActivityIntents.intentForTrips(context);
                }
                if (uri.getQueryParameter("trip_token") != null) {
                    return HomeActivityIntents.intentForTrips(context);
                }
                return ReservationObjectDeepLinkActivityIntents.forConfirmationCode(context, code);
            case SearchBlank:
            case SearchBlank2:
                return SearchActivityIntents.intentForWebLink(context, null, uri);
            case Search1:
            case Search2:
                String query = (String) uri.getPathSegments().get(matchingPath == Path.Search1 ? 2 : 1);
                if (isTabString(query)) {
                    query = null;
                }
                return SearchActivityIntents.intentForWebLink(context, query, uri);
            case Search3:
                if (!isTabString((String) uri.getPathSegments().get(2))) {
                    return null;
                }
                return SearchActivityIntents.intentForWebLink(context, (String) uri.getPathSegments().get(1), uri);
            case Listings1:
            case Listings2:
            case Listings3:
            case Listings4:
                Long listingId = matchingPath.getId(uri);
                if (listingId != null) {
                    return P3ActivityIntents.forUri(context, listingId.longValue(), uri);
                }
                return null;
            case ReservationCreate:
                Long threadId = WebIntentUtil.getIdFromQueryParams(uri, "thread_id");
                Long listingId2 = WebIntentUtil.getIdFromQueryParams(uri, "hosting_id");
                if (threadId != null) {
                    return ThreadFragmentIntents.newIntent(context, threadId.longValue(), InboxType.Guest);
                } else if (listingId2 != null) {
                    return P3ActivityIntents.withListingId(context, listingId2.longValue(), "deep_link");
                } else {
                    return null;
                }
            case CancelReservation:
                return DLSCancelReservationActivityIntents.intent(context, (String) uri.getPathSegments().get(1));
            case ReservationMoweb:
                Long reservationId = matchingPath.getId(uri);
                if (reservationId != null) {
                    return ReservationObjectDeepLinkActivityIntents.forReservationId(context, reservationId.longValue());
                }
                return null;
            case ReservationWithCode:
                String code2 = (String) uri.getPathSegments().get(1);
                if (code2.endsWith(".")) {
                    code2 = code2.substring(0, code2.length() - 1);
                }
                return ReservationObjectDeepLinkActivityIntents.forConfirmationCode(context, code2);
            case Referrals:
                return ReferralsIntents.newIntent(context, ReferralsIntents.ENTRY_POINT_WEB_INTENT);
            case ReservationReceipt:
            case ReservationChange:
            case ReservationAlteration:
                return null;
            case ListingDeactivated:
                return null;
            case NewListing:
                LYSAnalytics.trackAction(ReferralsIntents.ENTRY_POINT_WEB_INTENT, "enter_lys", null);
                return ListYourSpaceIntents.intentForNewListing(context, ReferralsIntents.ENTRY_POINT_WEB_INTENT, null);
            case ListingDeactivated2:
                return null;
            case ListingsInvalid:
                return null;
            case YourListings:
            case ManageListing:
                return HomeActivityIntents.intentForListings(context);
            case UserProfileOrWriteReview1:
            case UserProfileOrWriteReview2:
                Long reviewId = WebIntentUtil.getIdFromQueryParams(uri, "review_id");
                if (reviewId != null) {
                    return ReviewFeedbackActivity.intentForReviewId(context, reviewId.longValue());
                }
                Long userId = matchingPath.getId(uri);
                if (userId != null) {
                    return UserProfileIntents.intentForUserId(context, userId.longValue());
                }
                return null;
            case Reviews1:
            case Reviews2:
                if (currentUser != null) {
                    return UserProfileIntents.intentForUserId(context, currentUser.getId());
                }
                return null;
            case Reviews3:
            case Reviews4:
                Long reviewId2 = matchingPath.getId(uri);
                if (reviewId2 != null) {
                    return WriteReviewActivity.newIntent(context, reviewId2.longValue());
                }
                return null;
            case InboxThread:
            case InboxThread2:
                Long threadId2 = matchingPath.getId(uri);
                if (threadId2 != null) {
                    return MessageThreadWebLinkActivity.forThreadId(context, threadId2.longValue());
                }
                return null;
            case Inbox:
                if (currentUser == null) {
                    return HomeActivityIntents.intentForDefaultTab(context);
                }
                if (currentUser.isHost()) {
                    return HomeActivityIntents.intentForHostHome(context);
                }
                return InboxActivityIntents.intentForInbox(context, InboxType.Guest);
            case InquiryCreate:
                Long listingId3 = matchingPath.getId(uri);
                if (listingId3 != null) {
                    return P3ActivityIntents.withListingId(context, listingId3.longValue(), "deep_link");
                }
                return null;
            case ReviewCreate:
                if (currentUser != null) {
                    return ReviewsActivity.intentForUser(context, currentUser, ReviewsMode.MODE_ALL);
                }
                return null;
            case Payouts:
            case Payouts2:
            case Payouts3:
            case Payouts4:
                return PayoutActivityIntents.forManagePayoutMethods(context);
            case PaymentMethods:
                return null;
            case VerifiedId1:
            case VerifiedId2:
                if (currentUser == null) {
                    return null;
                }
                return ReplaceVerifiedIdWithIdentityActivity.intent(context, currentUser.getId(), VerificationFlow.MobileHandOffNonBooking);
            case VerifyEmail:
                if (currentUser == null || TextUtils.isEmpty(uri.getQueryParameter("code"))) {
                    return null;
                }
                return VerifyEmailActivityIntents.forWeb(context, uri.getQueryParameter("code"));
            case ProfileEdit:
            case ProfileEdit2:
                if (currentUser != null) {
                    return EditProfileActivity.intentForDefault(context);
                }
                return null;
            case TaxSendToWeb:
                return null;
            case ViewOwnProfileIfLoggedIn:
                if (currentUser != null) {
                    return UserProfileIntents.intentForUserId(context, currentUser.getId());
                }
                return null;
            case ResetPassword:
                if (TextUtils.isEmpty(uri.getQueryParameter("secret"))) {
                    return null;
                }
                return ResetPasswordIntents.forWebLink(context, uri.getQueryParameter("secret"));
            case UsersTosUnknown:
                return HomeActivityIntents.intentForDefaultTab(context);
            case NotificationsSendToWeb:
                return null;
            case ReferencesSendToWeb:
                return null;
            case ForgotPassword:
                return null;
            case HostTransactionHistorySendToWeb:
                return null;
            case SettingsSendToWeb:
                return null;
            case ZendeskSendToWeb:
                return null;
            case ReactivateHostMaybe:
                return null;
            case Wishlists:
                Long wishlistId = matchingPath.getId(uri);
                if (wishlistId != null) {
                    return HomeActivityIntents.intentForWishList(context, wishlistId.longValue());
                }
                return null;
            case WishlistJoin:
                Long wishlistId2 = matchingPath.getId(uri);
                String inviteCode = uri.getQueryParameter("invite_code");
                AirbnbEventLogger.track("android_web_intent", Strap.make().mo11640kv("wish_list_join_has_user", currentUser != null));
                if (wishlistId2 == null || TextUtils.isEmpty(inviteCode) || currentUser == null) {
                    return null;
                }
                return JoinWishlistFragment.intentForWishlist(context, wishlistId2.longValue(), inviteCode);
            case WishlistsMine:
                return HomeActivityIntents.intentForWishListIndex(context);
            case ManageListingForId:
            case ManageListingForId2:
                Long listingId4 = matchingPath.getId(uri);
                if (listingId4 != null) {
                    return ManageListingIntents.intentForExistingListing(context, listingId4.longValue());
                }
                return null;
            case ManageListingInstant:
                Long listingId5 = matchingPath.getId(uri);
                if (listingId5 == null) {
                    return null;
                }
                return ManageListingIntents.intentForExistingListingSetting(context, listingId5.longValue(), SettingDeepLink.InstantBook);
            case ManageListingCalendar:
                Long listingId6 = matchingPath.getId(uri);
                if (listingId6 == null || listingId6.longValue() <= 0) {
                    return null;
                }
                return ManageListingIntents.intentForExistingListingSetting(context, listingId6.longValue(), SettingDeepLink.CalendarSettings);
            case ManageListingPricing:
                Long listingId7 = matchingPath.getId(uri);
                if (listingId7 == null) {
                    return null;
                }
                return ManageListingIntents.intentForExistingListingSetting(context, listingId7.longValue(), SettingDeepLink.Price);
            case ManageListingRegistration:
                Long listingId8 = matchingPath.getId(uri);
                if (listingId8 == null) {
                    return null;
                }
                return ManageListingIntents.intentForExistingListingSetting(context, listingId8.longValue(), SettingDeepLink.CityRegistration);
            case ManageListingCohosts:
                Long listingId9 = matchingPath.getId(uri);
                if (listingId9 != null) {
                    return CohostingIntents.intentForCohostManagementWithListingId(context, listingId9.longValue());
                }
                return null;
            case ManageListingGeneric:
                Long listingId10 = matchingPath.getId(uri);
                if (listingId10 != null) {
                    return ManageListingIntents.intentForExistingListing(context, listingId10.longValue());
                }
                return null;
            case Homepage:
                return HomeActivityIntents.intentForDefaultTab(context);
            case OneAirbnb:
                return null;
            case ClaimWebLinkGiftCard:
                return createIntentForGiftCardClaim(context, uri, currentUser);
            case ContentFrameworkStory:
            case ContentFrameworkArticle:
                Long articleId = matchingPath.getId(uri);
                if (articleId == null) {
                    return null;
                }
                return ArticleRoutingActivity.intentForArticleId(context, articleId.longValue(), ContentFrameworkUtil.WEB_LINK);
            case StoryFeed:
                return HomeActivityIntents.intentForStoryFeed(context);
            case TripAssistant:
                Long helpThreadId = matchingPath.getId(uri);
                if (helpThreadId == null || !FeatureToggles.isTripAssistantEnabled()) {
                    return null;
                }
                return HelpThreadActivity.intentForId(context, helpThreadId.longValue());
            case Experiences:
                Long experienceId = matchingPath.getId(uri);
                if (experienceId != null) {
                    return ReactNativeIntents.intentForExperienceId(context, experienceId.longValue());
                }
                return null;
            case CollectionsEarlyAccess:
            case CollectionsEarlyAccessForId:
            case CollectionsEarlyAccessForIdGeneric:
                return WebViewIntentBuilder.newBuilder(context, uri.toString()).authenticate().toIntent();
            case CohostingInvitationForCode:
                return AcceptCohostInvitationActivity.intentForInvitationCode(context, uri.getQueryParameter("code"));
            case ReviewYourAccount:
                return ReviewYourAccountWebViewActivity.intent(context, uri.toString());
            case CheckInGuide:
                Long listingId11 = matchingPath.getId(uri);
                if (listingId11 != null) {
                    return CheckinIntents.intentForListingId(context, listingId11.longValue());
                }
                break;
        }
        throw new IllegalArgumentException("Unhandled path " + matchingPath);
    }

    private static boolean isTabString(String string) {
        return TAB_ALL.equalsIgnoreCase(string) || TAB_EXPERIENCES.equalsIgnoreCase(string) || TAB_HOMES.equalsIgnoreCase(string) || TAB_PLACES.equalsIgnoreCase(string);
    }

    private static Intent createIntentForGiftCardClaim(Context context, Uri uri, User currentUser) {
        GiftCard giftCard = GiftCard.fromUri(uri);
        if (!giftCard.isValid()) {
            Toast.makeText(context, C0880R.string.gift_card_invalid_web_link_message, 1).show();
            return null;
        } else if (currentUser == null) {
            return LoginActivityIntents.intentWithGiftCardRedemption(context, giftCard);
        } else {
            return ReactNativeIntents.intentForGiftCardsRedemptionApp(context, giftCard.code(), giftCard.verificationToken());
        }
    }
}
