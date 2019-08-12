package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingAmenityListController$$Lambda$3 implements Predicate {
    private final ManageListingAmenityListController arg$1;

    private ManageListingAmenityListController$$Lambda$3(ManageListingAmenityListController manageListingAmenityListController) {
        this.arg$1 = manageListingAmenityListController;
    }

    public static Predicate lambdaFactory$(ManageListingAmenityListController manageListingAmenityListController) {
        return new ManageListingAmenityListController$$Lambda$3(manageListingAmenityListController);
    }

    public boolean apply(Object obj) {
        return this.arg$1.amenitiesIds.contains(Integer.valueOf(((Amenity) obj).f8471id));
    }
}
