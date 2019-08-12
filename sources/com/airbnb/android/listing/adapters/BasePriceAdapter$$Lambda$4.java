package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.SanitizeUtils;
import java.util.Currency;

final /* synthetic */ class BasePriceAdapter$$Lambda$4 implements OnClickListener {
    private final BasePriceAdapter arg$1;
    private final Currency arg$2;
    private final Listing arg$3;
    private final int arg$4;

    private BasePriceAdapter$$Lambda$4(BasePriceAdapter basePriceAdapter, Currency currency, Listing listing, int i) {
        this.arg$1 = basePriceAdapter;
        this.arg$2 = currency;
        this.arg$3 = listing;
        this.arg$4 = i;
    }

    public static OnClickListener lambdaFactory$(BasePriceAdapter basePriceAdapter, Currency currency, Listing listing, int i) {
        return new BasePriceAdapter$$Lambda$4(basePriceAdapter, currency, listing, i);
    }

    public void onClick(View view) {
        this.arg$1.pricingJitneyLogger.adoptBasePrice(this.arg$2.getCurrencyCode(), (long) SanitizeUtils.zeroIfNull(Integer.valueOf(this.arg$3.getListingPrice())), (long) this.arg$4);
    }
}
