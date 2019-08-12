package com.airbnb.android.explore.fragments;

final /* synthetic */ class MTExploreParentFragment$$Lambda$12 implements Runnable {
    private final MTExploreParentFragment arg$1;
    private final String arg$2;

    private MTExploreParentFragment$$Lambda$12(MTExploreParentFragment mTExploreParentFragment, String str) {
        this.arg$1 = mTExploreParentFragment;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(MTExploreParentFragment mTExploreParentFragment, String str) {
        return new MTExploreParentFragment$$Lambda$12(mTExploreParentFragment, str);
    }

    public void run() {
        this.arg$1.logExperiments(this.arg$2);
    }
}
