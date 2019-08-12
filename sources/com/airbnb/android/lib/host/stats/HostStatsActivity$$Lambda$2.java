package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class HostStatsActivity$$Lambda$2 implements Predicate {
    private static final HostStatsActivity$$Lambda$2 instance = new HostStatsActivity$$Lambda$2();

    private HostStatsActivity$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostStatsActivity.lambda$getFirstListedListing$1((Listing) obj);
    }
}
