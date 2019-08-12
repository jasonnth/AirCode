package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PostBookingBusinessTravelPromoFragment$$Lambda$1 implements OnClickListener {
    private final PostBookingBusinessTravelPromoFragment arg$1;

    private PostBookingBusinessTravelPromoFragment$$Lambda$1(PostBookingBusinessTravelPromoFragment postBookingBusinessTravelPromoFragment) {
        this.arg$1 = postBookingBusinessTravelPromoFragment;
    }

    public static OnClickListener lambdaFactory$(PostBookingBusinessTravelPromoFragment postBookingBusinessTravelPromoFragment) {
        return new PostBookingBusinessTravelPromoFragment$$Lambda$1(postBookingBusinessTravelPromoFragment);
    }

    public void onClick(View view) {
        this.arg$1.goToAddEmail();
    }
}
