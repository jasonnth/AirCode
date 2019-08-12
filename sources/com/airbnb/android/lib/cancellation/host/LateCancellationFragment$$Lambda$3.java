package com.airbnb.android.lib.cancellation.host;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LateCancellationFragment$$Lambda$3 implements Action1 {
    private final LateCancellationFragment arg$1;

    private LateCancellationFragment$$Lambda$3(LateCancellationFragment lateCancellationFragment) {
        this.arg$1 = lateCancellationFragment;
    }

    public static Action1 lambdaFactory$(LateCancellationFragment lateCancellationFragment) {
        return new LateCancellationFragment$$Lambda$3(lateCancellationFragment);
    }

    public void call(Object obj) {
        LateCancellationFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
