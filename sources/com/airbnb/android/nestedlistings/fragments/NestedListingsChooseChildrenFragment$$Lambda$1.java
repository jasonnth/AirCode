package com.airbnb.android.nestedlistings.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$1 implements Action1 {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$1(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static Action1 lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$1(nestedListingsChooseChildrenFragment);
    }

    public void call(Object obj) {
        this.arg$1.makeNestedListingRefreshRequest();
    }
}
