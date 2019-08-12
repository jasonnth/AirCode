package com.airbnb.android.managelisting.picker;

import com.airbnb.android.core.responses.ListingPickerInfoResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingPickerFragment$$Lambda$1 implements Action1 {
    private final ManageListingPickerFragment arg$1;

    private ManageListingPickerFragment$$Lambda$1(ManageListingPickerFragment manageListingPickerFragment) {
        this.arg$1 = manageListingPickerFragment;
    }

    public static Action1 lambdaFactory$(ManageListingPickerFragment manageListingPickerFragment) {
        return new ManageListingPickerFragment$$Lambda$1(manageListingPickerFragment);
    }

    public void call(Object obj) {
        ManageListingPickerFragment.lambda$new$0(this.arg$1, (ListingPickerInfoResponse) obj);
    }
}
