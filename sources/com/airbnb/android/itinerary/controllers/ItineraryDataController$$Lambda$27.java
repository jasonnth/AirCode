package com.airbnb.android.itinerary.controllers;

import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$27 implements Callable {
    private final ItineraryDataController arg$1;
    private final String arg$2;

    private ItineraryDataController$$Lambda$27(ItineraryDataController itineraryDataController, String str) {
        this.arg$1 = itineraryDataController;
        this.arg$2 = str;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController, String str) {
        return new ItineraryDataController$$Lambda$27(itineraryDataController, str);
    }

    public Object call() {
        return this.arg$1.itineraryTableOpenHelper.getExperienceReservationObject(this.arg$2);
    }
}
