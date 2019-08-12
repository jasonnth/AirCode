package com.airbnb.android.explore.fragments;

import com.airbnb.android.explore.requests.SatoriAutocompleteResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$3 implements Action1 {
    private final MTLocationChinaFragment arg$1;

    private MTLocationChinaFragment$$Lambda$3(MTLocationChinaFragment mTLocationChinaFragment) {
        this.arg$1 = mTLocationChinaFragment;
    }

    public static Action1 lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment) {
        return new MTLocationChinaFragment$$Lambda$3(mTLocationChinaFragment);
    }

    public void call(Object obj) {
        MTLocationChinaFragment.lambda$new$5(this.arg$1, (SatoriAutocompleteResponse) obj);
    }
}
