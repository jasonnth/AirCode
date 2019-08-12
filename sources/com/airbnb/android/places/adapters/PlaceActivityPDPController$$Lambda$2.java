package com.airbnb.android.places.adapters;

import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;
import com.airbnb.android.places.adapters.PlaceActivityPDPController.PlaceActivityPDPNavigationController;

final /* synthetic */ class PlaceActivityPDPController$$Lambda$2 implements ResyTimeSlotClickListener {
    private final PlaceActivityPDPNavigationController arg$1;

    private PlaceActivityPDPController$$Lambda$2(PlaceActivityPDPNavigationController placeActivityPDPNavigationController) {
        this.arg$1 = placeActivityPDPNavigationController;
    }

    public static ResyTimeSlotClickListener lambdaFactory$(PlaceActivityPDPNavigationController placeActivityPDPNavigationController) {
        return new PlaceActivityPDPController$$Lambda$2(placeActivityPDPNavigationController);
    }

    public void onClick(RestaurantAvailability restaurantAvailability) {
        this.arg$1.onTapTimeSlot(restaurantAvailability);
    }
}
