package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInInformation;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$6 implements Predicate {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$6(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Predicate lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$6(manageListingAllCheckinMethodsFragment);
    }

    public boolean apply(Object obj) {
        return this.arg$1.changedMethods.containsKey(Integer.valueOf(((CheckInInformation) obj).getAmenityNumber()));
    }
}
