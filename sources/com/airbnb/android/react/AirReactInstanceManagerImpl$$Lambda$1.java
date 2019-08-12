package com.airbnb.android.react;

import com.facebook.react.bridge.ReactMarker.MarkerListener;

final /* synthetic */ class AirReactInstanceManagerImpl$$Lambda$1 implements MarkerListener {
    private final AirReactInstanceManagerImpl arg$1;

    private AirReactInstanceManagerImpl$$Lambda$1(AirReactInstanceManagerImpl airReactInstanceManagerImpl) {
        this.arg$1 = airReactInstanceManagerImpl;
    }

    public static MarkerListener lambdaFactory$(AirReactInstanceManagerImpl airReactInstanceManagerImpl) {
        return new AirReactInstanceManagerImpl$$Lambda$1(airReactInstanceManagerImpl);
    }

    public void logMarker(String str) {
        this.arg$1.logMarker(str);
    }
}
