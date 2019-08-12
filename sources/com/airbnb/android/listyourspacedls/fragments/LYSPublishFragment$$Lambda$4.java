package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSPublishFragment$$Lambda$4 implements Action1 {
    private final LYSPublishFragment arg$1;

    private LYSPublishFragment$$Lambda$4(LYSPublishFragment lYSPublishFragment) {
        this.arg$1 = lYSPublishFragment;
    }

    public static Action1 lambdaFactory$(LYSPublishFragment lYSPublishFragment) {
        return new LYSPublishFragment$$Lambda$4(lYSPublishFragment);
    }

    public void call(Object obj) {
        LYSPublishFragment.lambda$new$3(this.arg$1, (Boolean) obj);
    }
}
