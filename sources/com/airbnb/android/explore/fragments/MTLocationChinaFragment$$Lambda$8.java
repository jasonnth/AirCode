package com.airbnb.android.explore.fragments;

import com.airbnb.epoxy.EpoxyController.Interceptor;
import java.util.List;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$8 implements Interceptor {
    private final MTLocationChinaFragment arg$1;

    private MTLocationChinaFragment$$Lambda$8(MTLocationChinaFragment mTLocationChinaFragment) {
        this.arg$1 = mTLocationChinaFragment;
    }

    public static Interceptor lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment) {
        return new MTLocationChinaFragment$$Lambda$8(mTLocationChinaFragment);
    }

    public void intercept(List list) {
        MTLocationChinaFragment.lambda$onActivityCreated$1(this.arg$1, list);
    }
}
