package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Predicate;

final /* synthetic */ class HouseRulesAndExpectationsUtils$$Lambda$1 implements Predicate {
    private static final HouseRulesAndExpectationsUtils$$Lambda$1 instance = new HouseRulesAndExpectationsUtils$$Lambda$1();

    private HouseRulesAndExpectationsUtils$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ListingExpectation) obj).isChecked();
    }
}
