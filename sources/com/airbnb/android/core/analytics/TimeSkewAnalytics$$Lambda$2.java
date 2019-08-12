package com.airbnb.android.core.analytics;

final /* synthetic */ class TimeSkewAnalytics$$Lambda$2 implements Runnable {
    private final TimeSkewAnalytics arg$1;

    private TimeSkewAnalytics$$Lambda$2(TimeSkewAnalytics timeSkewAnalytics) {
        this.arg$1 = timeSkewAnalytics;
    }

    public static Runnable lambdaFactory$(TimeSkewAnalytics timeSkewAnalytics) {
        return new TimeSkewAnalytics$$Lambda$2(timeSkewAnalytics);
    }

    public void run() {
        this.arg$1.doRequest();
    }
}
