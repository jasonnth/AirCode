package com.airbnb.android.explore.views;

import com.airbnb.android.utils.animation.ManualValueAnimator.UpdateListener;

final /* synthetic */ class MTTripsTabletSearchView$$Lambda$1 implements UpdateListener {
    private final MTTripsTabletSearchView arg$1;

    private MTTripsTabletSearchView$$Lambda$1(MTTripsTabletSearchView mTTripsTabletSearchView) {
        this.arg$1 = mTTripsTabletSearchView;
    }

    public static UpdateListener lambdaFactory$(MTTripsTabletSearchView mTTripsTabletSearchView) {
        return new MTTripsTabletSearchView$$Lambda$1(mTTripsTabletSearchView);
    }

    public void onUpdate(float f, float f2) {
        MTTripsTabletSearchView.lambda$initColorAnimators$0(this.arg$1, f, f2);
    }
}
