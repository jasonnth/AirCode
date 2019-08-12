package com.airbnb.android.lib.cancellation.host;

import p032rx.functions.Action1;

final /* synthetic */ class LateCancellationFragment$$Lambda$1 implements Action1 {
    private final LateCancellationFragment arg$1;

    private LateCancellationFragment$$Lambda$1(LateCancellationFragment lateCancellationFragment) {
        this.arg$1 = lateCancellationFragment;
    }

    public static Action1 lambdaFactory$(LateCancellationFragment lateCancellationFragment) {
        return new LateCancellationFragment$$Lambda$1(lateCancellationFragment);
    }

    public void call(Object obj) {
        this.arg$1.isLoading = false;
    }
}
