package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSmartPricingFragment$$Lambda$7 implements Action1 {
    private final LYSSmartPricingFragment arg$1;

    private LYSSmartPricingFragment$$Lambda$7(LYSSmartPricingFragment lYSSmartPricingFragment) {
        this.arg$1 = lYSSmartPricingFragment;
    }

    public static Action1 lambdaFactory$(LYSSmartPricingFragment lYSSmartPricingFragment) {
        return new LYSSmartPricingFragment$$Lambda$7(lYSSmartPricingFragment);
    }

    public void call(Object obj) {
        LYSSmartPricingFragment.lambda$new$6(this.arg$1, (SimpleListingResponse) obj);
    }
}
