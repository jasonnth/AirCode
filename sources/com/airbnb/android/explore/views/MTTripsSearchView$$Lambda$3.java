package com.airbnb.android.explore.views;

final /* synthetic */ class MTTripsSearchView$$Lambda$3 implements Runnable {
    private final MTTripsSearchView arg$1;

    private MTTripsSearchView$$Lambda$3(MTTripsSearchView mTTripsSearchView) {
        this.arg$1 = mTTripsSearchView;
    }

    public static Runnable lambdaFactory$(MTTripsSearchView mTTripsSearchView) {
        return new MTTripsSearchView$$Lambda$3(mTTripsSearchView);
    }

    public void run() {
        this.arg$1.requestLayout();
    }
}
