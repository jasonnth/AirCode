package com.airbnb.android.nestedlistings.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$7 implements OnBackListener {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$7(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static OnBackListener lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$7(nestedListingsChooseChildrenFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
