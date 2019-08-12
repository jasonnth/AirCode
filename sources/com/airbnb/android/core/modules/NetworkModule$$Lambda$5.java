package com.airbnb.android.core.modules;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import p032rx.functions.Action1;

final /* synthetic */ class NetworkModule$$Lambda$5 implements Action1 {
    private final AirRequestHeadersInterceptor arg$1;

    private NetworkModule$$Lambda$5(AirRequestHeadersInterceptor airRequestHeadersInterceptor) {
        this.arg$1 = airRequestHeadersInterceptor;
    }

    public static Action1 lambdaFactory$(AirRequestHeadersInterceptor airRequestHeadersInterceptor) {
        return new NetworkModule$$Lambda$5(airRequestHeadersInterceptor);
    }

    public void call(Object obj) {
        this.arg$1.updateSID((AirResponse) obj);
    }
}
