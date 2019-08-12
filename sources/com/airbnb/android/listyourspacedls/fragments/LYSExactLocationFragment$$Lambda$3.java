package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSExactLocationFragment$$Lambda$3 implements Action1 {
    private final LYSExactLocationFragment arg$1;

    private LYSExactLocationFragment$$Lambda$3(LYSExactLocationFragment lYSExactLocationFragment) {
        this.arg$1 = lYSExactLocationFragment;
    }

    public static Action1 lambdaFactory$(LYSExactLocationFragment lYSExactLocationFragment) {
        return new LYSExactLocationFragment$$Lambda$3(lYSExactLocationFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), null);
    }
}
