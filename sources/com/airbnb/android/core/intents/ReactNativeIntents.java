package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.identity.IdentityReactNativeInfoType;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.utils.jitney.JitneyConverterUtils;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.ParcelableStringMap;
import com.airbnb.android.utils.ReactNativeIntentUtils;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.google.common.collect.FluentIterable;
import com.google.common.primitives.UnsignedLongs;
import java.util.ArrayList;
import java.util.List;

public final class ReactNativeIntents {
    private static final String ALTERATIONS_LAUNCH_POINT_CREATE_ALTERATION = "Alterations/CreateAlteration";
    private static final String ALTERATIONS_LAUNCH_POINT_VIEW_ALTERATION = "Alterations/ViewAlteration";
    private static final String APP_AIRLOCK = "Airlock/FrictionManager";
    private static final String APP_AIRLOCK_CHARGEBACK_APPEAL = "Airlock/ChargebackAppeal";
    private static final String APP_CITY_HOSTS_MANAGER = "CityHostsManager";
    private static final String APP_CITY_HOSTS_REVIEW_INSTANCE = "CityHostsManager/Feedback/ConfirmGuestAttendance";
    private static final String APP_CITY_HOSTS_TEMPLATE_INSTANCE = "CityHostsManager/view_trip_instance";
    private static final String APP_CONTACT_HOST = "ContactHost/ContactHostEntryScreen";
    private static final String APP_DLS_EXPLORER = "DLSExplorer";
    private static final String APP_FLAG_CONTENT = "FlagContent";
    private static final String APP_GIFTCARDS = "GiftCards";
    private static final String APP_GIFTCARDS_REDEMPTION = "GiftCards/redeemRoot";
    private static final String APP_GIFTCARDS_SENT = "GiftCards/sent";
    private static final String APP_GUIDEBOOK = "GuidebookApp";
    private static final String APP_HELP_CENTER = "HelpCenter";
    private static final String APP_ITINERARY = "Itinerary";
    private static final String APP_ITINERARY_CARD = "Itinerary/ItineraryCardScreen";
    private static final String APP_ITINERARY_PENDING_SCREEN = "Itinerary/ItineraryPendingScreen";
    private static final String APP_LISTING_SELF_CHECKIN_SETTINGS = "ListingSelfCheckinSettings/chooseType";
    private static final String APP_REVIEWS = "Reviews";
    private static final String APP_RN_MENU = "RNAppsMenu";
    private static final String APP_STORIES_SEARCH = "Stories/search";
    private static final String APP_SUPPORT_CHAT = "SupportChat";
    private static final String APP_SUPPORT_MESSAGING_THREAD = "SupportChat/ThreadScreen";
    private static final String APP_TRIP_SUPPORT = "TripSupport";
    private static final String APP_VERIFICATIONS_ID_INFO = "Verifications/Info";
    private static final String APP_VERIFICATIONS_ID_TYPE = "Verifications/GovernmentIDType";
    private static final String APP_VERIFICATIONS_ID_TYPE_LIGHT = "Verifications/GovernmentIDTypeLight";
    private static final String APP_VERIFICATIONS_ID_TYPE_V2 = "Verifications/GovernmentIDTypeV2";
    private static final String APP_VERIFICATIONS_SELFIE_REVIEW_TYPE = "Verifications/SelfieReview";
    private static final String BOOKING_CONFIRMED = "CityHostsGuest/booking/booking_confirmed";
    private static final String REACT_NATIVE_DATE_FORMAT = "yyyy-M-d";
    public static final String SCREEN_CITY_HOSTS_GUEST_SINGLE_EXPERIENCE_TEMPLATES = "CityHostsGuest/single_experience_template";
    private static final String SCREEN_CITY_HOSTS_GUEST_TEMPLATES = "CityHostsGuest/template";
    private static final String SCREEN_CITY_PLACE_PDP = "Guidebook/place";
    private static final String SCREEN_COHOSTING_CONTRACT = "Cohosting/CohostingContract";
    private static final String SCREEN_COHOSTING_INVITE_FRIEND = "Cohosting/CohostingInviteFriend";
    private static final String SCREEN_COHOSTING_SHARE_EARNINGS = "Cohosting/CohostingShareEarnings";
    private static final String SCREEN_COHOSTING_STOP_SHARE_EARNINGS = "Cohosting/CohostingStopShareEarnings";
    private static final String SCREEN_GUIDEBOOK_DETOUR = "Guidebook/detour";
    private static final String SCREEN_GUIDEBOOK_FEEDBACK = "Guidebook/Feedback";
    private static final String SCREEN_GUIDEBOOK_HOME_HOST_MAP = "Guidebook/HomeHostMap";
    private static final String SCREEN_GUIDEBOOK_INSIDER = "Guidebook/insider";
    private static final String SCREEN_GUIDEBOOK_MEETUPCOLLECTION = "Guidebook/meetupCollection";
    private static final String SCREEN_GUIDEBOOK_SUBCATEGORY = "Guidebook/subcategory";
    private static final String SCREEN_HOST_MAGICAL_TRIPS_NUX = "CityHostsGuest/new_user_experience";
    private static final String SCREEN_HOST_STATS_EARNINGS = "HostStats/Earnings/AllListings";
    private static final String SCREEN_HOST_STATS_SUPERHOST_PROGRESS = "HostStats/Superhost/Progress";
    private static final String SCREEN_HOST_STATS_TRANSACTIONS = "HostStats/Transactions";
    private static final String SCREEN_HOUSE_RULES_AND_EXPECTATIONS = "HouseRulesAndExpectations/EditExpectationsScreen";

    public static Intent intentForAlterations(Context context, Reservation reservation, boolean isHost) {
        boolean hasPendingAlteration = reservation.hasPendingAlterations();
        return modalIntent(context, hasPendingAlteration ? ALTERATIONS_LAUNCH_POINT_VIEW_ALTERATION : ALTERATIONS_LAUNCH_POINT_CREATE_ALTERATION, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("confirmationCode", reservation.getConfirmationCode())).putBoolean("viewOnly", !isHost && reservation.isSharedItinerary())).putBoolean("isHost", isHost)).toBundle());
    }

    public static Intent intentForHostStatsEarnings(Context context) {
        return intent(context, SCREEN_HOST_STATS_EARNINGS, null);
    }

    public static Intent intentForHostStatsSuperhostProgress(Context context) {
        return intent(context, SCREEN_HOST_STATS_SUPERHOST_PROGRESS, null);
    }

    public static Intent intentForHostStatsTransactions(Context context) {
        return intent(context, SCREEN_HOST_STATS_TRANSACTIONS, null);
    }

    public static Intent intentForStoriesSearch(Context context) {
        return intent(context, APP_STORIES_SEARCH, null);
    }

    public static Intent intentForFlagContent(Context context, long flaggableId, Long userFlagId, FlagContent content) {
        BundleBuilder builder = (BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("flaggableId", flaggableId)).putString("flaggableType", content.getServerKey());
        if (userFlagId != null) {
            builder.putLong("userFlagId", userFlagId.longValue());
        }
        return intent(context, APP_FLAG_CONTENT, builder.toBundle());
    }

    public static Intent intentForHouseRulesAndExpectations(Context context, long listingId, List<ListingExpectation> expectations) {
        return modalIntent(context, SCREEN_HOUSE_RULES_AND_EXPECTATIONS, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("listingId", String.valueOf(listingId))).putParcelableArrayList("initialExpectations", new ArrayList(FluentIterable.from((Iterable<E>) expectations).transform(ReactNativeIntents$$Lambda$1.lambdaFactory$()).toList()))).toBundle());
    }

    /* access modifiers changed from: private */
    public static Bundle getListingExpectationBundle(ListingExpectation expectation) {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("title", expectation.getTitle())).putString("description", expectation.getDescription())).putString("placeholder", expectation.getPlaceholder())).putString("added_details", expectation.getAddedDetails())).putBoolean("checked", expectation.isChecked())).putString("type", expectation.getType())).toBundle();
    }

    public static Intent intentForCohostingContract(Context context, long contractId, boolean isListingAdmin, Listing listing) {
        return intent(context, SCREEN_COHOSTING_CONTRACT, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("contractId", contractId)).putBoolean("isListingAdmin", isListingAdmin)).putBundle("listing", ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putDouble("lat", listing.getLatLng().lat())).putDouble("lng", listing.getLatLng().lng())).putString("name", listing.getName())).putString("property_type", listing.getPropertyType())).putString("picture_url", listing.getPictureUrl())).putString("address", listing.getAddress())).toBundle())).toBundle());
    }

    public static Intent intentForCohostingShareEarnings(Context context, long ownerId, long cohostId, long listingId, boolean fromAdditionalHost, String userName, String existingPaidCohostUserName) {
        return intent(context, SCREEN_COHOSTING_SHARE_EARNINGS, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("ownerId", ownerId)).putLong("cohostId", cohostId)).putLong("listingId", listingId)).putBoolean("fromAdditionalHost", fromAdditionalHost)).putString("userName", userName)).putString("existingPaidCohostUserName", existingPaidCohostUserName)).toBundle());
    }

    public static Intent intentForCohostingStopShareEarnings(Context context, long contractId, String userName) {
        return intent(context, SCREEN_COHOSTING_STOP_SHARE_EARNINGS, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("contractId", contractId)).putString("userName", userName)).toBundle());
    }

    public static Intent intentForCohostingInviteFriend(Context context, long listingId, String existingPaidCohostUserName) {
        return modalIntent(context, SCREEN_COHOSTING_INVITE_FRIEND, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("listingId", listingId)).putString("existingPaidCohostUserName", existingPaidCohostUserName)).toBundle());
    }

    public static Intent intentForDeepLink(Context context, String screenName, Bundle originalProps) {
        return intent(context, screenName, ((BundleBuilder) new BundleBuilder(originalProps).putBoolean("fromDeepLink", true)).toBundle());
    }

    public static Intent intentForDLSExplorer(Context context) {
        return intent(context, APP_DLS_EXPLORER, null);
    }

    public static Intent intentForRNAppsMenu(Context context) {
        return intent(context, APP_RN_MENU, null);
    }

    public static Intent intentForSupportChat(Context context) {
        return intent(context, APP_SUPPORT_CHAT, null);
    }

    public static Intent deepLinkedIntentForSupportMessagingThread(Context context, Bundle parameters) {
        if (Trebuchet.launch(TrebuchetKeys.SUPPORT_MESSAGING_IN_INBOX, false)) {
            return intentForSupportMessagingThread(context, Long.valueOf(Long.parseLong(parameters.getString("id"))).longValue());
        }
        return HomeActivityIntents.intentForTravelInbox(context);
    }

    public static Intent intentForSupportMessagingThread(Context context, long threadId) {
        return intent(context, APP_SUPPORT_MESSAGING_THREAD, ((BundleBuilder) new BundleBuilder().putLong(ThreadDataModel.THREADID, threadId)).toBundle());
    }

    public static Intent intentForCityHostsScheduledTemplate(Context context, long tripId) {
        return intent(context, APP_CITY_HOSTS_TEMPLATE_INSTANCE, ((BundleBuilder) new BundleBuilder().putLong(HomeActivityIntents.ARG_TRIP_HOST_SCHEDULED_TRIP_ID, tripId)).toBundle());
    }

    public static Intent intentForCityHostsReview(Context context, long selectedTripInstanceId) {
        return intent(context, APP_CITY_HOSTS_REVIEW_INSTANCE, ((BundleBuilder) new BundleBuilder().putLong(HomeActivityIntents.ARG_TRIP_HOST_REVIEWABLE_INSTANCE_ID, selectedTripInstanceId)).toBundle());
    }

    public static Intent intentForCityHostsManagerApp(Context context) {
        return intent(context, APP_CITY_HOSTS_MANAGER, null);
    }

    public static Intent intentForCityHostsBookingConfirmed(Context context) {
        return intent(context, BOOKING_CONFIRMED, null);
    }

    public static Intent intentForGiftCardsApp(Context context) {
        return intentForPortraitMode(context, APP_GIFTCARDS, null);
    }

    public static Intent intentForGiftCardsSent(Context context, String name, Double giftCreditAmount, String giftCreditCurrency) {
        return intentForPortraitMode(context, APP_GIFTCARDS_SENT, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("name", name)).putDouble("giftCreditAmount", giftCreditAmount.doubleValue())).putString("giftCreditCurrency", giftCreditCurrency)).toBundle());
    }

    public static Intent intentForGuidebook(Context context, String url) {
        return intent(context, APP_GUIDEBOOK, ((BundleBuilder) new BundleBuilder().putString("url", url)).toBundle());
    }

    public static Intent intentForListingSelfCheckinSettingsApp(Context context, long listingId) {
        return intent(context, APP_LISTING_SELF_CHECKIN_SETTINGS, ((BundleBuilder) new BundleBuilder().putString("listingId", UnsignedLongs.toString(listingId))).toBundle());
    }

    public static Intent intentForGuidebookSubcategory(Context context, ParcelableStringMap queryParams, C2731SearchContext searchContext) {
        BundleBuilder bb = (BundleBuilder) new BundleBuilder().putParcelable("queryParams", queryParams);
        addSearchContextString(bb, searchContext);
        return intent(context, SCREEN_GUIDEBOOK_SUBCATEGORY, bb.toBundle());
    }

    public static Intent intentForGuidebookInsider(Context context, long guidebookId, String primaryPhoto, String title, C2731SearchContext searchContext) {
        BundleBuilder bb = (BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("guidebookId", guidebookId)).putString("primaryPhoto", primaryPhoto)).putString("title", title);
        addSearchContextString(bb, searchContext);
        return intent(context, SCREEN_GUIDEBOOK_INSIDER, bb.toBundle());
    }

    public static Intent intentForGuidebookHomeHostMap(Context context, long listingId) {
        return intent(context, SCREEN_GUIDEBOOK_HOME_HOST_MAP, ((BundleBuilder) new BundleBuilder().putLong("listingId", listingId)).toBundle());
    }

    public static Intent intentForGuidebookFeedback(Context context, long placeActivityId) {
        return modalIntent(context, SCREEN_GUIDEBOOK_FEEDBACK, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("title", context.getString(C0716R.string.places_feedback_title))).putString("subtitle", context.getString(C0716R.string.places_feedback_subtitle))).putString("eventName", "guidebook_feedback_native")).putBundle("eventData", ((BundleBuilder) new BundleBuilder().putLong("activity_id", placeActivityId)).toBundle())).toBundle());
    }

    public static Intent intentForGuidebookDetour(Context context, String id, String title, C2731SearchContext searchContext) {
        BundleBuilder bb = (BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("id", id)).putString("title", title);
        addSearchContextString(bb, searchContext);
        return intent(context, SCREEN_GUIDEBOOK_DETOUR, bb.toBundle());
    }

    public static Intent intentForGuidebookMeetupCollection(Context context, ParcelableStringMap queryParams, C2731SearchContext searchContext) {
        BundleBuilder bb = (BundleBuilder) new BundleBuilder().putParcelable("queryParams", queryParams);
        addSearchContextString(bb, searchContext);
        return intent(context, SCREEN_GUIDEBOOK_MEETUPCOLLECTION, bb.toBundle());
    }

    public static Intent intentForReviews(Context context, long reviewId, String recipientFirstName) {
        return intent(context, APP_REVIEWS, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("reviewId", Long.valueOf(reviewId).toString())).putBoolean("showAllCategories", true)).putString("recipientFirstName", recipientFirstName)).toBundle());
    }

    public static Intent intentForItinerary(Context context) {
        return intentForPortraitMode(context, APP_ITINERARY, null);
    }

    public static Intent intentForItineraryImmersion(Context context, long id) {
        return intent(context, APP_ITINERARY_CARD, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("cardType", "immersion")).putLong("id", id)).toBundle());
    }

    public static Intent intentForItineraryExperienceCard(Context context, long id, String tripScheduleId, String picture) {
        return modalIntent(context, APP_ITINERARY_CARD, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("cardType", ShareActivityIntents.ENTRY_POINT_EXPERIENCE)).putLong("id", id)).putString("tripScheduleId", tripScheduleId)).putString("picture", picture)).toBundle());
    }

    public static Intent intentForExperiencePDP(Context context, TripTemplate tripTemplate, TopLevelSearchParams searchParams) {
        return intent(context, screenForExperiencePDP(tripTemplate.isImmersion()), bundleForExperiencePDP(tripTemplate.getId(), tripTemplate.getPosterUrl(), searchParams, null));
    }

    public static Intent intentForExperiencePDP(Context context, boolean isImmersion, long tripTemplateId) {
        return intent(context, screenForExperiencePDP(isImmersion), bundleForExperiencePDP(tripTemplateId, null, null, null));
    }

    public static Intent intentForExperiencePDP(Context context, boolean isImmersion, long tripTemplateId, C2443MtPdpReferrer referrer, String referrerId) {
        return intent(context, screenForExperiencePDP(isImmersion), bundleForExperiencePDP(tripTemplateId, (String) null, (TopLevelSearchParams) null, (C2731SearchContext) null, referrer, referrerId));
    }

    public static Intent intentForExperiencePDP(Context context, boolean isImmersion, long tripTemplateId, String posterImageUrl, TopLevelSearchParams params, C2731SearchContext searchContext, C2443MtPdpReferrer referrer, long referrerId) {
        return intent(context, screenForExperiencePDP(isImmersion), bundleForExperiencePDP(tripTemplateId, posterImageUrl, params, searchContext, referrer, referrerId));
    }

    private static String screenForExperiencePDP(boolean isImmersion) {
        return isImmersion ? SCREEN_CITY_HOSTS_GUEST_TEMPLATES : SCREEN_CITY_HOSTS_GUEST_SINGLE_EXPERIENCE_TEMPLATES;
    }

    private static Bundle bundleForExperiencePDP(long tripTemplateId, String posterImageUrl, TopLevelSearchParams params, C2731SearchContext searchContext) {
        return bundleForExperiencePDP(tripTemplateId, posterImageUrl, params, searchContext, C2443MtPdpReferrer.Unknown, 0);
    }

    private static Bundle bundleForExperiencePDP(long tripTemplateId, String posterImageUrl, TopLevelSearchParams params, C2731SearchContext searchContext, C2443MtPdpReferrer pdpReferrer, long referrerId) {
        BundleBuilder bb = bundleBuilderForExperiencePDP(tripTemplateId, posterImageUrl, params, searchContext, pdpReferrer);
        bb.putLong("mtPdpReferrerId", referrerId);
        return bb.toBundle();
    }

    private static Bundle bundleForExperiencePDP(long tripTemplateId, String posterImageUrl, TopLevelSearchParams params, C2731SearchContext searchContext, C2443MtPdpReferrer pdpReferrer, String referrerId) {
        BundleBuilder bb = bundleBuilderForExperiencePDP(tripTemplateId, posterImageUrl, params, searchContext, pdpReferrer);
        bb.putString("mtPdpReferrerId", referrerId);
        return bb.toBundle();
    }

    private static BundleBuilder bundleBuilderForExperiencePDP(long tripTemplateId, String posterImageUrl, TopLevelSearchParams params, C2731SearchContext searchContext, C2443MtPdpReferrer pdpReferrer) {
        BundleBuilder bb = (BundleBuilder) new BundleBuilder().putLong("tripTemplateId", tripTemplateId);
        if (posterImageUrl != null) {
            bb.putString("posterImageURL", posterImageUrl);
        }
        if (params != null) {
            AirDate checkIn = params.checkInDate();
            AirDate checkOut = params.checkOutDate();
            ((BundleBuilder) bb.putString("searchStartDate", checkIn == null ? "" : checkIn.formatDate(REACT_NATIVE_DATE_FORMAT))).putString("searchEndDate", checkOut == null ? "" : checkOut.formatDate(REACT_NATIVE_DATE_FORMAT));
        }
        bb.putInt("mtPdpReferrer", pdpReferrer.value);
        addSearchContextString(bb, searchContext);
        return bb;
    }

    private static void addSearchContextString(BundleBuilder bb, C2731SearchContext searchContext) {
        if (searchContext != null) {
            bb.putString("searchContextString", JitneyConverterUtils.toJson(searchContext));
        }
    }

    public static Intent intentForPlaceP3(Context context, GuidebookPlace place) {
        return intentForPlaceP3(context, Long.valueOf(place.getPrimaryPlace().getId()), place.getPrimaryPlace().getName(), null);
    }

    public static Intent intentForPlaceP3(Context context, Long id, String title, C2731SearchContext searchContext) {
        BundleBuilder bb = (BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("placeId", id.longValue())).putString("title", title);
        addSearchContextString(bb, searchContext);
        return intent(context, SCREEN_CITY_PLACE_PDP, bb.toBundle());
    }

    public static Intent intentForItineraryPlaceCard(Context context, long id, String tripScheduleId, String picture) {
        return intentForItineraryPlaceCard(context, false, id, tripScheduleId, picture);
    }

    public static Intent intentForItineraryPlaceCardModal(Context context, long id, String tripScheduleId, String picture) {
        return intentForItineraryPlaceCard(context, true, id, tripScheduleId, picture);
    }

    public static Intent intentForItineraryPlaceCard(Context context, boolean isModal, long id, String tripScheduleId, String picture) {
        Bundle bundle = ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("cardType", "place")).putLong("id", id)).putString("tripScheduleId", tripScheduleId)).putString("picture", picture)).putBoolean("isSourceNative", true)).toBundle();
        return isModal ? modalIntent(context, APP_ITINERARY_CARD, bundle) : intent(context, APP_ITINERARY_CARD, bundle);
    }

    public static Intent intentForItineraryCheckinCard(Context context, String confirmationCode) {
        return intent(context, APP_ITINERARY_CARD, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("cardType", UpdateReviewRequest.KEY_CHECKIN)).putString("id", confirmationCode)).toBundle());
    }

    public static Intent intentForItineraryCheckinCard(Context context, String confirmationCode, String tripScheduleId, String picture) {
        return modalIntent(context, APP_ITINERARY_CARD, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("cardType", UpdateReviewRequest.KEY_CHECKIN)).putString("id", confirmationCode)).putString("tripScheduleId", tripScheduleId)).putString("picture", picture)).toBundle());
    }

    public static Intent intentForVerificationSelfieReview(Context context, String imagePath) {
        return intent(context, APP_VERIFICATIONS_SELFIE_REVIEW_TYPE, ((BundleBuilder) new BundleBuilder().putString("imageUri", imagePath)).toBundle(), null);
    }

    public static Intent intentForVerifications(Context context) {
        return intent(context, APP_VERIFICATIONS_ID_TYPE, null, null);
    }

    public static Intent intentForVerificationsLight(Context context) {
        return intent(context, APP_VERIFICATIONS_ID_TYPE_LIGHT, ((BundleBuilder) new BundleBuilder().putBoolean("light", true)).toBundle(), null);
    }

    public static Intent intentForVerificationsV2(Context context) {
        return intent(context, APP_VERIFICATIONS_ID_TYPE_V2, new BundleBuilder().toBundle(), null);
    }

    public static Intent intentForVerificationInfo(Context context, IdentityReactNativeInfoType type) {
        return intent(context, APP_VERIFICATIONS_ID_INFO, ((BundleBuilder) new BundleBuilder().putString("type", type.name())).toBundle(), null);
    }

    public static Intent intentForContactHostEditText(Context context, User host, ReservationDetails reservationDetails, String message, String title, String subtitle, String hint, boolean showSuggestions, String defaultSuggestion, String tripContext) {
        return intent(context, APP_CONTACT_HOST, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("message", SanitizeUtils.emptyIfNull(message))).putString("title", title)).putString("subtitle", subtitle)).putString("hint", hint)).putBundle(TripRole.ROLE_KEY_HOST, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("firstName", host.getFirstName())).putString("profilePicPath", host.getPictureUrl())).toBundle())).putBundle("suggestionInfo", showSuggestions ? ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("defaultSuggestion", defaultSuggestion)).putLong("reservationId", reservationDetails.reservationId().longValue())).putLong("listingId", reservationDetails.listingId().longValue())).putInt("numGuests", reservationDetails.numberOfGuests())).putString("tripContext", tripContext)).toBundle() : null)).toBundle());
    }

    public static Intent intent(Context context, String moduleName) {
        return intent(context, moduleName, null);
    }

    public static Intent intent(Context context, String moduleName, Bundle props) {
        return intent(context, moduleName, props, null);
    }

    public static Intent intent(Context context, String moduleName, Bundle props, Bundle options) {
        return new Intent(context, Activities.reactNative()).putExtras(ReactNativeIntentUtils.intentExtras(moduleName, props, options));
    }

    public static Intent intentForPortraitMode(Context context, String moduleName, Bundle props) {
        return new Intent(context, Activities.reactNativePortrait()).putExtras(ReactNativeIntentUtils.intentExtras(moduleName, props));
    }

    public static Intent modalIntent(Context context, String moduleName, Bundle props) {
        return modalIntent(context, moduleName, props, null);
    }

    public static Intent modalIntent(Context context, String moduleName, Bundle props, Bundle options) {
        if (options != null) {
            String string = options.getString(ReactNativeIntentUtils.REACT_NAVIGATION_TAG);
        }
        return new Intent(context, Activities.reactNativeModal()).putExtras(ReactNativeIntentUtils.intentExtras(moduleName, props, options));
    }

    public static Intent intentForMagicalTripsNux(Context context, boolean isSignup) {
        return intent(context, SCREEN_HOST_MAGICAL_TRIPS_NUX, ((BundleBuilder) new BundleBuilder().putBoolean("is_sign_up", isSignup)).toBundle());
    }

    public static Intent intentForGiftCardsRedemptionApp(Context context, String code, String token) {
        return intentForPortraitMode(context, APP_GIFTCARDS_REDEMPTION, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString("code", code)).putString("token", token)).toBundle());
    }

    public static Intent intentForExperienceId(Context context, long experienceId) {
        return null;
    }

    public static Intent intentForTripSupport(Context context, long helpThreadId) {
        return intent(context, APP_TRIP_SUPPORT, ((BundleBuilder) new BundleBuilder().putLong("helpThreadId", helpThreadId)).toBundle(), null);
    }

    public static Intent intentForAirlock(Context context, long airlockId) {
        return intent(context, APP_AIRLOCK, ((BundleBuilder) new BundleBuilder().putLong("airlockId", airlockId)).toBundle()).putExtras(ReactNativeIntentUtils.optionsForNavigationTag("FrictionManager"));
    }

    public static Intent intentForItineraryPendingScreen(Context context, Bundle verificationBundle, ArrayList<Bundle> timelineTrips, String timeLeft) {
        return intent(context, APP_ITINERARY_PENDING_SCREEN, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putParcelableArrayList("tripSchedules", timelineTrips)).putBundle("verification", verificationBundle)).putString("timeLeft", timeLeft)).toBundle());
    }

    public static Intent intentForHelpCenter(Context context, long userId, boolean isGuestMode) {
        return intent(context, "HelpCenter", ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong("userId", userId)).putBoolean("isHostView", !isGuestMode)).toBundle());
    }
}
