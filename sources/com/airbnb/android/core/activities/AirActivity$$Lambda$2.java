package com.airbnb.android.core.activities;

final /* synthetic */ class AirActivity$$Lambda$2 implements Runnable {
    private final AirActivity arg$1;
    private final long arg$2;

    private AirActivity$$Lambda$2(AirActivity airActivity, long j) {
        this.arg$1 = airActivity;
        this.arg$2 = j;
    }

    public static Runnable lambdaFactory$(AirActivity airActivity, long j) {
        return new AirActivity$$Lambda$2(airActivity, j);
    }

    public void run() {
        this.arg$1.launchAnalytics.trackFirstActivityLoaded(this.arg$1.getClass().getSimpleName(), this.arg$2);
    }
}
