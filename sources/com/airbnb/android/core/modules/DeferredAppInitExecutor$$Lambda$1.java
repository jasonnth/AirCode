package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.JodaTimeInitializer;

final /* synthetic */ class DeferredAppInitExecutor$$Lambda$1 implements Runnable {
    private final Context arg$1;

    private DeferredAppInitExecutor$$Lambda$1(Context context) {
        this.arg$1 = context;
    }

    public static Runnable lambdaFactory$(Context context) {
        return new DeferredAppInitExecutor$$Lambda$1(context);
    }

    public void run() {
        JodaTimeInitializer.initalize(this.arg$1);
    }
}
