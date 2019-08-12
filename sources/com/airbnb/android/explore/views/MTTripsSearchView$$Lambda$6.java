package com.airbnb.android.explore.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTTripsSearchView$$Lambda$6 implements OnClickListener {
    private final MTTripsSearchView arg$1;

    private MTTripsSearchView$$Lambda$6(MTTripsSearchView mTTripsSearchView) {
        this.arg$1 = mTTripsSearchView;
    }

    public static OnClickListener lambdaFactory$(MTTripsSearchView mTTripsSearchView) {
        return new MTTripsSearchView$$Lambda$6(mTTripsSearchView);
    }

    public void onClick(View view) {
        MTTripsSearchView.lambda$init$2(this.arg$1, view);
    }
}
