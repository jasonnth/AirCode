package com.airbnb.android.lib.tripassistant.amenities;

import com.google.common.base.Function;

final /* synthetic */ class HTAmenitiesSelectionView$$Lambda$2 implements Function {
    private static final HTAmenitiesSelectionView$$Lambda$2 instance = new HTAmenitiesSelectionView$$Lambda$2();

    private HTAmenitiesSelectionView$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((HelpThreadAmenitySelectionViewItem) obj).getAmenity();
    }
}
