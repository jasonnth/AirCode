package com.airbnb.android.core.models;

import com.airbnb.android.core.models.HostStandardMetrics.MetricState;
import com.google.common.base.Predicate;

final /* synthetic */ class HostStandardMetrics$MetricState$$Lambda$1 implements Predicate {
    private final String arg$1;

    private HostStandardMetrics$MetricState$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new HostStandardMetrics$MetricState$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((MetricState) obj).key.equals(this.arg$1);
    }
}
