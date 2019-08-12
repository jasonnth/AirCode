package com.airbnb.p027n2.primitives;

/* renamed from: com.airbnb.n2.primitives.StaticMapView$$Lambda$1 */
final /* synthetic */ class StaticMapView$$Lambda$1 implements Runnable {
    private final StaticMapView arg$1;

    private StaticMapView$$Lambda$1(StaticMapView staticMapView) {
        this.arg$1 = staticMapView;
    }

    public static Runnable lambdaFactory$(StaticMapView staticMapView) {
        return new StaticMapView$$Lambda$1(staticMapView);
    }

    public void run() {
        this.arg$1.fetchImageIfNeeded();
    }
}
