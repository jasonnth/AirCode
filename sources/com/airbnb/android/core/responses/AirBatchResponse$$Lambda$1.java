package com.airbnb.android.core.responses;

import com.google.common.base.Predicate;

final /* synthetic */ class AirBatchResponse$$Lambda$1 implements Predicate {
    private final Class arg$1;

    private AirBatchResponse$$Lambda$1(Class cls) {
        this.arg$1 = cls;
    }

    public static Predicate lambdaFactory$(Class cls) {
        return new AirBatchResponse$$Lambda$1(cls);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isInstance(((BatchOperation) obj).convertedResponse);
    }
}
