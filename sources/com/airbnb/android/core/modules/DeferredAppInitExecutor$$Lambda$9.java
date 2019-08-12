package com.airbnb.android.core.modules;

import com.airbnb.android.core.PostInteractiveInitializer;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$9 implements Runnable {
    private final DeferredAppInitExecutor arg$1;
    private final PostInteractiveInitializer arg$2;

    private DeferredAppInitExecutor$$Lambda$9(DeferredAppInitExecutor deferredAppInitExecutor, PostInteractiveInitializer postInteractiveInitializer) {
        this.arg$1 = deferredAppInitExecutor;
        this.arg$2 = postInteractiveInitializer;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor, PostInteractiveInitializer postInteractiveInitializer) {
        return new DeferredAppInitExecutor$$Lambda$9(deferredAppInitExecutor, postInteractiveInitializer);
    }

    public void run() {
        DeferredAppInitExecutor.lambda$null$7(this.arg$1, this.arg$2);
    }
}
