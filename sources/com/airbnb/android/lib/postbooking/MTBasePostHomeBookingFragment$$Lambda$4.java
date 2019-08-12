package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTBasePostHomeBookingFragment$$Lambda$4 implements OnClickListener {
    private final MTBasePostHomeBookingFragment arg$1;

    private MTBasePostHomeBookingFragment$$Lambda$4(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        this.arg$1 = mTBasePostHomeBookingFragment;
    }

    public static OnClickListener lambdaFactory$(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        return new MTBasePostHomeBookingFragment$$Lambda$4(mTBasePostHomeBookingFragment);
    }

    public void onClick(View view) {
        this.arg$1.postBookingFlowController.onCurrentStateFinished();
    }
}
