package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRentHistoryFragment$$Lambda$4 implements Action1 {
    private final LYSRentHistoryFragment arg$1;

    private LYSRentHistoryFragment$$Lambda$4(LYSRentHistoryFragment lYSRentHistoryFragment) {
        this.arg$1 = lYSRentHistoryFragment;
    }

    public static Action1 lambdaFactory$(LYSRentHistoryFragment lYSRentHistoryFragment) {
        return new LYSRentHistoryFragment$$Lambda$4(lYSRentHistoryFragment);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
