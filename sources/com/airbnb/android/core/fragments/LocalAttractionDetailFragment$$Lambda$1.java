package com.airbnb.android.core.fragments;

final /* synthetic */ class LocalAttractionDetailFragment$$Lambda$1 implements Runnable {
    private final LocalAttractionDetailFragment arg$1;

    private LocalAttractionDetailFragment$$Lambda$1(LocalAttractionDetailFragment localAttractionDetailFragment) {
        this.arg$1 = localAttractionDetailFragment;
    }

    public static Runnable lambdaFactory$(LocalAttractionDetailFragment localAttractionDetailFragment) {
        return new LocalAttractionDetailFragment$$Lambda$1(localAttractionDetailFragment);
    }

    public void run() {
        this.arg$1.loadHeroImage();
    }
}
