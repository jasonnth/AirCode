package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.InviteGuestsActivity;

final /* synthetic */ class PostBookingLandingFragment$$Lambda$2 implements OnClickListener {
    private final PostBookingLandingFragment arg$1;

    private PostBookingLandingFragment$$Lambda$2(PostBookingLandingFragment postBookingLandingFragment) {
        this.arg$1 = postBookingLandingFragment;
    }

    public static OnClickListener lambdaFactory$(PostBookingLandingFragment postBookingLandingFragment) {
        return new PostBookingLandingFragment$$Lambda$2(postBookingLandingFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(InviteGuestsActivity.newIntent(this.arg$1.getContext(), this.arg$1.postBookingFlowController.getReservation().getConfirmationCode()));
    }
}
