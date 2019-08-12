package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSSmartPricingFragment$$Lambda$3 implements Action1 {
    private final LYSSmartPricingFragment arg$1;

    private LYSSmartPricingFragment$$Lambda$3(LYSSmartPricingFragment lYSSmartPricingFragment) {
        this.arg$1 = lYSSmartPricingFragment;
    }

    public static Action1 lambdaFactory$(LYSSmartPricingFragment lYSSmartPricingFragment) {
        return new LYSSmartPricingFragment$$Lambda$3(lYSSmartPricingFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
