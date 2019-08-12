package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.components.HomeAmenities;
import com.airbnb.p027n2.utils.AccessibleDrawableResource;
import com.google.common.base.Function;

final /* synthetic */ class HomeAmenitiesEpoxyModel$$Lambda$2 implements Function {
    private final HomeAmenities arg$1;

    private HomeAmenitiesEpoxyModel$$Lambda$2(HomeAmenities homeAmenities) {
        this.arg$1 = homeAmenities;
    }

    public static Function lambdaFactory$(HomeAmenities homeAmenities) {
        return new HomeAmenitiesEpoxyModel$$Lambda$2(homeAmenities);
    }

    public Object apply(Object obj) {
        return AccessibleDrawableResource.create(((Amenity) obj).getDrawableRes(), this.arg$1.getContext().getString(((Amenity) obj).stringRes));
    }
}
