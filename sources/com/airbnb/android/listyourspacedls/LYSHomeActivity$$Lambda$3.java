package com.airbnb.android.listyourspacedls;

import p032rx.functions.Action1;

final /* synthetic */ class LYSHomeActivity$$Lambda$3 implements Action1 {
    private final LYSHomeActivity arg$1;

    private LYSHomeActivity$$Lambda$3(LYSHomeActivity lYSHomeActivity) {
        this.arg$1 = lYSHomeActivity;
    }

    public static Action1 lambdaFactory$(LYSHomeActivity lYSHomeActivity) {
        return new LYSHomeActivity$$Lambda$3(lYSHomeActivity);
    }

    public void call(Object obj) {
        this.arg$1.actionExecutor.showLoadingOverlay(false);
    }
}
