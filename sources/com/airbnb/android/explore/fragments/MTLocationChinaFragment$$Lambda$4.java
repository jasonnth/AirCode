package com.airbnb.android.explore.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$4 implements Action1 {
    private final MTLocationChinaFragment arg$1;

    private MTLocationChinaFragment$$Lambda$4(MTLocationChinaFragment mTLocationChinaFragment) {
        this.arg$1 = mTLocationChinaFragment;
    }

    public static Action1 lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment) {
        return new MTLocationChinaFragment$$Lambda$4(mTLocationChinaFragment);
    }

    public void call(Object obj) {
        this.arg$1.isAutocompleteLoading = false;
    }
}
