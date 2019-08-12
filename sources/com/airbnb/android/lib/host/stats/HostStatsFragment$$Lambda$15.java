package com.airbnb.android.lib.host.stats;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class HostStatsFragment$$Lambda$15 implements OnRefreshListener {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$15(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static OnRefreshListener lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$15(hostStatsFragment);
    }

    public void onRefresh() {
        HostStatsFragment.lambda$onCreateView$3(this.arg$1);
    }
}
