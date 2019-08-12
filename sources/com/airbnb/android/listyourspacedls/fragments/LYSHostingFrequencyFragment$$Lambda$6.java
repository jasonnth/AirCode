package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHostingFrequencyFragment$$Lambda$6 implements Action1 {
    private final LYSHostingFrequencyFragment arg$1;

    private LYSHostingFrequencyFragment$$Lambda$6(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        this.arg$1 = lYSHostingFrequencyFragment;
    }

    public static Action1 lambdaFactory$(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        return new LYSHostingFrequencyFragment$$Lambda$6(lYSHostingFrequencyFragment);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
