package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingEditAddressFragment$$Lambda$1 implements Action1 {
    private final ManageListingEditAddressFragment arg$1;

    private ManageListingEditAddressFragment$$Lambda$1(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        this.arg$1 = manageListingEditAddressFragment;
    }

    public static Action1 lambdaFactory$(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        return new ManageListingEditAddressFragment$$Lambda$1(manageListingEditAddressFragment);
    }

    public void call(Object obj) {
        ManageListingEditAddressFragment.lambda$new$0(this.arg$1, (ListingRegistrationProcessesResponse) obj);
    }
}
