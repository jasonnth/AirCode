package com.airbnb.android.managelisting.picker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPickerFragment$$Lambda$5 implements OnClickListener {
    private final ManageListingPickerFragment arg$1;

    private ManageListingPickerFragment$$Lambda$5(ManageListingPickerFragment manageListingPickerFragment) {
        this.arg$1 = manageListingPickerFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingPickerFragment manageListingPickerFragment) {
        return new ManageListingPickerFragment$$Lambda$5(manageListingPickerFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeListingInfoRequest(false);
    }
}
