package com.airbnb.android.react;

import com.facebook.react.ReactInstanceManager.ReactInstanceEventListener;
import com.facebook.react.bridge.ReactContext;

final /* synthetic */ class AirReactInstanceManagerImpl$$Lambda$2 implements ReactInstanceEventListener {
    private static final AirReactInstanceManagerImpl$$Lambda$2 instance = new AirReactInstanceManagerImpl$$Lambda$2();

    private AirReactInstanceManagerImpl$$Lambda$2() {
    }

    public static ReactInstanceEventListener lambdaFactory$() {
        return instance;
    }

    public void onReactContextInitialized(ReactContext reactContext) {
        AirReactInstanceManagerImpl.lambda$enableAnimations$1(reactContext);
    }
}
