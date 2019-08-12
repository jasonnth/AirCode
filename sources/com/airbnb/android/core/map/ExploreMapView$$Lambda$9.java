package com.airbnb.android.core.map;

final /* synthetic */ class ExploreMapView$$Lambda$9 implements Runnable {
    private final ExploreMapView arg$1;

    private ExploreMapView$$Lambda$9(ExploreMapView exploreMapView) {
        this.arg$1 = exploreMapView;
    }

    public static Runnable lambdaFactory$(ExploreMapView exploreMapView) {
        return new ExploreMapView$$Lambda$9(exploreMapView);
    }

    public void run() {
        ExploreMapView.lambda$invalidateCarouselVisibility$5(this.arg$1);
    }
}
