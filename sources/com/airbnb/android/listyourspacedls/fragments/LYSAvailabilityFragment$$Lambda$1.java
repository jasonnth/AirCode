package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAvailabilityFragment$$Lambda$1 implements Action1 {
    private final LYSAvailabilityFragment arg$1;

    private LYSAvailabilityFragment$$Lambda$1(LYSAvailabilityFragment lYSAvailabilityFragment) {
        this.arg$1 = lYSAvailabilityFragment;
    }

    public static Action1 lambdaFactory$(LYSAvailabilityFragment lYSAvailabilityFragment) {
        return new LYSAvailabilityFragment$$Lambda$1(lYSAvailabilityFragment);
    }

    public void call(Object obj) {
        LYSAvailabilityFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
