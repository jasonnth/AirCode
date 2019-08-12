package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.CheckInInformation;
import com.google.common.base.Function;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$7 implements Function {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$7(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Function lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$7(manageListingAllCheckinMethodsFragment);
    }

    public Object apply(Object obj) {
        return ManageListingAllCheckinMethodsFragment.lambda$onNextClicked$7(this.arg$1, (CheckInInformation) obj);
    }
}
