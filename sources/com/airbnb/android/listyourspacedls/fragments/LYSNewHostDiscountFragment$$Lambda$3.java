package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSNewHostDiscountFragment$$Lambda$3 implements Action1 {
    private final LYSNewHostDiscountFragment arg$1;

    private LYSNewHostDiscountFragment$$Lambda$3(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        this.arg$1 = lYSNewHostDiscountFragment;
    }

    public static Action1 lambdaFactory$(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        return new LYSNewHostDiscountFragment$$Lambda$3(lYSNewHostDiscountFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.epoxyController);
    }
}
