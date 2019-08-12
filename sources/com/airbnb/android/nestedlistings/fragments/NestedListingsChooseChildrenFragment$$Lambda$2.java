package com.airbnb.android.nestedlistings.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class NestedListingsChooseChildrenFragment$$Lambda$2 implements Action1 {
    private final NestedListingsChooseChildrenFragment arg$1;

    private NestedListingsChooseChildrenFragment$$Lambda$2(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        this.arg$1 = nestedListingsChooseChildrenFragment;
    }

    public static Action1 lambdaFactory$(NestedListingsChooseChildrenFragment nestedListingsChooseChildrenFragment) {
        return new NestedListingsChooseChildrenFragment$$Lambda$2(nestedListingsChooseChildrenFragment);
    }

    public void call(Object obj) {
        NestedListingsChooseChildrenFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
