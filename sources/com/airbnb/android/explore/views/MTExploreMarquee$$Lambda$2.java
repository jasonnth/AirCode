package com.airbnb.android.explore.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class MTExploreMarquee$$Lambda$2 implements AnimatorUpdateListener {
    private final MTExploreMarquee arg$1;

    private MTExploreMarquee$$Lambda$2(MTExploreMarquee mTExploreMarquee) {
        this.arg$1 = mTExploreMarquee;
    }

    public static AnimatorUpdateListener lambdaFactory$(MTExploreMarquee mTExploreMarquee) {
        return new MTExploreMarquee$$Lambda$2(mTExploreMarquee);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.onBabuModeUpdate(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
