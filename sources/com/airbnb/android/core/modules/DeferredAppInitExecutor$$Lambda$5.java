package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AppForegroundDetector;
import dagger.Lazy;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$5 implements Runnable {
    private final DeferredAppInitExecutor arg$1;
    private final AppForegroundDetector arg$2;
    private final Lazy arg$3;
    private final Lazy arg$4;
    private final Context arg$5;

    private DeferredAppInitExecutor$$Lambda$5(DeferredAppInitExecutor deferredAppInitExecutor, AppForegroundDetector appForegroundDetector, Lazy lazy, Lazy lazy2, Context context) {
        this.arg$1 = deferredAppInitExecutor;
        this.arg$2 = appForegroundDetector;
        this.arg$3 = lazy;
        this.arg$4 = lazy2;
        this.arg$5 = context;
    }

    public static Runnable lambdaFactory$(DeferredAppInitExecutor deferredAppInitExecutor, AppForegroundDetector appForegroundDetector, Lazy lazy, Lazy lazy2, Context context) {
        return new DeferredAppInitExecutor$$Lambda$5(deferredAppInitExecutor, appForegroundDetector, lazy, lazy2, context);
    }

    public void run() {
        DeferredAppInitExecutor.lambda$registerReceiversAndListeners$4(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5);
    }
}
