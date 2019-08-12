package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSDiscountsFragment$$Lambda$3 implements Action1 {
    private final LYSDiscountsFragment arg$1;

    private LYSDiscountsFragment$$Lambda$3(LYSDiscountsFragment lYSDiscountsFragment) {
        this.arg$1 = lYSDiscountsFragment;
    }

    public static Action1 lambdaFactory$(LYSDiscountsFragment lYSDiscountsFragment) {
        return new LYSDiscountsFragment$$Lambda$3(lYSDiscountsFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
