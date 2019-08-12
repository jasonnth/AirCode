package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.responses.InsightsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostStatsFragment$$Lambda$9 implements Action1 {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$9(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static Action1 lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$9(hostStatsFragment);
    }

    public void call(Object obj) {
        HostStatsFragment.lambda$new$12(this.arg$1, (InsightsResponse) obj);
    }
}
