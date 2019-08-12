package com.airbnb.android.lib.views;

final /* synthetic */ class AnnotatedAirProgressBar$$Lambda$2 implements Runnable {
    private final AnnotatedAirProgressBar arg$1;

    private AnnotatedAirProgressBar$$Lambda$2(AnnotatedAirProgressBar annotatedAirProgressBar) {
        this.arg$1 = annotatedAirProgressBar;
    }

    public static Runnable lambdaFactory$(AnnotatedAirProgressBar annotatedAirProgressBar) {
        return new AnnotatedAirProgressBar$$Lambda$2(annotatedAirProgressBar);
    }

    public void run() {
        AnnotatedAirProgressBar.lambda$setProgressLevels$1(this.arg$1);
    }
}
