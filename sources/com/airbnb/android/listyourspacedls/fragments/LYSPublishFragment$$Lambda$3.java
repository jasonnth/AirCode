package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPublishFragment$$Lambda$3 implements Action1 {
    private final LYSPublishFragment arg$1;

    private LYSPublishFragment$$Lambda$3(LYSPublishFragment lYSPublishFragment) {
        this.arg$1 = lYSPublishFragment;
    }

    public static Action1 lambdaFactory$(LYSPublishFragment lYSPublishFragment) {
        return new LYSPublishFragment$$Lambda$3(lYSPublishFragment);
    }

    public void call(Object obj) {
        LYSPublishFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
