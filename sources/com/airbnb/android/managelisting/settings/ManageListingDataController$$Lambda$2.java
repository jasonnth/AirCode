package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInInformation;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingDataController$$Lambda$2 implements Predicate {
    private static final ManageListingDataController$$Lambda$2 instance = new ManageListingDataController$$Lambda$2();

    private ManageListingDataController$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((CheckInInformation) obj).isIsOptionAvailable().booleanValue();
    }
}
