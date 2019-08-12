package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class HostStatsFragment$$Lambda$21 implements Predicate {
    private static final HostStatsFragment$$Lambda$21 instance = new HostStatsFragment$$Lambda$21();

    private HostStatsFragment$$Lambda$21() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((Listing) obj).isListed();
    }
}
