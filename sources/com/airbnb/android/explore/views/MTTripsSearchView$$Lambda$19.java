package com.airbnb.android.explore.views;

import com.airbnb.android.utils.animation.ManualValueAnimator.UpdateListener;

final /* synthetic */ class MTTripsSearchView$$Lambda$19 implements UpdateListener {
    private final MTTripsSearchView arg$1;

    private MTTripsSearchView$$Lambda$19(MTTripsSearchView mTTripsSearchView) {
        this.arg$1 = mTTripsSearchView;
    }

    public static UpdateListener lambdaFactory$(MTTripsSearchView mTTripsSearchView) {
        return new MTTripsSearchView$$Lambda$19(mTTripsSearchView);
    }

    public void onUpdate(float f, float f2) {
        this.arg$1.locationRow.getTitleTextView().setAlpha(f2);
    }
}
