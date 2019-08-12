package com.airbnb.android.nestedlistings.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$6 implements OnClickListener {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$6(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static OnClickListener lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$6(nestedListingsChooseChildrenFragment);
    }

    public void onClick(View view) {
        NestedListingsChooseChildrenFragment.lambda$onCreateView$5(this.arg$1, view);
    }
}
