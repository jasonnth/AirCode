package com.airbnb.android.core.map;

final /* synthetic */ class ExploreMapView$$Lambda$10 implements Runnable {
    private final ExploreMapView arg$1;
    private final boolean arg$2;

    private ExploreMapView$$Lambda$10(ExploreMapView exploreMapView, boolean z) {
        this.arg$1 = exploreMapView;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(ExploreMapView exploreMapView, boolean z) {
        return new ExploreMapView$$Lambda$10(exploreMapView, z);
    }

    public void run() {
        ExploreMapView.lambda$invalidateMapPadding$6(this.arg$1, this.arg$2);
    }
}
