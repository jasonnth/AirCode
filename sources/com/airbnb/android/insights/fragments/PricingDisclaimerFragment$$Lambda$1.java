package com.airbnb.android.insights.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PricingDisclaimerFragment$$Lambda$1 implements OnClickListener {
    private final PricingDisclaimerFragment arg$1;

    private PricingDisclaimerFragment$$Lambda$1(PricingDisclaimerFragment pricingDisclaimerFragment) {
        this.arg$1 = pricingDisclaimerFragment;
    }

    public static OnClickListener lambdaFactory$(PricingDisclaimerFragment pricingDisclaimerFragment) {
        return new PricingDisclaimerFragment$$Lambda$1(pricingDisclaimerFragment);
    }

    public void onClick(View view) {
        ((InsightsParentFragment) this.arg$1.getParentFragment()).onBackPressed();
    }
}
