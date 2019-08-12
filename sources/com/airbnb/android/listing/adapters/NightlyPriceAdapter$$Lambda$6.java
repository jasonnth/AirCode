package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.DynamicPricingControl;
import java.util.Currency;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$6 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final Currency arg$2;
    private final DynamicPricingControl arg$3;

    private NightlyPriceAdapter$$Lambda$6(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, DynamicPricingControl dynamicPricingControl) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = currency;
        this.arg$3 = dynamicPricingControl;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, DynamicPricingControl dynamicPricingControl) {
        return new NightlyPriceAdapter$$Lambda$6(nightlyPriceAdapter, currency, dynamicPricingControl);
    }

    public void onClick(View view) {
        NightlyPriceAdapter.lambda$new$5(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
