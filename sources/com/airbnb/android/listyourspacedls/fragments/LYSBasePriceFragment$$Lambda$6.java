package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSBasePriceFragment$$Lambda$6 implements Action1 {
    private final LYSBasePriceFragment arg$1;

    private LYSBasePriceFragment$$Lambda$6(LYSBasePriceFragment lYSBasePriceFragment) {
        this.arg$1 = lYSBasePriceFragment;
    }

    public static Action1 lambdaFactory$(LYSBasePriceFragment lYSBasePriceFragment) {
        return new LYSBasePriceFragment$$Lambda$6(lYSBasePriceFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
