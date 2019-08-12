package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.ReactNativeIntents;

final /* synthetic */ class HostStatsFragment$$Lambda$20 implements OnClickListener {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$20(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static OnClickListener lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$20(hostStatsFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(ReactNativeIntents.intentForHostStatsTransactions(this.arg$1.getContext()));
    }
}
