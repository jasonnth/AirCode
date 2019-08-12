package com.airbnb.android.core.erf;

import com.airbnb.erf.Experiment;
import com.google.common.base.Predicate;
import java.util.Map;

final /* synthetic */ class ExperimentsProvider$$Lambda$6 implements Predicate {
    private final Map arg$1;

    private ExperimentsProvider$$Lambda$6(Map map) {
        this.arg$1 = map;
    }

    public static Predicate lambdaFactory$(Map map) {
        return new ExperimentsProvider$$Lambda$6(map);
    }

    public boolean apply(Object obj) {
        return this.arg$1.containsKey(((Experiment) obj).holdoutExperimentName());
    }
}
