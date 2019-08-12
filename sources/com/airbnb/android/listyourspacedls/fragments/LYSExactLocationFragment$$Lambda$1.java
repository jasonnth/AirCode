package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSExactLocationFragment$$Lambda$1 implements Action1 {
    private final LYSExactLocationFragment arg$1;

    private LYSExactLocationFragment$$Lambda$1(LYSExactLocationFragment lYSExactLocationFragment) {
        this.arg$1 = lYSExactLocationFragment;
    }

    public static Action1 lambdaFactory$(LYSExactLocationFragment lYSExactLocationFragment) {
        return new LYSExactLocationFragment$$Lambda$1(lYSExactLocationFragment);
    }

    public void call(Object obj) {
        LYSExactLocationFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
