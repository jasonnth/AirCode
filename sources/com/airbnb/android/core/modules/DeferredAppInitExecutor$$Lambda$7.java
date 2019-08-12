package com.airbnb.android.core.modules;

import java.util.concurrent.TimeUnit;
import p032rx.Observable;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$7 implements Runnable {
    private final DeferredAppInitExecutor arg$1;

    private DeferredAppInitExecutor$$Lambda$7(DeferredAppInitExecutor deferredAppInitExecutor) {
        this.arg$1 = deferredAppInitExecutor;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor) {
        return new DeferredAppInitExecutor$$Lambda$7(deferredAppInitExecutor);
    }

    public void run() {
        Observable.empty().delay(1, TimeUnit.SECONDS).doOnCompleted(DeferredAppInitExecutor$$Lambda$8.lambdaFactory$(this.arg$1)).subscribe();
    }
}
