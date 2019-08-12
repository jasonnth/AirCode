package com.airbnb.android.p011p3;

import android.view.View;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$21 */
final /* synthetic */ class P3EpoxyController$$Lambda$21 implements CarouselItemClickListener {
    private final P3EpoxyController arg$1;

    private P3EpoxyController$$Lambda$21(P3EpoxyController p3EpoxyController) {
        this.arg$1 = p3EpoxyController;
    }

    public static CarouselItemClickListener lambdaFactory$(P3EpoxyController p3EpoxyController) {
        return new P3EpoxyController$$Lambda$21(p3EpoxyController);
    }

    public void onCarouselItemClicked(View view, ListingTrayItem listingTrayItem) {
        P3EpoxyController.lambda$addSimilarListings$19(this.arg$1, view, listingTrayItem);
    }
}
