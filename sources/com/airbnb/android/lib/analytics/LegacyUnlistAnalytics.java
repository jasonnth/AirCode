package com.airbnb.android.lib.analytics;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.android.utils.Strap;

public class LegacyUnlistAnalytics extends BaseAnalytics {
    private static final String CLICKED_LINK = "clicked_link";
    private static final String CLICK_REASON = "click_reason";
    private static final String END_DATE = "end_date";
    private static final String EVENT_NAME = "manage_listing";
    private static final String LISTING_ID = "listing_id";
    private static final String OPERATION_CLICK_LINK = "click_link";
    private static final String OTHER_REASONS = "other_reasons";
    private static final String PAGE_UNLIST_PROMPT = "unlist_prompt";
    private static final String SECTION_AVAILABILITY_DROPDOWN = "availability_dropdown";
    private static final String START_DATE = "start_date";
    private static final String SUBMIT_SNOOZE = "submit_snooze";
    private static final String SUBMIT_UNLIST = "submit_unlist";
    private static final String SUBSCRIBE_TRAVEL_DATES_ALERT = "subscribe_travel_dates_alert";
    private static final String UNLIST_REASON = "unlist_reason";
    public static final String UNLIST_REASON_NEGATIVE_EXPERIENCE = "negative_experience";
    public static final String UNLIST_REASON_NOT_EARN_ENOUGH = "not_earn_enough";
    public static final String UNLIST_REASON_NO_LONGER_HAVE_ACCESS = "no_longer_have_access";
    public static final String UNLIST_REASON_OTHER = "other_reasons";
    public static final String UNLIST_REASON_QUESTIONS_ABOUT_LAW = "law_questions";
    public static final String UNLIST_REASON_QUESTIONS_ABOUT_TRUST = "trust_questions";
    public static final String UNLIST_REASON_TOO_MUCH_WORK = "too_much_work";
    public static final String UNLIST_REASON_UNLIST_TEMPORARILY = "unlist_temporarily";

    public static void trackViewUnlist(Listing listing) {
        AirbnbEventLogger.track("manage_listing", getBaseStrap(listing.getId()).mo11639kv(BaseAnalytics.OPERATION, "impression"));
    }

    public static void trackSelectUnlistReason(Listing listing, String unlistReason) {
        AirbnbEventLogger.track("manage_listing", getBaseStrap(listing.getId()).mo11639kv(BaseAnalytics.OPERATION, CLICK_REASON).mo11639kv(UNLIST_REASON, unlistReason));
    }

    public static void trackSubmitUnlistWithUnlistReason(Listing listing, LegacyUnlistReason unlistReason) {
        AirbnbEventLogger.track("manage_listing", getSubmitUnlistStrap(listing.getId(), unlistReason));
    }

    public static void trackSubmitUnlistWithSubscriptionToAlert(Listing listing, boolean subscribeToTravelAlerts) {
        AirbnbEventLogger.track("manage_listing", getSubmitUnlistStrap(listing.getId(), LegacyUnlistReason.NotEarnEnough).mo11640kv(SUBSCRIBE_TRAVEL_DATES_ALERT, subscribeToTravelAlerts));
    }

    public static void trackSubmitUnlistWithOtherReason(Listing listing, String otherReason) {
        AirbnbEventLogger.track("manage_listing", getSubmitUnlistStrap(listing.getId(), LegacyUnlistReason.OtherReason).mo11639kv("other_reasons", otherReason));
    }

    public static void trackSubmitSnooze(Listing listing, AirDate startDate, AirDate endDate) {
        AirbnbEventLogger.track("manage_listing", getBaseStrap(listing.getId()).mo11639kv(BaseAnalytics.OPERATION, SUBMIT_SNOOZE).mo11639kv("start_date", startDate.getIsoDateString()).mo11639kv("end_date", endDate.getIsoDateString()));
    }

    public static void trackClickLinkInUnlistDialog(Listing listing, String url) {
        AirbnbEventLogger.track("manage_listing", getBaseStrap(listing.getId()).mo11639kv(BaseAnalytics.OPERATION, OPERATION_CLICK_LINK).mo11639kv(CLICKED_LINK, url));
    }

    public static void trackSendAlertsForPopularTravelDates(Listing listing) {
        AirbnbEventLogger.track("manage_listing", getBaseStrap(listing.getId()).mo11639kv(BaseAnalytics.OPERATION, SUBSCRIBE_TRAVEL_DATES_ALERT));
    }

    private static Strap getBaseStrap(long listingId) {
        return Strap.make().mo11639kv("page", PAGE_UNLIST_PROMPT).mo11639kv(BaseAnalytics.SECTION, SECTION_AVAILABILITY_DROPDOWN).mo11638kv("listing_id", listingId);
    }

    private static Strap getSubmitUnlistStrap(long listingId, LegacyUnlistReason unlistReason) {
        return getBaseStrap(listingId).mo11639kv(BaseAnalytics.OPERATION, SUBMIT_UNLIST).mo11639kv(UNLIST_REASON, unlistReason.getReasonEvent());
    }
}
