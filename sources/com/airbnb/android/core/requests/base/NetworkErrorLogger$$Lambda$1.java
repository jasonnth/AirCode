package com.airbnb.android.core.requests.base;

import com.google.common.base.Function;
import retrofit2.Query;

final /* synthetic */ class NetworkErrorLogger$$Lambda$1 implements Function {
    private static final NetworkErrorLogger$$Lambda$1 instance = new NetworkErrorLogger$$Lambda$1();

    private NetworkErrorLogger$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return NetworkErrorLogger.lambda$logErrorToBugSnag$0((Query) obj);
    }
}
