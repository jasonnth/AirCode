package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.google.common.base.Predicate;

final /* synthetic */ class CityRegistrationInputGroupFragment$$Lambda$2 implements Predicate {
    private static final CityRegistrationInputGroupFragment$$Lambda$2 instance = new CityRegistrationInputGroupFragment$$Lambda$2();

    private CityRegistrationInputGroupFragment$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return CityRegistrationInputGroupFragment.lambda$saveAddressToFirstAddressQuestion$0((ListingRegistrationQuestion) obj);
    }
}
