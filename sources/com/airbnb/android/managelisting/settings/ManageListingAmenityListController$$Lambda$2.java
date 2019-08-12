package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.AmenityGroup;

final /* synthetic */ class ManageListingAmenityListController$$Lambda$2 implements OnClickListener {
    private final ManageListingAmenityListController arg$1;
    private final Integer arg$2;
    private final AmenityGroup arg$3;

    private ManageListingAmenityListController$$Lambda$2(ManageListingAmenityListController manageListingAmenityListController, Integer num, AmenityGroup amenityGroup) {
        this.arg$1 = manageListingAmenityListController;
        this.arg$2 = num;
        this.arg$3 = amenityGroup;
    }

    public static OnClickListener lambdaFactory$(ManageListingAmenityListController manageListingAmenityListController, Integer num, AmenityGroup amenityGroup) {
        return new ManageListingAmenityListController$$Lambda$2(manageListingAmenityListController, num, amenityGroup);
    }

    public void onClick(View view) {
        this.arg$1.controllerInterface.onRowClicked(this.arg$2.intValue(), this.arg$3);
    }
}
