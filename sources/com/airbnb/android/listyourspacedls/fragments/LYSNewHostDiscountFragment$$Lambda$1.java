package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.NewHostPromoResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSNewHostDiscountFragment$$Lambda$1 implements Action1 {
    private final LYSNewHostDiscountFragment arg$1;

    private LYSNewHostDiscountFragment$$Lambda$1(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        this.arg$1 = lYSNewHostDiscountFragment;
    }

    public static Action1 lambdaFactory$(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        return new LYSNewHostDiscountFragment$$Lambda$1(lYSNewHostDiscountFragment);
    }

    public void call(Object obj) {
        LYSNewHostDiscountFragment.lambda$new$1(this.arg$1, (NewHostPromoResponse) obj);
    }
}
