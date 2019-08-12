package com.airbnb.android.core.modules;

import com.airbnb.airrequest.Interceptor;
import com.airbnb.airrequest.Interceptor.Factory;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import java.lang.reflect.Type;

final /* synthetic */ class NetworkModule$$Lambda$3 implements Factory {
    private final AirRequestHeadersInterceptor arg$1;

    private NetworkModule$$Lambda$3(AirRequestHeadersInterceptor airRequestHeadersInterceptor) {
        this.arg$1 = airRequestHeadersInterceptor;
    }

    public static Factory lambdaFactory$(AirRequestHeadersInterceptor airRequestHeadersInterceptor) {
        return new NetworkModule$$Lambda$3(airRequestHeadersInterceptor);
    }

    public Interceptor interceptorFor(Type type) {
        return NetworkModule.lambda$provideAirRequestInitializer$2(this.arg$1, type);
    }
}
