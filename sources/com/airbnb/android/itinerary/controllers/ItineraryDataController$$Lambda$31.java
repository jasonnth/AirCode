package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.PlaceReservationObject;
import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$31 implements Callable {
    private final ItineraryDataController arg$1;
    private final PlaceReservationObject arg$2;

    private ItineraryDataController$$Lambda$31(ItineraryDataController itineraryDataController, PlaceReservationObject placeReservationObject) {
        this.arg$1 = itineraryDataController;
        this.arg$2 = placeReservationObject;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController, PlaceReservationObject placeReservationObject) {
        return new ItineraryDataController$$Lambda$31(itineraryDataController, placeReservationObject);
    }

    public Object call() {
        return Integer.valueOf(this.arg$1.itineraryTableOpenHelper.insertPlaceReservationObject(this.arg$2));
    }
}
