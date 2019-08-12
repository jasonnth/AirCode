package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.Insight;
import com.google.common.base.Predicate;

final /* synthetic */ class InsightsRequest$$Lambda$1 implements Predicate {
    private static final InsightsRequest$$Lambda$1 instance = new InsightsRequest$$Lambda$1();

    private InsightsRequest$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return Insight.getValidInsightConversionTypes().contains(((Insight) obj).getStoryConversionType());
    }
}
