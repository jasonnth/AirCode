package com.airbnb.android.lib.host.stats;

import p032rx.functions.Action1;

final /* synthetic */ class HostStatsFragment$$Lambda$5 implements Action1 {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$5(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static Action1 lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$5(hostStatsFragment);
    }

    public void call(Object obj) {
        this.arg$1.hostStandardMetrics = ((HostStandardsResponse) obj).hostStandardMetrics;
    }
}
