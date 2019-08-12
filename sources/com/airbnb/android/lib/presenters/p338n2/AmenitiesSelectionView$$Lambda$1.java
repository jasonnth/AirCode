package com.airbnb.android.lib.presenters.p338n2;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.lib.presenters.n2.AmenitiesSelectionView$$Lambda$1 */
final /* synthetic */ class AmenitiesSelectionView$$Lambda$1 implements Function {
    private static final AmenitiesSelectionView$$Lambda$1 instance = new AmenitiesSelectionView$$Lambda$1();

    private AmenitiesSelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new AmenitySelectionViewItem((Amenity) obj);
    }
}
