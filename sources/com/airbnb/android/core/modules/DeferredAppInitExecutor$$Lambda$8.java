package com.airbnb.android.core.modules;

import p032rx.functions.Action0;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$8 implements Action0 {
    private final DeferredAppInitExecutor arg$1;

    private DeferredAppInitExecutor$$Lambda$8(DeferredAppInitExecutor deferredAppInitExecutor) {
        this.arg$1 = deferredAppInitExecutor;
    }

    public static Action0 lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor) {
        return new DeferredAppInitExecutor$$Lambda$8(deferredAppInitExecutor);
    }

    public void call() {
        DeferredAppInitExecutor.lambda$null$8(this.arg$1);
    }
}
