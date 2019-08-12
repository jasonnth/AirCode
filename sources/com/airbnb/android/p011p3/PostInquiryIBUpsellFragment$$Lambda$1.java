package com.airbnb.android.p011p3;

import android.view.View;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;

/* renamed from: com.airbnb.android.p3.PostInquiryIBUpsellFragment$$Lambda$1 */
final /* synthetic */ class PostInquiryIBUpsellFragment$$Lambda$1 implements CarouselItemClickListener {
    private final PostInquiryIBUpsellFragment arg$1;

    private PostInquiryIBUpsellFragment$$Lambda$1(PostInquiryIBUpsellFragment postInquiryIBUpsellFragment) {
        this.arg$1 = postInquiryIBUpsellFragment;
    }

    public static CarouselItemClickListener lambdaFactory$(PostInquiryIBUpsellFragment postInquiryIBUpsellFragment) {
        return new PostInquiryIBUpsellFragment$$Lambda$1(postInquiryIBUpsellFragment);
    }

    public void onCarouselItemClicked(View view, ListingTrayItem listingTrayItem) {
        PostInquiryIBUpsellFragment.lambda$setupSimilarListings$0(this.arg$1, view, listingTrayItem);
    }
}
