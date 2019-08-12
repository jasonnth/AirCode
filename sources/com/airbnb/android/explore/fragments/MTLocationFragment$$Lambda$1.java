package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationFragment$$Lambda$1 implements Action1 {
    private final MTLocationFragment arg$1;

    private MTLocationFragment$$Lambda$1(MTLocationFragment mTLocationFragment) {
        this.arg$1 = mTLocationFragment;
    }

    public static Action1 lambdaFactory$(MTLocationFragment mTLocationFragment) {
        return new MTLocationFragment$$Lambda$1(mTLocationFragment);
    }

    public void call(Object obj) {
        MTLocationFragment.lambda$new$2(this.arg$1, (AutocompleteResponse) obj);
    }
}
