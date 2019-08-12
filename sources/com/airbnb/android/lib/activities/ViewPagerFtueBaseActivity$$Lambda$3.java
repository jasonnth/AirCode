package com.airbnb.android.lib.activities;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final /* synthetic */ class ViewPagerFtueBaseActivity$$Lambda$3 implements AnimatorUpdateListener {
    private final ViewPagerFtueBaseActivity arg$1;

    private ViewPagerFtueBaseActivity$$Lambda$3(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity) {
        this.arg$1 = viewPagerFtueBaseActivity;
    }

    public static AnimatorUpdateListener lambdaFactory$(ViewPagerFtueBaseActivity viewPagerFtueBaseActivity) {
        return new ViewPagerFtueBaseActivity$$Lambda$3(viewPagerFtueBaseActivity);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        ViewPagerFtueBaseActivity.lambda$initStickyAnimation$2(this.arg$1, valueAnimator);
    }
}
