package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.HomeReservationObject;
import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$33 implements Callable {
    private final ItineraryDataController arg$1;
    private final HomeReservationObject arg$2;

    private ItineraryDataController$$Lambda$33(ItineraryDataController itineraryDataController, HomeReservationObject homeReservationObject) {
        this.arg$1 = itineraryDataController;
        this.arg$2 = homeReservationObject;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController, HomeReservationObject homeReservationObject) {
        return new ItineraryDataController$$Lambda$33(itineraryDataController, homeReservationObject);
    }

    public Object call() {
        return Integer.valueOf(this.arg$1.itineraryTableOpenHelper.insertHomeReservationObject(this.arg$2));
    }
}
