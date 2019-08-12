package com.airbnb.android.managelisting.picker;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingPickerFragment$$Lambda$3 implements Action1 {
    private final ManageListingPickerFragment arg$1;

    private ManageListingPickerFragment$$Lambda$3(ManageListingPickerFragment manageListingPickerFragment) {
        this.arg$1 = manageListingPickerFragment;
    }

    public static Action1 lambdaFactory$(ManageListingPickerFragment manageListingPickerFragment) {
        return new ManageListingPickerFragment$$Lambda$3(manageListingPickerFragment);
    }

    public void call(Object obj) {
        this.arg$1.swipeRefreshLayout.setRefreshing(false);
    }
}
