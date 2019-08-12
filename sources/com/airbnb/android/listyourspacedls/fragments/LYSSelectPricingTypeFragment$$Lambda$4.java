package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSSelectPricingTypeFragment$$Lambda$4 implements Action1 {
    private final LYSSelectPricingTypeFragment arg$1;

    private LYSSelectPricingTypeFragment$$Lambda$4(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        this.arg$1 = lYSSelectPricingTypeFragment;
    }

    public static Action1 lambdaFactory$(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        return new LYSSelectPricingTypeFragment$$Lambda$4(lYSSelectPricingTypeFragment);
    }

    public void call(Object obj) {
        LYSSelectPricingTypeFragment.lambda$new$3(this.arg$1, (SimpleListingResponse) obj);
    }
}
