package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreFragment$$Lambda$3 implements OnClickListener {
    private final MTExploreFragment arg$1;

    private MTExploreFragment$$Lambda$3(MTExploreFragment mTExploreFragment) {
        this.arg$1 = mTExploreFragment;
    }

    public static OnClickListener lambdaFactory$(MTExploreFragment mTExploreFragment) {
        return new MTExploreFragment$$Lambda$3(mTExploreFragment);
    }

    public void onClick(View view) {
        MTExploreFragment.lambda$onCreateView$2(this.arg$1, view);
    }
}
