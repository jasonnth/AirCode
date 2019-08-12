package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.google.common.base.Function;

final /* synthetic */ class AirBatchRequest$$Lambda$2 implements Function {
    private static final AirBatchRequest$$Lambda$2 instance = new AirBatchRequest$$Lambda$2();

    private AirBatchRequest$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((BaseRequestV2) obj).getClass().getSimpleName();
    }
}
