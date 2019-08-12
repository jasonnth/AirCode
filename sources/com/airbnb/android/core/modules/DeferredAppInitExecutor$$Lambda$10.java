package com.airbnb.android.core.modules;

import com.airbnb.android.core.PostApplicationCreatedInitializer;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$10 implements Runnable {
    private final DeferredAppInitExecutor arg$1;
    private final PostApplicationCreatedInitializer arg$2;

    private DeferredAppInitExecutor$$Lambda$10(DeferredAppInitExecutor deferredAppInitExecutor, PostApplicationCreatedInitializer postApplicationCreatedInitializer) {
        this.arg$1 = deferredAppInitExecutor;
        this.arg$2 = postApplicationCreatedInitializer;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor, PostApplicationCreatedInitializer postApplicationCreatedInitializer) {
        return new DeferredAppInitExecutor$$Lambda$10(deferredAppInitExecutor, postApplicationCreatedInitializer);
    }

    public void run() {
        DeferredAppInitExecutor.lambda$null$5(this.arg$1, this.arg$2);
    }
}
