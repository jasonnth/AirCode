package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;

final /* synthetic */ class BaseExploreAdapter$$Lambda$12 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final Listing arg$2;
    private final PricingQuote arg$3;

    private BaseExploreAdapter$$Lambda$12(BaseExploreAdapter baseExploreAdapter, Listing listing, PricingQuote pricingQuote) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = listing;
        this.arg$3 = pricingQuote;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, Listing listing, PricingQuote pricingQuote) {
        return new BaseExploreAdapter$$Lambda$12(baseExploreAdapter, listing, pricingQuote);
    }

    public void onClick(View view) {
        this.arg$1.launchP3ForListing(this.arg$2, this.arg$3, view);
    }
}
