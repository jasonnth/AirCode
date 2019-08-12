package com.airbnb.android.listyourspacedls.fragments;

final /* synthetic */ class LYSLandingFragment$$Lambda$2 implements Runnable {
    private final LYSLandingFragment arg$1;

    private LYSLandingFragment$$Lambda$2(LYSLandingFragment lYSLandingFragment) {
        this.arg$1 = lYSLandingFragment;
    }

    public static Runnable lambdaFactory$(LYSLandingFragment lYSLandingFragment) {
        return new LYSLandingFragment$$Lambda$2(lYSLandingFragment);
    }

    public void run() {
        LYSLandingFragment.lambda$setUpCollectionStatus$1(this.arg$1);
    }
}
