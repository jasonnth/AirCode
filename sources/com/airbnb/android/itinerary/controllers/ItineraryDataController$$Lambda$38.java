package com.airbnb.android.itinerary.controllers;

import p032rx.functions.Action1;

final /* synthetic */ class ItineraryDataController$$Lambda$38 implements Action1 {
    private final ItineraryDataController arg$1;

    private ItineraryDataController$$Lambda$38(ItineraryDataController itineraryDataController) {
        this.arg$1 = itineraryDataController;
    }

    public static Action1 lambdaFactory$(ItineraryDataController itineraryDataController) {
        return new ItineraryDataController$$Lambda$38(itineraryDataController);
    }

    public void call(Object obj) {
        this.arg$1.triggerRefreshForTimelineTrips(((Integer) obj).intValue());
    }
}
