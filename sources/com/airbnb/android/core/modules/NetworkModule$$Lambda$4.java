package com.airbnb.android.core.modules;

import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.Transformer;
import com.airbnb.airrequest.Transformer.Factory;

final /* synthetic */ class NetworkModule$$Lambda$4 implements Factory {
    private final Transformer arg$1;

    private NetworkModule$$Lambda$4(Transformer transformer) {
        this.arg$1 = transformer;
    }

    public static Factory lambdaFactory$(Transformer transformer) {
        return new NetworkModule$$Lambda$4(transformer);
    }

    public Transformer transformerFor(AirRequest airRequest, AirRequestInitializer airRequestInitializer) {
        return NetworkModule.lambda$provideAirRequestInitializer$3(this.arg$1, airRequest, airRequestInitializer);
    }
}
