package com.airbnb.android.react;

import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import p032rx.functions.Action0;

final /* synthetic */ class ReactNativeUtils$$Lambda$3 implements Action0 {
    private final PerformanceLogger arg$1;

    private ReactNativeUtils$$Lambda$3(PerformanceLogger performanceLogger) {
        this.arg$1 = performanceLogger;
    }

    public static Action0 lambdaFactory$(PerformanceLogger performanceLogger) {
        return new ReactNativeUtils$$Lambda$3(performanceLogger);
    }

    public void call() {
        this.arg$1.markCompleted(ReactNativeUtils.INITIALIZE_REACT_NATIVE, C2445NativeMeasurementType.ActionDuration, null);
    }
}
