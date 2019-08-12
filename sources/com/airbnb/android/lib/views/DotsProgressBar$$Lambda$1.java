package com.airbnb.android.lib.views;

import com.airbnb.android.utils.AnimationUtils;

final /* synthetic */ class DotsProgressBar$$Lambda$1 implements Runnable {
    private final DotsProgressBar arg$1;

    private DotsProgressBar$$Lambda$1(DotsProgressBar dotsProgressBar) {
        this.arg$1 = dotsProgressBar;
    }

    public static Runnable lambdaFactory$(DotsProgressBar dotsProgressBar) {
        return new DotsProgressBar$$Lambda$1(dotsProgressBar);
    }

    public void run() {
        AnimationUtils.fadeIn(this.arg$1);
    }
}
