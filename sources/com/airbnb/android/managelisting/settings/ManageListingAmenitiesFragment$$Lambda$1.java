package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAmenitiesFragment$$Lambda$1 implements Action1 {
    private final ManageListingAmenitiesFragment arg$1;

    private ManageListingAmenitiesFragment$$Lambda$1(ManageListingAmenitiesFragment manageListingAmenitiesFragment) {
        this.arg$1 = manageListingAmenitiesFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAmenitiesFragment manageListingAmenitiesFragment) {
        return new ManageListingAmenitiesFragment$$Lambda$1(manageListingAmenitiesFragment);
    }

    public void call(Object obj) {
        ManageListingAmenitiesFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
