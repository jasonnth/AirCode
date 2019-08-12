package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$3 implements Action1 {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$3(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$3(manageListingAllCheckinMethodsFragment);
    }

    public void call(Object obj) {
        ManageListingAllCheckinMethodsFragment.lambda$new$2(this.arg$1, (ListingCheckInInformationResponse) obj);
    }
}
