package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MTExploreParentFragment$$Lambda$11 implements OnClickListener {
    private final MTExploreParentFragment arg$1;
    private final String arg$2;

    private MTExploreParentFragment$$Lambda$11(MTExploreParentFragment mTExploreParentFragment, String str) {
        this.arg$1 = mTExploreParentFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(MTExploreParentFragment mTExploreParentFragment, String str) {
        return new MTExploreParentFragment$$Lambda$11(mTExploreParentFragment, str);
    }

    public void onClick(View view) {
        this.arg$1.dataController.retryPaginationRequest(this.arg$2);
    }
}
