package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSHouseRulesFragment$$Lambda$3 implements Action1 {
    private final LYSHouseRulesFragment arg$1;

    private LYSHouseRulesFragment$$Lambda$3(LYSHouseRulesFragment lYSHouseRulesFragment) {
        this.arg$1 = lYSHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(LYSHouseRulesFragment lYSHouseRulesFragment) {
        return new LYSHouseRulesFragment$$Lambda$3(lYSHouseRulesFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
