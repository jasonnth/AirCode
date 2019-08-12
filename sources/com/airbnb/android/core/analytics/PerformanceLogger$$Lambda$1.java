package com.airbnb.android.core.analytics;

import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;

final /* synthetic */ class PerformanceLogger$$Lambda$1 implements Runnable {
    private final PerformanceLogger arg$1;
    private final String arg$2;
    private final Long arg$3;
    private final String arg$4;
    private final Strap arg$5;
    private final C2445NativeMeasurementType arg$6;
    private final String arg$7;

    private PerformanceLogger$$Lambda$1(PerformanceLogger performanceLogger, String str, Long l, String str2, Strap strap, C2445NativeMeasurementType nativeMeasurementType, String str3) {
        this.arg$1 = performanceLogger;
        this.arg$2 = str;
        this.arg$3 = l;
        this.arg$4 = str2;
        this.arg$5 = strap;
        this.arg$6 = nativeMeasurementType;
        this.arg$7 = str3;
    }

    public static Runnable lambdaFactory$(PerformanceLogger performanceLogger, String str, Long l, String str2, Strap strap, C2445NativeMeasurementType nativeMeasurementType, String str3) {
        return new PerformanceLogger$$Lambda$1(performanceLogger, str, l, str2, strap, nativeMeasurementType, str3);
    }

    public void run() {
        this.arg$1.logPerformanceImpl(this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7);
    }
}
