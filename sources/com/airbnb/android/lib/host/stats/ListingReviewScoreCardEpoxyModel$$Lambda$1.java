package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingReviewScoreCardEpoxyModel$$Lambda$1 implements OnClickListener {
    private final ListingReviewScoreCardEpoxyModel arg$1;

    private ListingReviewScoreCardEpoxyModel$$Lambda$1(ListingReviewScoreCardEpoxyModel listingReviewScoreCardEpoxyModel) {
        this.arg$1 = listingReviewScoreCardEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(ListingReviewScoreCardEpoxyModel listingReviewScoreCardEpoxyModel) {
        return new ListingReviewScoreCardEpoxyModel$$Lambda$1(listingReviewScoreCardEpoxyModel);
    }

    public void onClick(View view) {
        this.arg$1.carouselClickListener.onCarouselItemClicked(view, this.arg$1.listing, this.arg$1.carouselIndex);
    }
}
