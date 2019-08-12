package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRatingAggregation;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$16 implements Predicate {
    private final Listing arg$1;

    private HostReviewDetailsFragment$$Lambda$16(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new HostReviewDetailsFragment$$Lambda$16(listing);
    }

    public boolean apply(Object obj) {
        return HostReviewDetailsFragment.lambda$null$8(this.arg$1, (ListingRatingAggregation) obj);
    }
}
