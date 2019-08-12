package com.airbnb.android.nestedlistings;

import com.airbnb.android.core.responses.NestedListingsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class NestedListingsActivity$$Lambda$1 implements Action1 {
    private final NestedListingsActivity arg$1;

    private NestedListingsActivity$$Lambda$1(NestedListingsActivity nestedListingsActivity) {
        this.arg$1 = nestedListingsActivity;
    }

    public static Action1 lambdaFactory$(NestedListingsActivity nestedListingsActivity) {
        return new NestedListingsActivity$$Lambda$1(nestedListingsActivity);
    }

    public void call(Object obj) {
        NestedListingsActivity.lambda$new$0(this.arg$1, (NestedListingsResponse) obj);
    }
}
