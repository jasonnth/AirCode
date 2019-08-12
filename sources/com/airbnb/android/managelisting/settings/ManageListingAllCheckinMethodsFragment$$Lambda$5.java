package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInInformation;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$5 implements Predicate {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$5(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Predicate lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$5(manageListingAllCheckinMethodsFragment);
    }

    public boolean apply(Object obj) {
        return ManageListingAllCheckinMethodsFragment.lambda$getEnabledCheckInMethods$5(this.arg$1, (CheckInInformation) obj);
    }
}
