package com.airbnb.android.core.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;

final /* synthetic */ class ListingTrayCarouselAdapter$$Lambda$1 implements OnClickListener {
    private final ListingTrayCarouselAdapter arg$1;
    private final ListingTrayItem arg$2;

    private ListingTrayCarouselAdapter$$Lambda$1(ListingTrayCarouselAdapter listingTrayCarouselAdapter, ListingTrayItem listingTrayItem) {
        this.arg$1 = listingTrayCarouselAdapter;
        this.arg$2 = listingTrayItem;
    }

    public static OnClickListener lambdaFactory$(ListingTrayCarouselAdapter listingTrayCarouselAdapter, ListingTrayItem listingTrayItem) {
        return new ListingTrayCarouselAdapter$$Lambda$1(listingTrayCarouselAdapter, listingTrayItem);
    }

    public void onClick(View view) {
        this.arg$1.carouselClickListener.onCarouselItemClicked(view, this.arg$2);
    }
}
