package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HostDemandsDetailFragment$$Lambda$3 implements Action1 {
    private final HostDemandsDetailFragment arg$1;

    private HostDemandsDetailFragment$$Lambda$3(HostDemandsDetailFragment hostDemandsDetailFragment) {
        this.arg$1 = hostDemandsDetailFragment;
    }

    public static Action1 lambdaFactory$(HostDemandsDetailFragment hostDemandsDetailFragment) {
        return new HostDemandsDetailFragment$$Lambda$3(hostDemandsDetailFragment);
    }

    public void call(Object obj) {
        HostDemandsDetailFragment.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}
