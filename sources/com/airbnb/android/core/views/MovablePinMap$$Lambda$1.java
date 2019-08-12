package com.airbnb.android.core.views;

import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;

final /* synthetic */ class MovablePinMap$$Lambda$1 implements OnMapInitializedListener {
    private final MovablePinMap arg$1;

    private MovablePinMap$$Lambda$1(MovablePinMap movablePinMap) {
        this.arg$1 = movablePinMap;
    }

    public static OnMapInitializedListener lambdaFactory$(MovablePinMap movablePinMap) {
        return new MovablePinMap$$Lambda$1(movablePinMap);
    }

    public void onMapInitialized() {
        this.arg$1.onMapInitialized();
    }
}
