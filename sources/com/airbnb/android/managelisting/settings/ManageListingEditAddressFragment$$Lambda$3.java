package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingEditAddressFragment$$Lambda$3 implements Action1 {
    private final ManageListingEditAddressFragment arg$1;

    private ManageListingEditAddressFragment$$Lambda$3(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        this.arg$1 = manageListingEditAddressFragment;
    }

    public static Action1 lambdaFactory$(ManageListingEditAddressFragment manageListingEditAddressFragment) {
        return new ManageListingEditAddressFragment$$Lambda$3(manageListingEditAddressFragment);
    }

    public void call(Object obj) {
        ManageListingEditAddressFragment.lambda$new$2(this.arg$1, (SimpleListingResponse) obj);
    }
}
