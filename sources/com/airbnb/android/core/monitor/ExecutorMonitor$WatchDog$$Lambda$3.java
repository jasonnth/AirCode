package com.airbnb.android.core.monitor;

import p032rx.functions.Action1;

final /* synthetic */ class ExecutorMonitor$WatchDog$$Lambda$3 implements Action1 {
    private final WatchDog arg$1;

    private ExecutorMonitor$WatchDog$$Lambda$3(WatchDog watchDog) {
        this.arg$1 = watchDog;
    }

    public static Action1 lambdaFactory$(WatchDog watchDog) {
        return new ExecutorMonitor$WatchDog$$Lambda$3(watchDog);
    }

    public void call(Object obj) {
        this.arg$1.executed.compareAndSet(false, true);
    }
}
