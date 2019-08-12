package com.airbnb.android.core.monitor;

import p032rx.functions.Action1;

final /* synthetic */ class ExecutorMonitor$WatchDog$$Lambda$4 implements Action1 {
    private static final ExecutorMonitor$WatchDog$$Lambda$4 instance = new ExecutorMonitor$WatchDog$$Lambda$4();

    private ExecutorMonitor$WatchDog$$Lambda$4() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((Throwable) obj).printStackTrace();
    }
}
