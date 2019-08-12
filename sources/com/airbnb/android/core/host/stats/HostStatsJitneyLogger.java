package com.airbnb.android.core.host.stats;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsEarningsDetailsEvent.Builder;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsListingPickerOpenEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsListingPickerTapChoiceEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsListingViewsDetailsEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsOverallRatingDetailsEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsRatingsRecentRatingTapEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsStandardsAboutButtonEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsSuperhostDetailsButtonEvent;
import com.airbnb.jitney.event.logging.HostStats.p013v1.StatsSuperhostLearnMoreEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class HostStatsJitneyLogger extends BaseLogger {
    public static final String PAGE_LISTING_VIEW_DETAILS = "stats_views_details";
    public static final String PAGE_REVIEW_DETAILS = "stats_ratings";

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatsPage {
    }

    public HostStatsJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logEarningsDetailsButtonClickEvent() {
        publish(new Builder(context()));
    }

    public void logListingPickerOpenEvent(String page) {
        publish(new StatsListingPickerOpenEvent.Builder(context(), page));
    }

    public void logListingPickerListingSelectedEvent(long listingId) {
        publish(new StatsListingPickerTapChoiceEvent.Builder(context(), Boolean.valueOf(false)).listing_id(Long.valueOf(listingId)));
    }

    public void logListingPickerAllListingsClickedEvent() {
        publish(new StatsListingPickerTapChoiceEvent.Builder(context(), Boolean.valueOf(true)));
    }

    public void logListingViewsDetailsClickEvent() {
        publish(new StatsListingViewsDetailsEvent.Builder(context()));
    }

    public void logRatingDetailsButtonClickEvent() {
        publish(new StatsOverallRatingDetailsEvent.Builder(context()));
    }

    public void logRatingsRecentRatingTapEvent(Listing listing, long carouselIndex) {
        publish(new StatsRatingsRecentRatingTapEvent.Builder(context(), Long.valueOf(listing.getId()), Long.valueOf(carouselIndex), listing.getReviewScores().getStateKey()));
    }

    public void logHostingStandardsLearnMoreButtonClickEvent() {
        publish(new StatsStandardsAboutButtonEvent.Builder(context()));
    }

    public void logSuperhostDetailsButtonClickEvent() {
        publish(new StatsSuperhostDetailsButtonEvent.Builder(context()));
    }

    public void logSuperhostLearnMoreClickEvent() {
        publish(new StatsSuperhostLearnMoreEvent.Builder(context()));
    }
}
