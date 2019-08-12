package com.airbnb.android.core.modules;

import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import dagger.Lazy;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$3 implements Runnable {
    private final Lazy arg$1;
    private final long arg$2;

    private DeferredAppInitExecutor$$Lambda$3(Lazy lazy, long j) {
        this.arg$1 = lazy;
        this.arg$2 = j;
    }

    public static Runnable lambdaFactory$(Lazy lazy, long j) {
        return new DeferredAppInitExecutor$$Lambda$3(lazy, j);
    }

    public void run() {
        ((AppLaunchAnalytics) this.arg$1.get()).trackColdLaunchStart(this.arg$2);
    }
}
