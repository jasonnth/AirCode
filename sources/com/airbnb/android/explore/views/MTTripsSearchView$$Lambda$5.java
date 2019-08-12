package com.airbnb.android.explore.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTTripsSearchView$$Lambda$5 implements OnClickListener {
    private final MTTripsSearchView arg$1;

    private MTTripsSearchView$$Lambda$5(MTTripsSearchView mTTripsSearchView) {
        this.arg$1 = mTTripsSearchView;
    }

    public static OnClickListener lambdaFactory$(MTTripsSearchView mTTripsSearchView) {
        return new MTTripsSearchView$$Lambda$5(mTTripsSearchView);
    }

    public void onClick(View view) {
        MTTripsSearchView.lambda$init$1(this.arg$1, view);
    }
}
