package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSAmenitiesFragment$$Lambda$1 implements Action1 {
    private final LYSAmenitiesFragment arg$1;

    private LYSAmenitiesFragment$$Lambda$1(LYSAmenitiesFragment lYSAmenitiesFragment) {
        this.arg$1 = lYSAmenitiesFragment;
    }

    public static Action1 lambdaFactory$(LYSAmenitiesFragment lYSAmenitiesFragment) {
        return new LYSAmenitiesFragment$$Lambda$1(lYSAmenitiesFragment);
    }

    public void call(Object obj) {
        LYSAmenitiesFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
