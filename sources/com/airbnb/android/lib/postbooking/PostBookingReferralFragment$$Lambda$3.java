package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PostBookingReferralFragment$$Lambda$3 implements OnClickListener {
    private final PostBookingReferralFragment arg$1;

    private PostBookingReferralFragment$$Lambda$3(PostBookingReferralFragment postBookingReferralFragment) {
        this.arg$1 = postBookingReferralFragment;
    }

    public static OnClickListener lambdaFactory$(PostBookingReferralFragment postBookingReferralFragment) {
        return new PostBookingReferralFragment$$Lambda$3(postBookingReferralFragment);
    }

    public void onClick(View view) {
        PostBookingReferralFragment.lambda$onCreateView$2(this.arg$1, view);
    }
}
