package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSSelectPricingTypeFragment$$Lambda$7 implements OnClickListener {
    private final LYSSelectPricingTypeFragment arg$1;

    private LYSSelectPricingTypeFragment$$Lambda$7(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        this.arg$1 = lYSSelectPricingTypeFragment;
    }

    public static OnClickListener lambdaFactory$(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment) {
        return new LYSSelectPricingTypeFragment$$Lambda$7(lYSSelectPricingTypeFragment);
    }

    public void onClick(View view) {
        this.arg$1.showDisclaimer();
    }
}
