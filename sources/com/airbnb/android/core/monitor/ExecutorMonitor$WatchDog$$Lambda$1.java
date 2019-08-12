package com.airbnb.android.core.monitor;

import p032rx.functions.Func1;

final /* synthetic */ class ExecutorMonitor$WatchDog$$Lambda$1 implements Func1 {
    private final WatchDog arg$1;

    private ExecutorMonitor$WatchDog$$Lambda$1(WatchDog watchDog) {
        this.arg$1 = watchDog;
    }

    public static Func1 lambdaFactory$(WatchDog watchDog) {
        return new ExecutorMonitor$WatchDog$$Lambda$1(watchDog);
    }

    public Object call(Object obj) {
        return Boolean.valueOf(this.arg$1.executed.compareAndSet(true, false));
    }
}
