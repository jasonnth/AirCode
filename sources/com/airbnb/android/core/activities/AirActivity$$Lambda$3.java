package com.airbnb.android.core.activities;

final /* synthetic */ class AirActivity$$Lambda$3 implements Runnable {
    private final AirActivity arg$1;
    private final long arg$2;

    private AirActivity$$Lambda$3(AirActivity airActivity, long j) {
        this.arg$1 = airActivity;
        this.arg$2 = j;
    }

    public static Runnable lambdaFactory$(AirActivity airActivity, long j) {
        return new AirActivity$$Lambda$3(airActivity, j);
    }

    public void run() {
        this.arg$1.launchAnalytics.trackFirstActivityShown(this.arg$1.getClass().getSimpleName(), this.arg$2);
    }
}
