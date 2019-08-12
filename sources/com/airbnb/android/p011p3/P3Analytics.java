package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppboyAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SearchJitneyUtils;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.lib.analytics.ROAnalytics;
import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1.ImpressionData;
import com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1.UrgencyCommitmentEvent;
import com.airbnb.jitney.event.logging.core.P3ListingView.p302v3.P3ListingViewEvent.Builder;
import com.airbnb.jitney.event.logging.p167P3.p168v2.LeavePageData;
import com.airbnb.jitney.event.logging.p167P3.p168v2.P3EngagementEvent;
import com.airbnb.jitney.event.logging.p167P3.p168v2.PageNavigationAction;
import com.airbnb.jitney.event.logging.p167P3.p168v2.PageNavigationActionType;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.jpush.Gson;
import com.mparticle.commerce.Product;
import icepick.Icepick;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* renamed from: com.airbnb.android.p3.P3Analytics */
public class P3Analytics extends BaseAnalytics {
    public static final String CONTACT_HOST_TARGET_BOOK_BUTTON = "contact_host_book_button";
    public static final String CONTACT_HOST_TARGET_ORIGIN = "contact_host_button";
    private static final String EVENT_NAME = "p3";
    private static final int MAX_REMARKETING_IDS = 6;
    private final ListingDetailsController controller;
    @State
    boolean hasLoggedImpression;
    @State
    String impressionId;
    private final LoggingContextFactory loggingContextFactory;

    public P3Analytics(ListingDetailsController controller2, LoggingContextFactory loggingContextFactory2, Bundle savedInstanceState) {
        this.controller = controller2;
        this.loggingContextFactory = loggingContextFactory2;
        if (savedInstanceState == null) {
            this.impressionId = "p3_" + UUID.randomUUID().toString();
            return;
        }
        Icepick.restoreInstanceState(this, savedInstanceState);
        Check.notNull(this.impressionId, "Impression ID was not saved");
    }

    private P3State state() {
        return this.controller.getState();
    }

    public String getImpressionId() {
        return this.impressionId;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void trackPhotoCarouselClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("photo_carousel"));
    }

    public void trackShareClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(ShareDialog.WEB_SHARE_DIALOG).mo11639kv(BaseAnalytics.TARGET, "share_button"));
    }

    public void trackHostProfileClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("host_profile"));
    }

    public void trackSuperhostHelpClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("super_host"));
    }

    public void trackListingDescriptionClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("listing_description"));
    }

    public void trackToggleListingDescriptionTranslationClick(boolean showTranslatedDescription, long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("translate_description").mo11639kv(BaseAnalytics.TARGET, showTranslatedDescription ? "show_original_description_button" : "translate_description_button").mo11638kv("id_listing", listingId));
    }

    public void trackAmenitiesClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("more_amenities").mo11639kv(BaseAnalytics.TARGET, "amenities_button"));
    }

    public void trackMapClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(P3Arguments.FROM_MAP));
    }

    public void trackReviewsClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(VerificationsAdapter.VERIFICATION_REVIEWS));
    }

    public void trackGuidebookClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("guidebook").mo11639kv(BaseAnalytics.TARGET, "guidebook_button"));
    }

    public void trackHouseRulesClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(ListingRequestConstants.JSON_HOUSE_RULES_KEY).mo11639kv(BaseAnalytics.TARGET, "house_rules_button"));
    }

    public void trackCancellationPolicyClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(ListingRequestConstants.JSON_CANCELLATION_KEY));
    }

    public void trackAdditionalPricesClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("additional_prices"));
    }

    public void trackAvailabilityCalendarClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("availability_calendar"));
    }

    public void trackContactHostClick(String target) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("contact_host").mo11639kv(BaseAnalytics.TARGET, target));
    }

    public void trackContactHostAddDatesClick(AirDate checkIn, AirDate checkout) {
        AirbnbEventLogger.track(EVENT_NAME, contactHostClickStrap(CancellationAnalytics.VALUE_PAGE_DATES).mo11639kv(BaseAnalytics.SUBEVENT, "add").mo11639kv(UpdateReviewRequest.KEY_CHECKIN, checkIn != null ? checkIn.getIsoDateString() : "").mo11639kv(Product.CHECKOUT, checkout != null ? checkout.getIsoDateString() : ""));
    }

    public void trackBookItAddDatesClick(AirDate checkIn, AirDate checkout) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("book_it").mo11639kv(BaseAnalytics.SUBEVENT, CancellationAnalytics.VALUE_PAGE_DATES).mo11639kv(UpdateReviewRequest.KEY_CHECKIN, checkIn != null ? checkIn.getIsoDateString() : "").mo11639kv(Product.CHECKOUT, checkout != null ? checkout.getIsoDateString() : ""));
    }

    public void trackContactHostAddGuestsClick(GuestDetails data) {
        int i;
        boolean z = true;
        String str = EVENT_NAME;
        Strap kv = contactHostClickStrap(FindTweenAnalytics.GUESTS).mo11639kv(BaseAnalytics.SUBEVENT, "add");
        String str2 = FindTweenAnalytics.GUESTS;
        if (data != null) {
            i = data.totalGuestCount();
        } else {
            i = 1;
        }
        Strap kv2 = kv.mo11637kv(str2, i);
        String str3 = FindTweenAnalytics.PETS;
        if (data == null || !data.isBringingPets()) {
            z = false;
        }
        AirbnbEventLogger.track(str, kv2.mo11640kv(str3, z));
    }

    public void trackContactHostAddMessageClick() {
        AirbnbEventLogger.track(EVENT_NAME, contactHostClickStrap("message").mo11639kv(BaseAnalytics.SUBEVENT, "add"));
    }

    public void trackBookItButtonClick(long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("book_it").mo11639kv(BaseAnalytics.TARGET, "book_it_button").mo11638kv("id_listing", listingId));
    }

    public void trackBookItButtonClickWithoutDates(long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("book_it").mo11639kv(BaseAnalytics.TARGET, "book_it_button_without_dates").mo11638kv("id_listing", listingId));
    }

    public void trackCheckAvailabilityButtonClick(long listingId) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("check_availability").mo11639kv(BaseAnalytics.TARGET, "check_availability_button").mo11638kv("id_listing", listingId));
    }

    public void trackLeaveP3() {
        String str;
        String str2 = null;
        String str3 = EVENT_NAME;
        Strap kv = getP3SectionClickStrap(null).mo11639kv(BaseAnalytics.SUBEVENT, "go_back");
        String str4 = UpdateReviewRequest.KEY_CHECKIN;
        if (state().checkInDate() != null) {
            str = state().checkInDate().getIsoDateString();
        } else {
            str = null;
        }
        Strap kv2 = kv.mo11639kv(str4, str);
        String str5 = Product.CHECKOUT;
        if (state().checkOutDate() != null) {
            str2 = state().checkOutDate().getIsoDateString();
        }
        AirbnbEventLogger.track(str3, kv2.mo11639kv(str5, str2).mo11638kv("id_listing", state().listingId()));
    }

    public void trackImpressionIfNeeded(Context context) {
        if (!this.hasLoggedImpression) {
            this.hasLoggedImpression = true;
            Listing listing = state().listing();
            logAirEventImpression(context, listing);
            logJitneyImpression(listing);
        }
    }

    private void logAirEventImpression(Context context, Listing listing) {
        String str;
        String str2;
        boolean z;
        int i;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z2 = false;
        String str7 = null;
        String str8 = EVENT_NAME;
        Strap kv = defaultP3PageStrap().mo11639kv(BaseAnalytics.SECTION, ROAnalytics.GENERAL).mo11639kv(BaseAnalytics.OPERATION, "view").mo11639kv("action", "view");
        String str9 = UpdateReviewRequest.KEY_CHECKIN;
        if (state().checkInDate() != null) {
            str = state().checkInDate().getIsoDateString();
        } else {
            str = null;
        }
        Strap kv2 = kv.mo11639kv(str9, str);
        String str10 = Product.CHECKOUT;
        if (state().checkOutDate() != null) {
            str2 = state().checkOutDate().getIsoDateString();
        } else {
            str2 = null;
        }
        Strap kv3 = kv2.mo11639kv(str10, str2);
        String str11 = "ib";
        if (state().pricingQuote() != null) {
            z = state().pricingQuote().isInstantBookable();
        } else {
            z = false;
        }
        Strap kv4 = kv3.mo11640kv(str11, z);
        String str12 = "num_reviews";
        if (listing != null) {
            i = listing.getReviewsCount();
        } else {
            i = 0;
        }
        Strap kv5 = kv4.mo11637kv(str12, i).mo11636kv("overall_review_rating", listing != null ? listing.getStarRating() : 0.0f).mo11637kv(FindTweenAnalytics.GUESTS, state().guestDetails() != null ? state().guestDetails().totalGuestCount() : 1);
        String str13 = FindTweenAnalytics.PETS;
        if (state().guestDetails() != null) {
            z2 = state().guestDetails().isBringingPets();
        }
        AirbnbEventLogger.track(str8, kv5.mo11640kv(str13, z2).mo11639kv("description_language", listing != null ? listing.getDescriptionLocale() : ""));
        List<String> remarketingIds = new ArrayList<>();
        if (listing != null) {
            List<Long> newRemarketingIds = listing.getRemarketingIds();
            if (newRemarketingIds != null) {
                int i2 = 0;
                while (i2 < 6 && i2 < newRemarketingIds.size()) {
                    remarketingIds.add(Long.toString(((Long) newRemarketingIds.get(i2)).longValue()));
                    i2++;
                }
            }
        }
        if (remarketingIds.isEmpty()) {
            remarketingIds.add(Long.toString(state().listingId()));
        }
        String remarketingIdsJSON = new Gson().toJson((Object) remarketingIds);
        Strap make = Strap.make();
        String str14 = "checkin_date";
        if (state().checkInDate() != null) {
            str3 = state().checkInDate().getIsoDateString();
        } else {
            str3 = null;
        }
        Strap kv6 = make.mo11639kv(str14, str3);
        String str15 = "checkout_date";
        if (state().checkOutDate() != null) {
            str4 = state().checkOutDate().getIsoDateString();
        } else {
            str4 = null;
        }
        Strap kv7 = kv6.mo11639kv(str15, str4).mo11639kv("listing_id", remarketingIdsJSON);
        String str16 = "listing_city";
        if (listing != null) {
            str5 = listing.getCity();
        } else {
            str5 = null;
        }
        Strap kv8 = kv7.mo11639kv(str16, str5);
        String str17 = "listing_state";
        if (listing != null) {
            str6 = listing.getState();
        } else {
            str6 = null;
        }
        Strap kv9 = kv8.mo11639kv(str17, str6);
        String str18 = "listing_country";
        if (listing != null) {
            str7 = listing.getCountry();
        }
        Strap info = kv9.mo11639kv(str18, str7);
        MParticleAnalytics.logEvent("p3_impression", info);
        AppboyAnalytics.logEvent(context, "p3_impression", info);
    }

    private void logJitneyImpression(Listing listing) {
        Double valueOf;
        Double valueOf2;
        Double valueOf3;
        Double valueOf4;
        Double valueOf5;
        Double valueOf6;
        double accuracyRating = (double) listing.getReviewRatingAccuracy();
        double checkinRating = (double) listing.getReviewRatingCheckin();
        double cleanlinessRating = (double) listing.getReviewRatingCleanliness();
        double communicationRating = (double) listing.getReviewRatingCommunication();
        double locationRating = (double) listing.getReviewRatingLocation();
        double valueRating = (double) listing.getReviewRatingValue();
        Builder builder = new Builder(this.impressionId, Long.valueOf(listing.getId()), SearchJitneyUtils.spaceTypeToRoomType(listing.getSpaceType()), this.loggingContextFactory.newInstance(), listing.getDescriptionLocale());
        if (accuracyRating == 0.0d) {
            valueOf = null;
        } else {
            valueOf = Double.valueOf(accuracyRating);
        }
        Builder checkin_date = builder.accuracy_rating(valueOf).amenities(SearchJitneyUtils.intArrayToLongList(listing.getAmenityIdsArray())).cancel_policy(SearchJitneyUtils.getCancellationPolicyFromString(listing.getCancellationPolicyKey())).checkin_date(state().checkInDate() != null ? state().checkInDate().getIsoDateString() : null);
        if (checkinRating == 0.0d) {
            valueOf2 = null;
        } else {
            valueOf2 = Double.valueOf(checkinRating);
        }
        Builder checkout_date = checkin_date.checkin_rating(valueOf2).checkout_date(state().checkOutDate() != null ? state().checkOutDate().getIsoDateString() : null);
        if (cleanlinessRating == 0.0d) {
            valueOf3 = null;
        } else {
            valueOf3 = Double.valueOf(cleanlinessRating);
        }
        Builder cleanliness_rating = checkout_date.cleanliness_rating(valueOf3);
        if (communicationRating == 0.0d) {
            valueOf4 = null;
        } else {
            valueOf4 = Double.valueOf(communicationRating);
        }
        Builder listing_lng = cleanliness_rating.communication_rating(valueOf4).guests(Long.valueOf((long) (state().guestDetails() != null ? state().guestDetails().totalGuestCount() : 1))).instant_book_possible(state().pricingQuote() != null ? Boolean.valueOf(state().pricingQuote().isInstantBookable()) : null).is_superhost(Boolean.valueOf(listing.isSuperHosted())).listing_lat(Double.valueOf(listing.getLatitude())).listing_lng(Double.valueOf(listing.getLongitude()));
        if (locationRating == 0.0d) {
            valueOf5 = null;
        } else {
            valueOf5 = Double.valueOf(locationRating);
        }
        Builder search_ranking_id = listing_lng.location_rating(valueOf5).person_capacity(Long.valueOf((long) listing.getPersonCapacity())).picture_count(Long.valueOf((long) listing.getPictureCount())).search_ranking_id(state().searchSessionId());
        if (valueRating == 0.0d) {
            valueOf6 = null;
        } else {
            valueOf6 = Double.valueOf(valueRating);
        }
        JitneyPublisher.publish(search_ranking_id.value_rating(valueOf6).visible_review_count(Long.valueOf((long) listing.getReviewsCount())));
    }

    public void trackContactHostImpression() {
        int i;
        String str = null;
        boolean z = true;
        String str2 = EVENT_NAME;
        Strap kv = defaultContactHostPageStrap().mo11639kv(BaseAnalytics.OPERATION, "view").mo11639kv("action", "view").mo11639kv(UpdateReviewRequest.KEY_CHECKIN, state().checkInDate() != null ? state().checkInDate().getIsoDateString() : null);
        String str3 = Product.CHECKOUT;
        if (state().checkOutDate() != null) {
            str = state().checkOutDate().getIsoDateString();
        }
        Strap kv2 = kv.mo11639kv(str3, str).mo11638kv("id_listing", state().listingId());
        String str4 = FindTweenAnalytics.GUESTS;
        if (state().guestDetails() != null) {
            i = state().guestDetails().totalGuestCount();
        } else {
            i = 1;
        }
        Strap kv3 = kv2.mo11637kv(str4, i);
        String str5 = FindTweenAnalytics.PETS;
        if (state().guestDetails() == null || !state().guestDetails().isBringingPets()) {
            z = false;
        }
        AirbnbEventLogger.track(str2, kv3.mo11640kv(str5, z));
    }

    public void trackSimilarListingsEnteringViewport(List<Long> similarListingIds, int numberOfIBListings) {
        AirbnbEventLogger.track(EVENT_NAME, defaultP3PageStrap().mo11639kv(BaseAnalytics.OPERATION, "view").mo11639kv(BaseAnalytics.SECTION, P3Arguments.FROM_SIMILAR_LISTINGS).mo11639kv(BaseAnalytics.SUBEVENT, "similar_listings_impression").mo11637kv("ib_similar_listings_count", numberOfIBListings).mo11639kv("id_recommendations", TextUtils.join(",", similarListingIds)));
    }

    public void trackSimilarListingsSwipe(String direction) {
        AirbnbEventLogger.track(EVENT_NAME, defaultP3PageStrap().mo11639kv(BaseAnalytics.SECTION, P3Arguments.FROM_SIMILAR_LISTINGS).mo11639kv(BaseAnalytics.OPERATION, direction));
    }

    public void trackBusinessDetailsClick() {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap("business_details"));
    }

    private Strap contactHostClickStrap(String section) {
        return defaultContactHostPageStrap().mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.SECTION, section);
    }

    private Strap getP3SectionClickStrap(String section) {
        return defaultP3PageStrap().mo11639kv(BaseAnalytics.SECTION, section).mo11639kv(BaseAnalytics.OPERATION, "click");
    }

    private Strap defaultP3PageStrap() {
        return Strap.make().mo11639kv("page", "listing").mo11638kv("listing_id", state().listingId()).mo11639kv("mobile_search_session_id", state().searchSessionId());
    }

    private Strap defaultContactHostPageStrap() {
        return defaultP3PageStrap().mo11639kv("page", "contact_host");
    }

    public void trackSimilarListingsClick(long similarListingId) {
        AirbnbEventLogger.track(EVENT_NAME, getP3SectionClickStrap(P3Arguments.FROM_SIMILAR_LISTINGS).mo11639kv(BaseAnalytics.SUBEVENT, "similar_listings_listing").mo11638kv("id_recommendations", similarListingId));
    }

    public void trackCarouselSwipe(String direction) {
        AirbnbEventLogger.track(EVENT_NAME, defaultP3PageStrap().mo11639kv(BaseAnalytics.SECTION, "photo_carousel").mo11639kv(BaseAnalytics.OPERATION, direction));
    }

    public void trackScrollToSection(String scrollToSection) {
        AirbnbEventLogger.track("scrolling", defaultP3PageStrap().mo11639kv("scroll_to_section", scrollToSection).mo11639kv(BaseAnalytics.OPERATION, "scroll_to"));
    }

    public void trackViewDuration(long duration) {
        JitneyPublisher.publish(new P3EngagementEvent.Builder(this.loggingContextFactory.newInstance()).p3_impression_id(this.impressionId).search_ranking_id(state().searchSessionId()).listing_id(Long.valueOf(state().listingId())).page_navigation_action(new PageNavigationAction.Builder().action_type(PageNavigationActionType.LEAVE_PAGE).leave_page_data(new LeavePageData.Builder().page_view_duration(Integer.valueOf((int) (duration / 1000))).build()).build()));
        AirbnbEventLogger.track(EVENT_NAME, defaultP3PageStrap().mo11639kv(BaseAnalytics.OPERATION, "duration").mo11638kv("total_view_duration", duration));
    }

    public void trackUrgencyImpression(UrgencyMessageData urgencyData) {
        JitneyPublisher.publish(new UrgencyCommitmentEvent.Builder(this.loggingContextFactory.newInstance()).operation("impression").page("listing_page").listing_id(Long.valueOf(state().listingId())).checkin_date(state().checkInDate() == null ? "" : state().checkInDate().getIsoDateString()).checkout_date(state().checkOutDate() == null ? "" : state().checkOutDate().getIsoDateString()).guests(Long.valueOf((long) state().guestDetails().totalGuestCount())).search_ranking_id(state().searchSessionId()).p3_impression_id(this.impressionId).impression_data(new ImpressionData.Builder().message_type(urgencyData.getType().getServerKey()).headline_variation(urgencyData.getMessage().getHeadline()).body_variation(urgencyData.getMessage().getBody()).context_variation(urgencyData.getMessage().getContextualMessage()).build()));
    }
}
