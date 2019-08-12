package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.google.common.base.Function;

final /* synthetic */ class ItineraryNavigationController$$Lambda$1 implements Function {
    private static final ItineraryNavigationController$$Lambda$1 instance = new ItineraryNavigationController$$Lambda$1();

    private ItineraryNavigationController$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((TimelineTrip) obj).toBundle();
    }
}
