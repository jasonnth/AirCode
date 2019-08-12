package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostStatsFragment$$Lambda$11 implements Action1 {
    private final HostStatsFragment arg$1;

    private HostStatsFragment$$Lambda$11(HostStatsFragment hostStatsFragment) {
        this.arg$1 = hostStatsFragment;
    }

    public static Action1 lambdaFactory$(HostStatsFragment hostStatsFragment) {
        return new HostStatsFragment$$Lambda$11(hostStatsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
