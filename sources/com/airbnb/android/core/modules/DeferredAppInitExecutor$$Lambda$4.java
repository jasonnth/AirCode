package com.airbnb.android.core.modules;

import com.airbnb.android.core.monitor.ExecutorMonitor;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$4 implements Runnable {
    private static final DeferredAppInitExecutor$$Lambda$4 instance = new DeferredAppInitExecutor$$Lambda$4();

    private DeferredAppInitExecutor$$Lambda$4() {
    }

    public static Runnable lambdaFactory$() {
        return instance;
    }

    public void run() {
        ExecutorMonitor.watch(DeferredAppInitExecutor.THREAD_POOL_EXECUTOR, DeferredAppInitExecutor.ASYNCTASK_THREAD_POOL_EXECUTOR_TAG);
    }
}
