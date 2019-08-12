package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSmartPricingFragment$$Lambda$4 implements Action1 {
    private final LYSSmartPricingFragment arg$1;

    private LYSSmartPricingFragment$$Lambda$4(LYSSmartPricingFragment lYSSmartPricingFragment) {
        this.arg$1 = lYSSmartPricingFragment;
    }

    public static Action1 lambdaFactory$(LYSSmartPricingFragment lYSSmartPricingFragment) {
        return new LYSSmartPricingFragment$$Lambda$4(lYSSmartPricingFragment);
    }

    public void call(Object obj) {
        LYSSmartPricingFragment.lambda$new$3(this.arg$1, (DemandBasedPricingResponse) obj);
    }
}
