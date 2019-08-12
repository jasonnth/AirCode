package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHostingFrequencyFragment$$Lambda$3 implements Action1 {
    private final LYSHostingFrequencyFragment arg$1;

    private LYSHostingFrequencyFragment$$Lambda$3(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        this.arg$1 = lYSHostingFrequencyFragment;
    }

    public static Action1 lambdaFactory$(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        return new LYSHostingFrequencyFragment$$Lambda$3(lYSHostingFrequencyFragment);
    }

    public void call(Object obj) {
        LYSHostingFrequencyFragment.lambda$new$1(this.arg$1, (AirBatchResponse) obj);
    }
}
