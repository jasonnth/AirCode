package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRatingAggregation;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$10 implements Predicate {
    private final Listing arg$1;

    private HostReviewDetailsFragment$$Lambda$10(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new HostReviewDetailsFragment$$Lambda$10(listing);
    }

    public boolean apply(Object obj) {
        return HostReviewDetailsFragment.lambda$loadListingRatingAggregations$7(this.arg$1, (ListingRatingAggregation) obj);
    }
}
