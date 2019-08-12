package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSCityRegistrationInputGroupFragment$$Lambda$2 implements Predicate {
    private static final LYSCityRegistrationInputGroupFragment$$Lambda$2 instance = new LYSCityRegistrationInputGroupFragment$$Lambda$2();

    private LYSCityRegistrationInputGroupFragment$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSCityRegistrationInputGroupFragment.lambda$saveAddressToFirstAddressQuestion$0((ListingRegistrationQuestion) obj);
    }
}
