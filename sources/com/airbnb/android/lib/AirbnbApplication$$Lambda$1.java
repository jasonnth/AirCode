package com.airbnb.android.lib;

import com.airbnb.android.lib.coldstart.PreloadExecutor.DelayedTask;

final /* synthetic */ class AirbnbApplication$$Lambda$1 implements DelayedTask {
    private final AirbnbApplication arg$1;

    private AirbnbApplication$$Lambda$1(AirbnbApplication airbnbApplication) {
        this.arg$1 = airbnbApplication;
    }

    public static DelayedTask lambdaFactory$(AirbnbApplication airbnbApplication) {
        return new AirbnbApplication$$Lambda$1(airbnbApplication);
    }

    public void onPreloadComplete() {
        this.arg$1.deferredAppInitExecutor.schedulePostApplicationCreatedInitialization();
    }
}
