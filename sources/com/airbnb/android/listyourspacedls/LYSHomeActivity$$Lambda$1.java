package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHomeActivity$$Lambda$1 implements Action1 {
    private final LYSHomeActivity arg$1;

    private LYSHomeActivity$$Lambda$1(LYSHomeActivity lYSHomeActivity) {
        this.arg$1 = lYSHomeActivity;
    }

    public static Action1 lambdaFactory$(LYSHomeActivity lYSHomeActivity) {
        return new LYSHomeActivity$$Lambda$1(lYSHomeActivity);
    }

    public void call(Object obj) {
        LYSHomeActivity.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
