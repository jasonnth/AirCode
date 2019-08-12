package com.airbnb.android.itinerary.fragments;

final /* synthetic */ class TripFragment$$Lambda$1 implements Runnable {
    private final TripFragment arg$1;

    private TripFragment$$Lambda$1(TripFragment tripFragment) {
        this.arg$1 = tripFragment;
    }

    public static Runnable lambdaFactory$(TripFragment tripFragment) {
        return new TripFragment$$Lambda$1(tripFragment);
    }

    public void run() {
        this.arg$1.loadingView.setVisibility(0);
    }
}
