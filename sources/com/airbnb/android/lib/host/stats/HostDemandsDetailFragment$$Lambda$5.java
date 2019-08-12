package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingDemandDetails;
import com.google.common.base.Predicate;

final /* synthetic */ class HostDemandsDetailFragment$$Lambda$5 implements Predicate {
    private final Listing arg$1;

    private HostDemandsDetailFragment$$Lambda$5(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new HostDemandsDetailFragment$$Lambda$5(listing);
    }

    public boolean apply(Object obj) {
        return HostDemandsDetailFragment.lambda$loadDataForListing$1(this.arg$1, (ListingDemandDetails) obj);
    }
}
