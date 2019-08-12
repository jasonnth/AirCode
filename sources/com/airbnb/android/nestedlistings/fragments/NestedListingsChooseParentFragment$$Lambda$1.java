package com.airbnb.android.nestedlistings.fragments;

import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseParentAdapter.NestedListingsChooseParentListener;

final /* synthetic */ class NestedListingsChooseParentFragment$$Lambda$1 implements NestedListingsChooseParentListener {
    private final NestedListingsChooseParentFragment arg$1;

    private NestedListingsChooseParentFragment$$Lambda$1(NestedListingsChooseParentFragment nestedListingsChooseParentFragment) {
        this.arg$1 = nestedListingsChooseParentFragment;
    }

    public static NestedListingsChooseParentListener lambdaFactory$(NestedListingsChooseParentFragment nestedListingsChooseParentFragment) {
        return new NestedListingsChooseParentFragment$$Lambda$1(nestedListingsChooseParentFragment);
    }

    public void onChooseParent(NestedListing nestedListing) {
        this.arg$1.controller.getActionExecutor().nestedListingsChildren(nestedListing, false);
    }
}
