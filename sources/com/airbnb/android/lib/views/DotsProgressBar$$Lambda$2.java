package com.airbnb.android.lib.views;

final /* synthetic */ class DotsProgressBar$$Lambda$2 implements Runnable {
    private final DotsProgressBar arg$1;

    private DotsProgressBar$$Lambda$2(DotsProgressBar dotsProgressBar) {
        this.arg$1 = dotsProgressBar;
    }

    public static Runnable lambdaFactory$(DotsProgressBar dotsProgressBar) {
        return new DotsProgressBar$$Lambda$2(dotsProgressBar);
    }

    public void run() {
        this.arg$1.addDots();
    }
}
