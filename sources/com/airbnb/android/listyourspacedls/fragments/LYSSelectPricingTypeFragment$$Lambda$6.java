package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSSelectPricingTypeFragment$$Lambda$6 implements Action1 {
    private final LYSSelectPricingTypeFragment arg$1;

    private LYSSelectPricingTypeFragment$$Lambda$6(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        this.arg$1 = lYSSelectPricingTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        return new LYSSelectPricingTypeFragment$$Lambda$6(lYSSelectPricingTypeFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
