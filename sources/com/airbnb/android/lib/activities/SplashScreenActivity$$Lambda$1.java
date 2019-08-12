package com.airbnb.android.lib.activities;

final /* synthetic */ class SplashScreenActivity$$Lambda$1 implements Runnable {
    private final SplashScreenActivity arg$1;

    private SplashScreenActivity$$Lambda$1(SplashScreenActivity splashScreenActivity) {
        this.arg$1 = splashScreenActivity;
    }

    public static Runnable lambdaFactory$(SplashScreenActivity splashScreenActivity) {
        return new SplashScreenActivity$$Lambda$1(splashScreenActivity);
    }

    public void run() {
        this.arg$1.finish(FinishState.Timeout);
    }
}
