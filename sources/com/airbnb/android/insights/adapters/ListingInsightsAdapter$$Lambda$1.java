package com.airbnb.android.insights.adapters;

import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingInsightsAdapter$$Lambda$1 implements Predicate {
    private final Listing arg$1;

    private ListingInsightsAdapter$$Lambda$1(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new ListingInsightsAdapter$$Lambda$1(listing);
    }

    public boolean apply(Object obj) {
        return ListingInsightsAdapter.lambda$new$0(this.arg$1, (Insight) obj);
    }
}
