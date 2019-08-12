package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.google.common.base.Predicate;

final /* synthetic */ class AirBatchRequest$$Lambda$1 implements Predicate {
    private static final AirBatchRequest$$Lambda$1 instance = new AirBatchRequest$$Lambda$1();

    private AirBatchRequest$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((BaseRequestV2) obj).isAllowedIfMonkey();
    }
}
