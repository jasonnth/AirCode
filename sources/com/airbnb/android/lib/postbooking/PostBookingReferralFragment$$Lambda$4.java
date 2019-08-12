package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PostBookingReferralFragment$$Lambda$4 implements OnClickListener {
    private final PostBookingReferralFragment arg$1;

    private PostBookingReferralFragment$$Lambda$4(PostBookingReferralFragment postBookingReferralFragment) {
        this.arg$1 = postBookingReferralFragment;
    }

    public static OnClickListener lambdaFactory$(PostBookingReferralFragment postBookingReferralFragment) {
        return new PostBookingReferralFragment$$Lambda$4(postBookingReferralFragment);
    }

    public void onClick(View view) {
        this.arg$1.postBookingFlowController.onCurrentStateFinished();
    }
}
