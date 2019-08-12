package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.itinerary.HomeReservationModel;
import com.airbnb.android.itinerary.HomeReservationModel.Creator;

final /* synthetic */ class HomeReservationObject$$Lambda$1 implements Creator {
    private static final HomeReservationObject$$Lambda$1 instance = new HomeReservationObject$$Lambda$1();

    private HomeReservationObject$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public HomeReservationModel create(String str, String str2) {
        return new AutoValue_HomeReservationObject(str, str2);
    }
}
