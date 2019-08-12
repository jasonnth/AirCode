package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.responses.TimelineResponse;
import java.util.concurrent.Callable;

final /* synthetic */ class ItineraryDataController$$Lambda$35 implements Callable {
    private final ItineraryDataController arg$1;
    private final TimelineResponse arg$2;

    private ItineraryDataController$$Lambda$35(ItineraryDataController itineraryDataController, TimelineResponse timelineResponse) {
        this.arg$1 = itineraryDataController;
        this.arg$2 = timelineResponse;
    }

    public static Callable lambdaFactory$(ItineraryDataController itineraryDataController, TimelineResponse timelineResponse) {
        return new ItineraryDataController$$Lambda$35(itineraryDataController, timelineResponse);
    }

    public Object call() {
        return Integer.valueOf(this.arg$1.diffWithServer(this.arg$2.metadata.getConfirmationCodes()));
    }
}
