package com.airbnb.android.core;

import com.squareup.otto.Bus;

final /* synthetic */ class AirbnbApi$$Lambda$1 implements Runnable {
    private final AirbnbApi arg$1;
    private final Bus arg$2;

    private AirbnbApi$$Lambda$1(AirbnbApi airbnbApi, Bus bus) {
        this.arg$1 = airbnbApi;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(AirbnbApi airbnbApi, Bus bus) {
        return new AirbnbApi$$Lambda$1(airbnbApi, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
