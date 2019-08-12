package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreMapFragment$$Lambda$1 implements OnClickListener {
    private final MTExploreMapFragment arg$1;

    private MTExploreMapFragment$$Lambda$1(MTExploreMapFragment mTExploreMapFragment) {
        this.arg$1 = mTExploreMapFragment;
    }

    public static OnClickListener lambdaFactory$(MTExploreMapFragment mTExploreMapFragment) {
        return new MTExploreMapFragment$$Lambda$1(mTExploreMapFragment);
    }

    public void onClick(View view) {
        MTExploreMapFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
