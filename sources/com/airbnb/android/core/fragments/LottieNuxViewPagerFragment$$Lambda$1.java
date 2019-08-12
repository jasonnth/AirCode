package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LottieNuxViewPagerFragment$$Lambda$1 implements OnClickListener {
    private final LottieNuxViewPagerFragment arg$1;

    private LottieNuxViewPagerFragment$$Lambda$1(LottieNuxViewPagerFragment lottieNuxViewPagerFragment) {
        this.arg$1 = lottieNuxViewPagerFragment;
    }

    public static OnClickListener lambdaFactory$(LottieNuxViewPagerFragment lottieNuxViewPagerFragment) {
        return new LottieNuxViewPagerFragment$$Lambda$1(lottieNuxViewPagerFragment);
    }

    public void onClick(View view) {
        LottieNuxViewPagerFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
