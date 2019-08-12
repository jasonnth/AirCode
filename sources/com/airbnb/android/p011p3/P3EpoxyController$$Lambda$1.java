package com.airbnb.android.p011p3;

import android.widget.ImageView;
import com.airbnb.p027n2.components.HomeMarquee.ImageCarouselItemClickListener;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$1 */
final /* synthetic */ class P3EpoxyController$$Lambda$1 implements ImageCarouselItemClickListener {
    private final ListingDetailsController arg$1;

    private P3EpoxyController$$Lambda$1(ListingDetailsController listingDetailsController) {
        this.arg$1 = listingDetailsController;
    }

    public static ImageCarouselItemClickListener lambdaFactory$(ListingDetailsController listingDetailsController) {
        return new P3EpoxyController$$Lambda$1(listingDetailsController);
    }

    public void onImageCarouselItemClicked(ImageView imageView, int i) {
        this.arg$1.onHomeMarqueeImageClicked(imageView, i);
    }
}
