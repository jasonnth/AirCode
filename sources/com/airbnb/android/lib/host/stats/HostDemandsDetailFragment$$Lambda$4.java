package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;
import java.util.Map.Entry;

final /* synthetic */ class HostDemandsDetailFragment$$Lambda$4 implements Predicate {
    private final Listing arg$1;

    private HostDemandsDetailFragment$$Lambda$4(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new HostDemandsDetailFragment$$Lambda$4(listing);
    }

    public boolean apply(Object obj) {
        return HostDemandsDetailFragment.lambda$loadDataForListing$0(this.arg$1, (Entry) obj);
    }
}
