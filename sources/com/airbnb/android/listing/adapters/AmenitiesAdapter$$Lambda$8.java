package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class AmenitiesAdapter$$Lambda$8 implements OnCheckedChangeListener {
    private final AmenitiesAdapter arg$1;
    private final Amenity arg$2;

    private AmenitiesAdapter$$Lambda$8(AmenitiesAdapter amenitiesAdapter, Amenity amenity) {
        this.arg$1 = amenitiesAdapter;
        this.arg$2 = amenity;
    }

    public static OnCheckedChangeListener lambdaFactory$(AmenitiesAdapter amenitiesAdapter, Amenity amenity) {
        return new AmenitiesAdapter$$Lambda$8(amenitiesAdapter, amenity);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        AmenitiesAdapter.lambda$createAmenityRow$6(this.arg$1, this.arg$2, switchRowInterface, z);
    }
}
