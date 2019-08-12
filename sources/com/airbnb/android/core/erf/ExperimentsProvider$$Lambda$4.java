package com.airbnb.android.core.erf;

import com.google.common.base.Function;

final /* synthetic */ class ExperimentsProvider$$Lambda$4 implements Function {
    private static final ExperimentsProvider$$Lambda$4 instance = new ExperimentsProvider$$Lambda$4();

    private ExperimentsProvider$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ExperimentsProvider.lambda$getExperimentsWithHoldout$3((ErfExperiment) obj);
    }
}
