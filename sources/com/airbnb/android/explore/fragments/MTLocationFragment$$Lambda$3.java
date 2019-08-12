package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.responses.GetSavedSearchesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationFragment$$Lambda$3 implements Action1 {
    private final MTLocationFragment arg$1;

    private MTLocationFragment$$Lambda$3(MTLocationFragment mTLocationFragment) {
        this.arg$1 = mTLocationFragment;
    }

    public static Action1 lambdaFactory$(MTLocationFragment mTLocationFragment) {
        return new MTLocationFragment$$Lambda$3(mTLocationFragment);
    }

    public void call(Object obj) {
        MTLocationFragment.lambda$new$4(this.arg$1, (GetSavedSearchesResponse) obj);
    }
}
