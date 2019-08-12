package com.airbnb.android.core.analytics;

import com.airbnb.android.core.monitor.ExecutorMonitor;

final /* synthetic */ class TimeSkewAnalytics$$Lambda$1 implements Runnable {
    private final TimeSkewAnalytics arg$1;

    private TimeSkewAnalytics$$Lambda$1(TimeSkewAnalytics timeSkewAnalytics) {
        this.arg$1 = timeSkewAnalytics;
    }

    public static Runnable lambdaFactory$(TimeSkewAnalytics timeSkewAnalytics) {
        return new TimeSkewAnalytics$$Lambda$1(timeSkewAnalytics);
    }

    public void run() {
        ExecutorMonitor.watch(this.arg$1.requestExecutor, TimeSkewAnalytics.EXECUTOR_TAG);
    }
}
