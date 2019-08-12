package com.airbnb.android.managelisting.picker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPickerEpoxyController$$Lambda$1 implements OnClickListener {
    private final ManageListingPickerEpoxyController arg$1;

    private ManageListingPickerEpoxyController$$Lambda$1(ManageListingPickerEpoxyController manageListingPickerEpoxyController) {
        this.arg$1 = manageListingPickerEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ManageListingPickerEpoxyController manageListingPickerEpoxyController) {
        return new ManageListingPickerEpoxyController$$Lambda$1(manageListingPickerEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.newListing();
    }
}
