package com.airbnb.android.core.modules;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$2 implements Runnable {
    private final DeferredAppInitExecutor arg$1;

    private DeferredAppInitExecutor$$Lambda$2(DeferredAppInitExecutor deferredAppInitExecutor) {
        this.arg$1 = deferredAppInitExecutor;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor) {
        return new DeferredAppInitExecutor$$Lambda$2(deferredAppInitExecutor);
    }

    public void run() {
        DeferredAppInitExecutor.lambda$preloadResources$1(this.arg$1);
    }
}
