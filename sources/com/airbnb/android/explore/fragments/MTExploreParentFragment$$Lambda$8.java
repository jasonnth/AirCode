package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$8 implements OnClickListener {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$8(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static OnClickListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$8(mTExploreParentFragment);
    }

    public void onClick(View view) {
        MTExploreParentFragment.lambda$setupSearchBarText$8(this.arg$1, view);
    }
}
