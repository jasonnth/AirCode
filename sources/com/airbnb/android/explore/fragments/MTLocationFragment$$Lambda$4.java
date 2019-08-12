package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.responses.TravelDestinationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationFragment$$Lambda$4 implements Action1 {
    private final MTLocationFragment arg$1;

    private MTLocationFragment$$Lambda$4(MTLocationFragment mTLocationFragment) {
        this.arg$1 = mTLocationFragment;
    }

    public static Action1 lambdaFactory$(MTLocationFragment mTLocationFragment) {
        return new MTLocationFragment$$Lambda$4(mTLocationFragment);
    }

    public void call(Object obj) {
        MTLocationFragment.lambda$new$5(this.arg$1, (TravelDestinationsResponse) obj);
    }
}
