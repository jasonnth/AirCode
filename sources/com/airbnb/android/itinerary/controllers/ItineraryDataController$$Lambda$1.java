package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.responses.TimelineResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$1 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$1(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$1(itineraryDataController);
    }

    public void call(Object obj) {
        ItineraryDataController.lambda$new$7(this.arg$1, (TimelineResponse) obj);
    }
}
