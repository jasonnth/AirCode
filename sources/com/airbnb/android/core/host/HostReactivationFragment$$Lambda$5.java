package com.airbnb.android.core.host;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HostReactivationFragment$$Lambda$5 implements Action1 {
    private final HostReactivationFragment arg$1;

    private HostReactivationFragment$$Lambda$5(HostReactivationFragment hostReactivationFragment) {
        this.arg$1 = hostReactivationFragment;
    }

    public static Action1 lambdaFactory$(HostReactivationFragment hostReactivationFragment) {
        return new HostReactivationFragment$$Lambda$5(hostReactivationFragment);
    }

    public void call(Object obj) {
        HostReactivationFragment.lambda$new$5(this.arg$1, (AirRequestNetworkException) obj);
    }
}