package com.airbnb.android.lib.host.stats;

import p032rx.functions.Action1;

final /* synthetic */ class HostStatsFragment$$Lambda$8 implements Action1 {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$8(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static Action1 lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$8(hostStatsFragment);
    }

    public void call(Object obj) {
        HostStatsFragment.lambda$new$11(this.arg$1, (HostEarningsResponse) obj);
    }
}
