package com.airbnb.android.core.views;

import com.airbnb.android.airmapview.listeners.OnCameraMoveListener;

final /* synthetic */ class MovablePinMap$$Lambda$2 implements OnCameraMoveListener {
    private final MovablePinMap arg$1;

    private MovablePinMap$$Lambda$2(MovablePinMap movablePinMap) {
        this.arg$1 = movablePinMap;
    }

    public static OnCameraMoveListener lambdaFactory$(MovablePinMap movablePinMap) {
        return new MovablePinMap$$Lambda$2(movablePinMap);
    }

    public void onCameraMove() {
        this.arg$1.onCameraMove();
    }
}
