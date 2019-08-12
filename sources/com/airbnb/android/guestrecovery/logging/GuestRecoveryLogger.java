package com.airbnb.android.guestrecovery.logging;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.Rejection.p224v1.RejectionRejectionImpressionEvent.Builder;
import com.airbnb.jitney.event.logging.Rejection.p224v1.RejectionRejectionSimilarListingsBrowseMoreClickEvent;
import com.airbnb.jitney.event.logging.Rejection.p224v1.RejectionRejectionSimilarListingsClickEvent;
import com.airbnb.jitney.event.logging.Rejection.p224v1.RejectionRejectionSimilarListingsImpressionEvent;
import com.airbnb.jitney.event.logging.Rejection.p224v1.RejectionRejectionSimilarListingsTripDetailsClickEvent;
import java.util.Set;

public class GuestRecoveryLogger extends BaseLogger {
    public GuestRecoveryLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void rejectionImpression(long userId, long listingId, String reservationCode) {
        Builder builder = new Builder(context(), Long.valueOf(userId), Long.valueOf(listingId));
        builder.reservation_code(reservationCode);
        publish(builder);
    }

    public void similarListingsImpression(long userId, long listingId, Set<String> similarListingIds, String reservationCode) {
        RejectionRejectionSimilarListingsImpressionEvent.Builder builder = new RejectionRejectionSimilarListingsImpressionEvent.Builder(context(), Long.valueOf(userId), Long.valueOf(listingId));
        builder.reservation_code(reservationCode);
        builder.listing_ids(similarListingIds);
        publish(builder);
    }

    public void browseMoreClick(long userId, long listingId, String reservationCode) {
        RejectionRejectionSimilarListingsBrowseMoreClickEvent.Builder builder = new RejectionRejectionSimilarListingsBrowseMoreClickEvent.Builder(context(), Long.valueOf(userId), Long.valueOf(listingId));
        builder.reservation_code(reservationCode);
        publish(builder);
    }

    public void similarListingsClick(long userId, long listingId, Set<String> similarListingIds, String reservationCode) {
        RejectionRejectionSimilarListingsClickEvent.Builder builder = new RejectionRejectionSimilarListingsClickEvent.Builder(context(), Long.valueOf(userId), Long.valueOf(listingId), similarListingIds);
        builder.reservation_code(reservationCode);
        publish(builder);
    }

    public void tripDetailsClick(long userId, long listingId, String reservationCode) {
        RejectionRejectionSimilarListingsTripDetailsClickEvent.Builder builder = new RejectionRejectionSimilarListingsTripDetailsClickEvent.Builder(context(), Long.valueOf(userId), Long.valueOf(listingId));
        builder.reservation_code(reservationCode);
        publish(builder);
    }
}
