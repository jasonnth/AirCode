package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PostBookingLandingFragment$$Lambda$1 implements OnClickListener {
    private final PostBookingLandingFragment arg$1;

    private PostBookingLandingFragment$$Lambda$1(PostBookingLandingFragment postBookingLandingFragment) {
        this.arg$1 = postBookingLandingFragment;
    }

    public static OnClickListener lambdaFactory$(PostBookingLandingFragment postBookingLandingFragment) {
        return new PostBookingLandingFragment$$Lambda$1(postBookingLandingFragment);
    }

    public void onClick(View view) {
        PostBookingLandingFragment.lambda$setUpViews$0(this.arg$1, view);
    }
}
