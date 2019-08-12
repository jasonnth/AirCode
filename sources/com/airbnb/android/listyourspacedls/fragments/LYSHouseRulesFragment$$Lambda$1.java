package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.GuestControlsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHouseRulesFragment$$Lambda$1 implements Action1 {
    private final LYSHouseRulesFragment arg$1;

    private LYSHouseRulesFragment$$Lambda$1(LYSHouseRulesFragment lYSHouseRulesFragment) {
        this.arg$1 = lYSHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(LYSHouseRulesFragment lYSHouseRulesFragment) {
        return new LYSHouseRulesFragment$$Lambda$1(lYSHouseRulesFragment);
    }

    public void call(Object obj) {
        LYSHouseRulesFragment.lambda$new$0(this.arg$1, (GuestControlsResponse) obj);
    }
}
