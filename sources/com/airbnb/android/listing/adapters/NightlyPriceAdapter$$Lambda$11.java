package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Currency;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$11 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final Currency arg$2;

    private NightlyPriceAdapter$$Lambda$11(NightlyPriceAdapter nightlyPriceAdapter, Currency currency) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = currency;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, Currency currency) {
        return new NightlyPriceAdapter$$Lambda$11(nightlyPriceAdapter, currency);
    }

    public void onClick(View view) {
        this.arg$1.pricingJitneyLogger.adoptSmartPricingMinPriceTip(this.arg$2.getCurrencyCode(), (long) this.arg$1.settings.getMinPrice(), (long) this.arg$1.settings.getSuggestedMinPrice(), this.arg$1.settings.toSmartPricingSettingsContext());
    }
}
