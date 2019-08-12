package com.airbnb.android.explore.fragments;

final /* synthetic */ class MTExploreParentFragment$$Lambda$4 implements Runnable {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$4(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static Runnable lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$4(mTExploreParentFragment);
    }

    public void run() {
        MTExploreParentFragment.lambda$onResume$4(this.arg$1);
    }
}
