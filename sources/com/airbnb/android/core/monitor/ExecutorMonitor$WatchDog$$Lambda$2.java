package com.airbnb.android.core.monitor;

import p032rx.functions.Action0;

final /* synthetic */ class ExecutorMonitor$WatchDog$$Lambda$2 implements Action0 {
    private final WatchDog arg$1;

    private ExecutorMonitor$WatchDog$$Lambda$2(WatchDog watchDog) {
        this.arg$1 = watchDog;
    }

    public static Action0 lambdaFactory$(WatchDog watchDog) {
        return new ExecutorMonitor$WatchDog$$Lambda$2(watchDog);
    }

    public void call() {
        this.arg$1.report();
    }
}
