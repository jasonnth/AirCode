package com.airbnb.android.lib.cancellation.host;

import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LateCancellationFragment$$Lambda$2 implements Action1 {
    private final LateCancellationFragment arg$1;

    private LateCancellationFragment$$Lambda$2(LateCancellationFragment lateCancellationFragment) {
        this.arg$1 = lateCancellationFragment;
    }

    public static Action1 lambdaFactory$(LateCancellationFragment lateCancellationFragment) {
        return new LateCancellationFragment$$Lambda$2(lateCancellationFragment);
    }

    public void call(Object obj) {
        LateCancellationFragment.lambda$new$1(this.arg$1, (SupportPhoneNumbersResponse) obj);
    }
}
