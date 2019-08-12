package com.airbnb.android.lib.views;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class MarketingHeroMarquee$$Lambda$1 implements AnimatorUpdateListener {
    private final MarketingHeroMarquee arg$1;

    private MarketingHeroMarquee$$Lambda$1(MarketingHeroMarquee marketingHeroMarquee) {
        this.arg$1 = marketingHeroMarquee;
    }

    public static AnimatorUpdateListener lambdaFactory$(MarketingHeroMarquee marketingHeroMarquee) {
        return new MarketingHeroMarquee$$Lambda$1(marketingHeroMarquee);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        MarketingHeroMarquee.lambda$startAnimation$0(this.arg$1, valueAnimator);
    }
}
