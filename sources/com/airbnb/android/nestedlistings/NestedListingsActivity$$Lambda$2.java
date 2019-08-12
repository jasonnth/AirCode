package com.airbnb.android.nestedlistings;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class NestedListingsActivity$$Lambda$2 implements Action1 {
    private final NestedListingsActivity arg$1;

    private NestedListingsActivity$$Lambda$2(NestedListingsActivity nestedListingsActivity) {
        this.arg$1 = nestedListingsActivity;
    }

    public static Action1 lambdaFactory$(NestedListingsActivity nestedListingsActivity) {
        return new NestedListingsActivity$$Lambda$2(nestedListingsActivity);
    }

    public void call(Object obj) {
        NetworkUtil.getGenericNetworkError(this.arg$1);
    }
}
