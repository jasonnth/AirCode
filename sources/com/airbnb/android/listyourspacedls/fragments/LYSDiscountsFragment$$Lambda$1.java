package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSDiscountsFragment$$Lambda$1 implements Action1 {
    private final LYSDiscountsFragment arg$1;

    private LYSDiscountsFragment$$Lambda$1(LYSDiscountsFragment lYSDiscountsFragment) {
        this.arg$1 = lYSDiscountsFragment;
    }

    public static Action1 lambdaFactory$(LYSDiscountsFragment lYSDiscountsFragment) {
        return new LYSDiscountsFragment$$Lambda$1(lYSDiscountsFragment);
    }

    public void call(Object obj) {
        LYSDiscountsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
