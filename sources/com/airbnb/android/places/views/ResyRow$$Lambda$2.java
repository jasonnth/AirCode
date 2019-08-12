package com.airbnb.android.places.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RestaurantAvailability;

final /* synthetic */ class ResyRow$$Lambda$2 implements OnClickListener {
    private final ResyRow arg$1;
    private final RestaurantAvailability arg$2;

    private ResyRow$$Lambda$2(ResyRow resyRow, RestaurantAvailability restaurantAvailability) {
        this.arg$1 = resyRow;
        this.arg$2 = restaurantAvailability;
    }

    public static OnClickListener lambdaFactory$(ResyRow resyRow, RestaurantAvailability restaurantAvailability) {
        return new ResyRow$$Lambda$2(resyRow, restaurantAvailability);
    }

    public void onClick(View view) {
        this.arg$1.onTimeSlotClick(this.arg$2);
    }
}
