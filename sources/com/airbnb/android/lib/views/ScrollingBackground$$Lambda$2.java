package com.airbnb.android.lib.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class ScrollingBackground$$Lambda$2 implements AnimatorUpdateListener {
    private final ScrollingBackground arg$1;

    private ScrollingBackground$$Lambda$2(ScrollingBackground scrollingBackground) {
        this.arg$1 = scrollingBackground;
    }

    public static AnimatorUpdateListener lambdaFactory$(ScrollingBackground scrollingBackground) {
        return new ScrollingBackground$$Lambda$2(scrollingBackground);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.mScrollSpeedPixelsPerMilli = ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }
}
