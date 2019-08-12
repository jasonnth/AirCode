package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Predicate;

final /* synthetic */ class HouseRulesAndExpectationsUtils$$Lambda$2 implements Predicate {
    private static final HouseRulesAndExpectationsUtils$$Lambda$2 instance = new HouseRulesAndExpectationsUtils$$Lambda$2();

    private HouseRulesAndExpectationsUtils$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HouseRulesAndExpectationsUtils.lambda$hasAnyAddedDetails$0((ListingExpectation) obj);
    }
}
