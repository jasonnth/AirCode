package com.airbnb.android.itinerary.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$6 implements Action1 {
    private static final ItineraryDataController$$Lambda$6 instance = new ItineraryDataController$$Lambda$6();

    private ItineraryDataController$$Lambda$6() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ItineraryDataController.lambda$new$10((AirRequestNetworkException) obj);
    }
}
