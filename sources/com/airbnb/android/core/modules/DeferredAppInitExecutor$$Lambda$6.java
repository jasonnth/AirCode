package com.airbnb.android.core.modules;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$6 implements Runnable {
    private final DeferredAppInitExecutor arg$1;

    private DeferredAppInitExecutor$$Lambda$6(DeferredAppInitExecutor deferredAppInitExecutor) {
        this.arg$1 = deferredAppInitExecutor;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor) {
        return new DeferredAppInitExecutor$$Lambda$6(deferredAppInitExecutor);
    }

    public void run() {
        DeferredAppInitExecutor.lambda$schedulePostApplicationCreatedInitialization$6(this.arg$1);
    }
}
