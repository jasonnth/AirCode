package com.airbnb.android.itinerary.controllers;

import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$19 implements Callable {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$19(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$19(itineraryDataController);
    }

    public Object call() {
        return this.arg$1.itineraryTableOpenHelper.getAllTimelineTrips();
    }
}
