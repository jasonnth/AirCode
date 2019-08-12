package com.airbnb.android.lib.tripassistant.amenities;

import com.airbnb.android.core.models.HelpThreadAmenity;
import com.google.common.base.Function;

final /* synthetic */ class HTAmenitiesSelectionView$$Lambda$1 implements Function {
    private static final HTAmenitiesSelectionView$$Lambda$1 instance = new HTAmenitiesSelectionView$$Lambda$1();

    private HTAmenitiesSelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new HelpThreadAmenitySelectionViewItem((HelpThreadAmenity) obj);
    }
}
