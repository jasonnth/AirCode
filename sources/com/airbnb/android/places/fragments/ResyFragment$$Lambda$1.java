package com.airbnb.android.places.fragments;

import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.places.ResyController.ResyTimeSlotClickListener;

final /* synthetic */ class ResyFragment$$Lambda$1 implements ResyTimeSlotClickListener {
    private final ResyFragment arg$1;

    private ResyFragment$$Lambda$1(ResyFragment resyFragment) {
        this.arg$1 = resyFragment;
    }

    public static ResyTimeSlotClickListener lambdaFactory$(ResyFragment resyFragment) {
        return new ResyFragment$$Lambda$1(resyFragment);
    }

    public void onClick(RestaurantAvailability restaurantAvailability) {
        ResyFragment.lambda$new$1(this.arg$1, restaurantAvailability);
    }
}
