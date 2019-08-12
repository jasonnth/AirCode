package com.airbnb.android.core.views;

import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.google.android.gms.maps.model.LatLng;

final /* synthetic */ class MovablePinMap$$Lambda$3 implements OnCameraChangeListener {
    private final MovablePinMap arg$1;

    private MovablePinMap$$Lambda$3(MovablePinMap movablePinMap) {
        this.arg$1 = movablePinMap;
    }

    public static OnCameraChangeListener lambdaFactory$(MovablePinMap movablePinMap) {
        return new MovablePinMap$$Lambda$3(movablePinMap);
    }

    public void onCameraChanged(LatLng latLng, int i) {
        this.arg$1.onCameraChanged(latLng, i);
    }
}
