package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PricingQuotePricingDetails$$Lambda$2 implements OnClickListener {
    private final PricingQuotePricingDetails arg$1;

    private PricingQuotePricingDetails$$Lambda$2(PricingQuotePricingDetails pricingQuotePricingDetails) {
        this.arg$1 = pricingQuotePricingDetails;
    }

    public static OnClickListener lambdaFactory$(PricingQuotePricingDetails pricingQuotePricingDetails) {
        return new PricingQuotePricingDetails$$Lambda$2(pricingQuotePricingDetails);
    }

    public void onClick(View view) {
        PricingQuotePricingDetails.lambda$setupPriceFields$1(this.arg$1, view);
    }
}
