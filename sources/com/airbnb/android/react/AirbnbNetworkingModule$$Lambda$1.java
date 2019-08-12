package com.airbnb.android.react;

import okhttp3.Request.Builder;
import p032rx.Single.OnSubscribe;
import p032rx.SingleSubscriber;

final /* synthetic */ class AirbnbNetworkingModule$$Lambda$1 implements OnSubscribe {
    private final AirbnbNetworkingModule arg$1;
    private final Builder arg$2;

    private AirbnbNetworkingModule$$Lambda$1(AirbnbNetworkingModule airbnbNetworkingModule, Builder builder) {
        this.arg$1 = airbnbNetworkingModule;
        this.arg$2 = builder;
    }

    public static OnSubscribe lambdaFactory$(AirbnbNetworkingModule airbnbNetworkingModule, Builder builder) {
        return new AirbnbNetworkingModule$$Lambda$1(airbnbNetworkingModule, builder);
    }

    public void call(Object obj) {
        this.arg$1.client.newCall(this.arg$2.build()).enqueue(new DelegatingCallback((SingleSubscriber) obj));
    }
}
