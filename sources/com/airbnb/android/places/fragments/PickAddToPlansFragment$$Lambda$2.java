package com.airbnb.android.places.fragments;

final /* synthetic */ class PickAddToPlansFragment$$Lambda$2 implements Runnable {
    private final PickAddToPlansFragment arg$1;

    private PickAddToPlansFragment$$Lambda$2(PickAddToPlansFragment pickAddToPlansFragment) {
        this.arg$1 = pickAddToPlansFragment;
    }

    public static Runnable lambdaFactory$(PickAddToPlansFragment pickAddToPlansFragment) {
        return new PickAddToPlansFragment$$Lambda$2(pickAddToPlansFragment);
    }

    public void run() {
        PickAddToPlansFragment.lambda$slideOut$1(this.arg$1);
    }
}
