package com.airbnb.android.insights.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingInsightsAdapter$$Lambda$2 implements OnClickListener {
    private final ListingInsightsAdapter arg$1;
    private final Listing arg$2;

    private ListingInsightsAdapter$$Lambda$2(ListingInsightsAdapter listingInsightsAdapter, Listing listing) {
        this.arg$1 = listingInsightsAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(ListingInsightsAdapter listingInsightsAdapter, Listing listing) {
        return new ListingInsightsAdapter$$Lambda$2(listingInsightsAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onGoToCardsClick(this.arg$2);
    }
}
