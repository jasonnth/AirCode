package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.ExperienceReservationObject;
import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$29 implements Callable {
    private final ItineraryDataController arg$1;
    private final ExperienceReservationObject arg$2;

    private ItineraryDataController$$Lambda$29(ItineraryDataController itineraryDataController, ExperienceReservationObject experienceReservationObject) {
        this.arg$1 = itineraryDataController;
        this.arg$2 = experienceReservationObject;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController, ExperienceReservationObject experienceReservationObject) {
        return new ItineraryDataController$$Lambda$29(itineraryDataController, experienceReservationObject);
    }

    public Object call() {
        return Integer.valueOf(this.arg$1.itineraryTableOpenHelper.insertExperienceReservationObject(this.arg$2));
    }
}
