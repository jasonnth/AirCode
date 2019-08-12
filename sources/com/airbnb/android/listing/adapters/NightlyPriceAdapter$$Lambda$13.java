package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.SanitizeUtils;
import java.util.Currency;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$13 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final Currency arg$2;
    private final Listing arg$3;

    private NightlyPriceAdapter$$Lambda$13(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, Listing listing) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = currency;
        this.arg$3 = listing;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, Listing listing) {
        return new NightlyPriceAdapter$$Lambda$13(nightlyPriceAdapter, currency, listing);
    }

    public void onClick(View view) {
        this.arg$1.pricingJitneyLogger.adoptBasePrice(this.arg$2.getCurrencyCode(), (long) SanitizeUtils.zeroIfNull(Integer.valueOf(this.arg$1.getListingPrice(this.arg$3))), (long) this.arg$3.getAutoPricingDaily());
    }
}
