package com.airbnb.android.managelisting.picker;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class ManageListingPickerFragment$$Lambda$4 implements OnRefreshListener {
    private final ManageListingPickerFragment arg$1;

    private ManageListingPickerFragment$$Lambda$4(ManageListingPickerFragment manageListingPickerFragment) {
        this.arg$1 = manageListingPickerFragment;
    }

    public static OnRefreshListener lambdaFactory$(ManageListingPickerFragment manageListingPickerFragment) {
        return new ManageListingPickerFragment$$Lambda$4(manageListingPickerFragment);
    }

    public void onRefresh() {
        this.arg$1.makeListingInfoRequest(true);
    }
}
