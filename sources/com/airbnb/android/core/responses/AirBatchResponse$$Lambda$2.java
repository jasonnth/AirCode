package com.airbnb.android.core.responses;

import com.google.common.base.Function;

final /* synthetic */ class AirBatchResponse$$Lambda$2 implements Function {
    private final Class arg$1;

    private AirBatchResponse$$Lambda$2(Class cls) {
        this.arg$1 = cls;
    }

    public static Function lambdaFactory$(Class cls) {
        return new AirBatchResponse$$Lambda$2(cls);
    }

    public Object apply(Object obj) {
        return AirBatchResponse.lambda$filterResponses$1(this.arg$1, (BatchOperation) obj);
    }
}
