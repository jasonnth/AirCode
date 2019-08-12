package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HostListingSelectorFragment$$Lambda$1 implements Action1 {
    private final HostListingSelectorFragment arg$1;

    private HostListingSelectorFragment$$Lambda$1(HostListingSelectorFragment hostListingSelectorFragment) {
        this.arg$1 = hostListingSelectorFragment;
    }

    public static Action1 lambdaFactory$(HostListingSelectorFragment hostListingSelectorFragment) {
        return new HostListingSelectorFragment$$Lambda$1(hostListingSelectorFragment);
    }

    public void call(Object obj) {
        HostListingSelectorFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
