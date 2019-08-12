package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import java.util.Currency;

final /* synthetic */ class NightlyPriceAdapter$$Lambda$8 implements OnClickListener {
    private final NightlyPriceAdapter arg$1;
    private final Currency arg$2;
    private final Listing arg$3;

    private NightlyPriceAdapter$$Lambda$8(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, Listing listing) {
        this.arg$1 = nightlyPriceAdapter;
        this.arg$2 = currency;
        this.arg$3 = listing;
    }

    public static OnClickListener lambdaFactory$(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, Listing listing) {
        return new NightlyPriceAdapter$$Lambda$8(nightlyPriceAdapter, currency, listing);
    }

    public void onClick(View view) {
        NightlyPriceAdapter.lambda$new$7(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
