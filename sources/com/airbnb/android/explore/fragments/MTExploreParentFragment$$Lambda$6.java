package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$6 implements OnClickListener {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$6(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static OnClickListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$6(mTExploreParentFragment);
    }

    public void onClick(View view) {
        MTExploreParentFragment.lambda$setupSearchBar$6(this.arg$1, view);
    }
}