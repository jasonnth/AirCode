package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$3 implements OnClickListener {
    private final MTExploreParentFragment arg$1;

    private MTExploreParentFragment$$Lambda$3(MTExploreParentFragment mTExploreParentFragment) {
        this.arg$1 = mTExploreParentFragment;
    }

    public static OnClickListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment) {
        return new MTExploreParentFragment$$Lambda$3(mTExploreParentFragment);
    }

    public void onClick(View view) {
        this.arg$1.dataController.reset();
    }
}