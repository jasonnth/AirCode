package com.airbnb.android.explore.fragments;

import com.airbnb.android.explore.requests.SatoriAutocompleteResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationFragment$$Lambda$2 implements Action1 {
    private final MTLocationFragment arg$1;

    private MTLocationFragment$$Lambda$2(MTLocationFragment mTLocationFragment) {
        this.arg$1 = mTLocationFragment;
    }

    public static Action1 lambdaFactory$(MTLocationFragment mTLocationFragment) {
        return new MTLocationFragment$$Lambda$2(mTLocationFragment);
    }

    public void call(Object obj) {
        MTLocationFragment.lambda$new$3(this.arg$1, (SatoriAutocompleteResponse) obj);
    }
}
