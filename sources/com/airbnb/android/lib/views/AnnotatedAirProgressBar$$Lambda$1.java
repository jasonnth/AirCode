package com.airbnb.android.lib.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class AnnotatedAirProgressBar$$Lambda$1 implements AnimatorUpdateListener {
    private final AnnotatedAirProgressBar arg$1;
    private final int arg$2;
    private final int arg$3;

    private AnnotatedAirProgressBar$$Lambda$1(AnnotatedAirProgressBar annotatedAirProgressBar, int i, int i2) {
        this.arg$1 = annotatedAirProgressBar;
        this.arg$2 = i;
        this.arg$3 = i2;
    }

    public static AnimatorUpdateListener lambdaFactory$(AnnotatedAirProgressBar annotatedAirProgressBar, int i, int i2) {
        return new AnnotatedAirProgressBar$$Lambda$1(annotatedAirProgressBar, i, i2);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        AnnotatedAirProgressBar.lambda$setProgressLevels$0(this.arg$1, this.arg$2, this.arg$3, valueAnimator);
    }
}
