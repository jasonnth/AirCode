package com.airbnb.android.managelisting.picker;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingPickerInfo;

final /* synthetic */ class ManageListingPickerEpoxyController$$Lambda$2 implements OnClickListener {
    private final ManageListingPickerEpoxyController arg$1;
    private final ListingPickerInfo arg$2;

    private ManageListingPickerEpoxyController$$Lambda$2(ManageListingPickerEpoxyController manageListingPickerEpoxyController, ListingPickerInfo listingPickerInfo) {
        this.arg$1 = manageListingPickerEpoxyController;
        this.arg$2 = listingPickerInfo;
    }

    public static OnClickListener lambdaFactory$(ManageListingPickerEpoxyController manageListingPickerEpoxyController, ListingPickerInfo listingPickerInfo) {
        return new ManageListingPickerEpoxyController$$Lambda$2(manageListingPickerEpoxyController, listingPickerInfo);
    }

    public void onClick(View view) {
        this.arg$1.listener.continueNewListing(this.arg$2.getId());
    }
}
