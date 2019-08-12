package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.ListingPersonaResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRentHistoryFragment$$Lambda$3 implements Action1 {
    private final LYSRentHistoryFragment arg$1;

    private LYSRentHistoryFragment$$Lambda$3(LYSRentHistoryFragment lYSRentHistoryFragment) {
        this.arg$1 = lYSRentHistoryFragment;
    }

    public static Action1 lambdaFactory$(LYSRentHistoryFragment lYSRentHistoryFragment) {
        return new LYSRentHistoryFragment$$Lambda$3(lYSRentHistoryFragment);
    }

    public void call(Object obj) {
        LYSRentHistoryFragment.lambda$new$1(this.arg$1, (ListingPersonaResponse) obj);
    }
}
