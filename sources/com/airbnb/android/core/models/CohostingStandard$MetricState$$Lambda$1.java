package com.airbnb.android.core.models;

import com.airbnb.android.core.models.CohostingStandard.MetricState;
import com.airbnb.android.core.models.CohostingStandard.State;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostingStandard$MetricState$$Lambda$1 implements Predicate {
    private final State arg$1;

    private CohostingStandard$MetricState$$Lambda$1(State state) {
        this.arg$1 = state;
    }

    public static Predicate lambdaFactory$(State state) {
        return new CohostingStandard$MetricState$$Lambda$1(state);
    }

    public boolean apply(Object obj) {
        return MetricState.lambda$forKey$0(this.arg$1, (MetricState) obj);
    }
}
