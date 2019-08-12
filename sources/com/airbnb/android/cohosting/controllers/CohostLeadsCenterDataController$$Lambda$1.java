package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.core.models.CohostingContract;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostLeadsCenterDataController$$Lambda$1 implements Predicate {
    private static final CohostLeadsCenterDataController$$Lambda$1 instance = new CohostLeadsCenterDataController$$Lambda$1();

    private CohostLeadsCenterDataController$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return CohostLeadsCenterDataController.lambda$getAcceptedContracts$0((CohostingContract) obj);
    }
}
