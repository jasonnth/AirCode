package com.airbnb.android.core.erf;

import com.airbnb.erf.Experiment;
import com.google.common.base.Predicate;

final /* synthetic */ class ExperimentsProvider$$Lambda$5 implements Predicate {
    private static final ExperimentsProvider$$Lambda$5 instance = new ExperimentsProvider$$Lambda$5();

    private ExperimentsProvider$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((Experiment) obj).hasHoldoutExperiment();
    }
}
