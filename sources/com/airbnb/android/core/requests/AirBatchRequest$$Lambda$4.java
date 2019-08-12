package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.google.common.base.Predicate;

final /* synthetic */ class AirBatchRequest$$Lambda$4 implements Predicate {
    private final Class arg$1;

    private AirBatchRequest$$Lambda$4(Class cls) {
        this.arg$1 = cls;
    }

    public static Predicate lambdaFactory$(Class cls) {
        return new AirBatchRequest$$Lambda$4(cls);
    }

    public boolean apply(Object obj) {
        return ((BaseRequestV2) obj).getClass().equals(this.arg$1);
    }
}
