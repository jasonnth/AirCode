package com.airbnb.android.nestedlistings.fragments;

import com.airbnb.android.core.responses.NestedListingsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$3 implements Action1 {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$3(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static Action1 lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$3(nestedListingsChooseChildrenFragment);
    }

    public void call(Object obj) {
        NestedListingsChooseChildrenFragment.lambda$new$2(this.arg$1, (NestedListingsResponse) obj);
    }
}
