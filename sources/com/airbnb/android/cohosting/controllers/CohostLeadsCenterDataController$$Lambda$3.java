package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.core.models.CohostInquiry;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostLeadsCenterDataController$$Lambda$3 implements Predicate {
    private final long arg$1;

    private CohostLeadsCenterDataController$$Lambda$3(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new CohostLeadsCenterDataController$$Lambda$3(j);
    }

    public boolean apply(Object obj) {
        return CohostLeadsCenterDataController.lambda$getCohostInquiry$2(this.arg$1, (CohostInquiry) obj);
    }
}
