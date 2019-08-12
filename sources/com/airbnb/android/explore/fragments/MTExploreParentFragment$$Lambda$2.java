package com.airbnb.android.explore.fragments;

import android.view.View;
import com.airbnb.android.core.views.SlidingTabLayout.OnTabClickedListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$2 implements OnTabClickedListener {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$2(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static OnTabClickedListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$2(mTExploreParentFragment);
    }

    public void onTabClicked(View view, int i) {
        MTExploreParentFragment.lambda$onCreateView$1(this.arg$1, view, i);
    }
}
