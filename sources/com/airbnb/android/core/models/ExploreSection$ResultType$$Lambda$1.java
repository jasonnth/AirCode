package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.google.common.base.Predicate;

final /* synthetic */ class ExploreSection$ResultType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ExploreSection$ResultType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ExploreSection$ResultType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ResultType.lambda$fromKey$0(this.arg$1, (ResultType) obj);
    }
}
