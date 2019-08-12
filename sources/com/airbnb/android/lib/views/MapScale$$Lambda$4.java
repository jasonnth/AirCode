package com.airbnb.android.lib.views;

import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.airmapview.listeners.OnMapBoundsCallback;
import com.google.android.gms.maps.model.LatLngBounds;

final /* synthetic */ class MapScale$$Lambda$4 implements OnMapBoundsCallback {
    private final MapScale arg$1;
    private final AirMapView arg$2;

    private MapScale$$Lambda$4(MapScale mapScale, AirMapView airMapView) {
        this.arg$1 = mapScale;
        this.arg$2 = airMapView;
    }

    public static OnMapBoundsCallback lambdaFactory$(MapScale mapScale, AirMapView airMapView) {
        return new MapScale$$Lambda$4(mapScale, airMapView);
    }

    public void onMapBoundsReady(LatLngBounds latLngBounds) {
        MapScale.lambda$updateScale$1(this.arg$1, this.arg$2, latLngBounds);
    }
}
