package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSmartPricingFragment$$Lambda$2 implements Action1 {
    private final LYSSmartPricingFragment arg$1;

    private LYSSmartPricingFragment$$Lambda$2(LYSSmartPricingFragment lYSSmartPricingFragment) {
        this.arg$1 = lYSSmartPricingFragment;
    }

    public static Action1 lambdaFactory$(LYSSmartPricingFragment lYSSmartPricingFragment) {
        return new LYSSmartPricingFragment$$Lambda$2(lYSSmartPricingFragment);
    }

    public void call(Object obj) {
        LYSSmartPricingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
