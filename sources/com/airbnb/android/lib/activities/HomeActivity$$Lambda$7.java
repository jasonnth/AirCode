package com.airbnb.android.lib.activities;

import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.react.AirReactInstanceManager;

final /* synthetic */ class HomeActivity$$Lambda$7 implements Runnable {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$7(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static Runnable lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$7(homeActivity);
    }

    public void run() {
        ((AirReactInstanceManager) this.arg$1.reactInstanceManager.get()).preloadScreen(ReactNativeIntents.SCREEN_CITY_HOSTS_GUEST_SINGLE_EXPERIENCE_TEMPLATES);
    }
}
