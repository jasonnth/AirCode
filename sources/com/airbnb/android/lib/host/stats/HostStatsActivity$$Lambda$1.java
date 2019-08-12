package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class HostStatsActivity$$Lambda$1 implements Predicate {
    private static final HostStatsActivity$$Lambda$1 instance = new HostStatsActivity$$Lambda$1();

    private HostStatsActivity$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostStatsActivity.lambda$userHasOnlyOneListedListing$0((Listing) obj);
    }
}
