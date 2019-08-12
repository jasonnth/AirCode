package com.airbnb.android.itinerary.controllers;

import java.util.List;
import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$22 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$22(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$22(itineraryDataController);
    }

    public void call(Object obj) {
        this.arg$1.handleTripEventsDatabaseResults((List) obj);
    }
}
