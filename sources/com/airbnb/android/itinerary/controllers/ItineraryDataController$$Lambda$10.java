package com.airbnb.android.itinerary.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$10 implements Action1 {
    private static final ItineraryDataController$$Lambda$10 instance = new ItineraryDataController$$Lambda$10();

    private ItineraryDataController$$Lambda$10() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ItineraryDataController.lambda$new$14((AirRequestNetworkException) obj);
    }
}
