package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;
import java.util.Set;

final /* synthetic */ class HostStatsFragment$$Lambda$23 implements Predicate {
    private final Set arg$1;

    private HostStatsFragment$$Lambda$23(Set set) {
        this.arg$1 = set;
    }

    public static Predicate lambdaFactory$(Set set) {
        return new HostStatsFragment$$Lambda$23(set);
    }

    public boolean apply(Object obj) {
        return this.arg$1.contains(Long.valueOf(((Listing) obj).getId()));
    }
}
