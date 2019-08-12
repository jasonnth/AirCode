package com.airbnb.android.core.map;

import com.airbnb.android.airmapview.listeners.OnMapBoundsCallback;
import com.google.android.gms.maps.model.LatLngBounds;

final /* synthetic */ class ExploreMapView$$Lambda$8 implements OnMapBoundsCallback {
    private final ExploreMapView arg$1;

    private ExploreMapView$$Lambda$8(ExploreMapView exploreMapView) {
        this.arg$1 = exploreMapView;
    }

    public static OnMapBoundsCallback lambdaFactory$(ExploreMapView exploreMapView) {
        return new ExploreMapView$$Lambda$8(exploreMapView);
    }

    public void onMapBoundsReady(LatLngBounds latLngBounds) {
        this.arg$1.eventCallbacks.onRedoSearchClicked(latLngBounds);
    }
}
