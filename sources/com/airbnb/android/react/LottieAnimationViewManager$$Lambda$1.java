package com.airbnb.android.react;

import com.airbnb.lottie.LottieAnimationView;

final /* synthetic */ class LottieAnimationViewManager$$Lambda$1 implements Runnable {
    private final LottieAnimationView arg$1;

    private LottieAnimationViewManager$$Lambda$1(LottieAnimationView lottieAnimationView) {
        this.arg$1 = lottieAnimationView;
    }

    public static Runnable lambdaFactory$(LottieAnimationView lottieAnimationView) {
        return new LottieAnimationViewManager$$Lambda$1(lottieAnimationView);
    }

    public void run() {
        LottieAnimationViewManager.lambda$receiveCommand$0(this.arg$1);
    }
}
