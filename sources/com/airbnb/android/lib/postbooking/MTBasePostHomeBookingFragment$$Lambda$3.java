package com.airbnb.android.lib.postbooking;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;

final /* synthetic */ class MTBasePostHomeBookingFragment$$Lambda$3 implements OnClickListener {
    private final MTBasePostHomeBookingFragment arg$1;

    private MTBasePostHomeBookingFragment$$Lambda$3(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        this.arg$1 = mTBasePostHomeBookingFragment;
    }

    public static OnClickListener lambdaFactory$(MTBasePostHomeBookingFragment mTBasePostHomeBookingFragment) {
        return new MTBasePostHomeBookingFragment$$Lambda$3(mTBasePostHomeBookingFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HomeActivityIntents.intentForSearchLanding(this.arg$1.getActivity()));
    }
}
