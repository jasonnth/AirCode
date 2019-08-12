package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicate;

final /* synthetic */ class HomeAmenitiesEpoxyModel$$Lambda$1 implements Predicate {
    private static final HomeAmenitiesEpoxyModel$$Lambda$1 instance = new HomeAmenitiesEpoxyModel$$Lambda$1();

    private HomeAmenitiesEpoxyModel$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((Amenity) obj).hasDrawableRes();
    }
}
