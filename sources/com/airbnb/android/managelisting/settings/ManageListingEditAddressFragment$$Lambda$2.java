package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingEditAddressFragment$$Lambda$2 implements Action1 {
    private final ManageListingEditAddressFragment arg$1;

    private ManageListingEditAddressFragment$$Lambda$2(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        this.arg$1 = manageListingEditAddressFragment;
    }

    public static Action1 lambdaFactory$(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        return new ManageListingEditAddressFragment$$Lambda$2(manageListingEditAddressFragment);
    }

    public void call(Object obj) {
        ManageListingEditAddressFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
