package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.itinerary.PlaceReservationModel;
import com.airbnb.android.itinerary.PlaceReservationModel.Creator;

final /* synthetic */ class PlaceReservationObject$$Lambda$1 implements Creator {
    private static final PlaceReservationObject$$Lambda$1 instance = new PlaceReservationObject$$Lambda$1();

    private PlaceReservationObject$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public PlaceReservationModel create(String str, String str2) {
        return new AutoValue_PlaceReservationObject(str, str2);
    }
}
