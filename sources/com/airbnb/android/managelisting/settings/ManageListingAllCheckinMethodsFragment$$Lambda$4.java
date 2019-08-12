package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$4 implements Action1 {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$4(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$4(manageListingAllCheckinMethodsFragment);
    }

    public void call(Object obj) {
        ManageListingAllCheckinMethodsFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
