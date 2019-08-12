package com.airbnb.android.react;

import com.facebook.react.ReactInstanceManager.ReactInstanceEventListener;
import com.facebook.react.bridge.ReactContext;
import p032rx.Subscription;
import p032rx.functions.Action0;

final /* synthetic */ class AirReactInstanceManagerImpl$$Lambda$3 implements ReactInstanceEventListener {
    private final Subscription arg$1;
    private final Action0 arg$2;

    private AirReactInstanceManagerImpl$$Lambda$3(Subscription subscription, Action0 action0) {
        this.arg$1 = subscription;
        this.arg$2 = action0;
    }

    public static ReactInstanceEventListener lambdaFactory$(Subscription subscription, Action0 action0) {
        return new AirReactInstanceManagerImpl$$Lambda$3(subscription, action0);
    }

    public void onReactContextInitialized(ReactContext reactContext) {
        AirReactInstanceManagerImpl.lambda$addReactInstanceEventListener$2(this.arg$1, this.arg$2, reactContext);
    }
}
