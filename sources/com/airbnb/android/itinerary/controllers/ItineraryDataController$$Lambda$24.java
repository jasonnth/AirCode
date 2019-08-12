package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.HomeReservationObject;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$24 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$24(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$24(itineraryDataController);
    }

    public void call(Object obj) {
        this.arg$1.handleReservationObjectDatabaseResults((HomeReservationObject) obj);
    }
}
