package com.airbnb.android.cityregistration.adapters;

import com.google.common.base.Predicate;

final /* synthetic */ class CityRegistrationSubmissionAdapter$$Lambda$4 implements Predicate {
    private static final CityRegistrationSubmissionAdapter$$Lambda$4 instance = new CityRegistrationSubmissionAdapter$$Lambda$4();

    private CityRegistrationSubmissionAdapter$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return CityRegistrationSubmissionAdapter.lambda$getDisplayAddress$1((String) obj);
    }
}
