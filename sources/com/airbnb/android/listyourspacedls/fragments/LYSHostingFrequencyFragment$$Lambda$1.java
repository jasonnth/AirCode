package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.ListingPersonaResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHostingFrequencyFragment$$Lambda$1 implements Action1 {
    private final LYSHostingFrequencyFragment arg$1;

    private LYSHostingFrequencyFragment$$Lambda$1(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        this.arg$1 = lYSHostingFrequencyFragment;
    }

    public static Action1 lambdaFactory$(LYSHostingFrequencyFragment lYSHostingFrequencyFragment) {
        return new LYSHostingFrequencyFragment$$Lambda$1(lYSHostingFrequencyFragment);
    }

    public void call(Object obj) {
        LYSHostingFrequencyFragment.lambda$new$0(this.arg$1, (ListingPersonaResponse) obj);
    }
}
