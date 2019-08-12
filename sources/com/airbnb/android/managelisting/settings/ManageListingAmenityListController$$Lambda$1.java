package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicate;
import java.util.Set;

final /* synthetic */ class ManageListingAmenityListController$$Lambda$1 implements Predicate {
    private final Set arg$1;

    private ManageListingAmenityListController$$Lambda$1(Set set) {
        this.arg$1 = set;
    }

    public static Predicate lambdaFactory$(Set set) {
        return new ManageListingAmenityListController$$Lambda$1(set);
    }

    public boolean apply(Object obj) {
        return ManageListingAmenityListController.lambda$filteredAmenities$0(this.arg$1, (Amenity) obj);
    }
}
