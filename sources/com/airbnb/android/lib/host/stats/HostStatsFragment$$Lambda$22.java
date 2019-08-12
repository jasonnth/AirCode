package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Insight;
import com.google.common.base.Function;

final /* synthetic */ class HostStatsFragment$$Lambda$22 implements Function {
    private static final HostStatsFragment$$Lambda$22 instance = new HostStatsFragment$$Lambda$22();

    private HostStatsFragment$$Lambda$22() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((Insight) obj).getListingId());
    }
}
