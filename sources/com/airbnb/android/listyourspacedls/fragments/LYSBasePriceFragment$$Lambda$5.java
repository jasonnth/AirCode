package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$5 implements Action1 {
    private final LYSBasePriceFragment arg$1;

    private LYSBasePriceFragment$$Lambda$5(LYSBasePriceFragment lYSBasePriceFragment) {
        this.arg$1 = lYSBasePriceFragment;
    }

    public static Action1 lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment) {
        return new LYSBasePriceFragment$$Lambda$5(lYSBasePriceFragment);
    }

    public void call(Object obj) {
        LYSBasePriceFragment.lambda$new$5(this.arg$1, (SimpleListingResponse) obj);
    }
}
