package com.airbnb.android.core.erf;

import com.google.common.base.Function;

final /* synthetic */ class ExperimentsProvider$$Lambda$2 implements Function {
    private static final ExperimentsProvider$$Lambda$2 instance = new ExperimentsProvider$$Lambda$2();

    private ExperimentsProvider$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ExperimentsProvider.lambda$getExperimentDebugOutput$1((ErfExperiment) obj);
    }
}
