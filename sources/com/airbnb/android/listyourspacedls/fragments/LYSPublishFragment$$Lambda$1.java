package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSPublishFragment$$Lambda$1 implements Action1 {
    private final LYSPublishFragment arg$1;

    private LYSPublishFragment$$Lambda$1(LYSPublishFragment lYSPublishFragment) {
        this.arg$1 = lYSPublishFragment;
    }

    public static Action1 lambdaFactory$(LYSPublishFragment lYSPublishFragment) {
        return new LYSPublishFragment$$Lambda$1(lYSPublishFragment);
    }

    public void call(Object obj) {
        LYSPublishFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
