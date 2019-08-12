package com.airbnb.android.explore.fragments;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$1 implements OnGlobalLayoutListener {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$1(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static OnGlobalLayoutListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$1(mTExploreParentFragment);
    }

    public void onGlobalLayout() {
        this.arg$1.scrollController.adjustForExploreMarquee();
    }
}
