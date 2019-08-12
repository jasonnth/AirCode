package com.airbnb.android.itinerary.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$4 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$4(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$4(itineraryDataController);
    }

    public void call(Object obj) {
        ItineraryDataController.lambda$new$8(this.arg$1, (AirRequestNetworkException) obj);
    }
}
