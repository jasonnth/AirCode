package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.models.PopularDestinationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$6 implements Action1 {
    private final MTLocationChinaFragment arg$1;

    private MTLocationChinaFragment$$Lambda$6(MTLocationChinaFragment mTLocationChinaFragment) {
        this.arg$1 = mTLocationChinaFragment;
    }

    public static Action1 lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment) {
        return new MTLocationChinaFragment$$Lambda$6(mTLocationChinaFragment);
    }

    public void call(Object obj) {
        MTLocationChinaFragment.lambda$new$8(this.arg$1, (PopularDestinationResponse) obj);
    }
}
