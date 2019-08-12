package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HostStatsFragment$$Lambda$14 implements OnClickListener {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$14(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static OnClickListener lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$14(hostStatsFragment);
    }

    public void onClick(View view) {
        this.arg$1.showDemandDetailsFragment();
    }
}
