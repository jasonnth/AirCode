package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Currency;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$12 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final Currency arg$2;

    private NightlyPriceAdapter$$Lambda$12(NightlyPriceAdapter nightlyPriceAdapter, Currency currency) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = currency;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, Currency currency) {
        return new NightlyPriceAdapter$$Lambda$12(nightlyPriceAdapter, currency);
    }

    public void onClick(View view) {
        this.arg$1.pricingJitneyLogger.adoptSmartPricingMaxPriceTip(this.arg$2.getCurrencyCode(), (long) this.arg$1.settings.getMaxPrice(), (long) this.arg$1.settings.getSuggestedMaxPrice(), this.arg$1.settings.toSmartPricingSettingsContext());
    }
}
