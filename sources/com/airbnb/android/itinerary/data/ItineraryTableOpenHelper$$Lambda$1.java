package com.airbnb.android.itinerary.data;

import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.google.common.base.Predicate;

final /* synthetic */ class ItineraryTableOpenHelper$$Lambda$1 implements Predicate {
    private final ItineraryTableOpenHelper arg$1;

    private ItineraryTableOpenHelper$$Lambda$1(ItineraryTableOpenHelper itineraryTableOpenHelper) {
        this.arg$1 = itineraryTableOpenHelper;
    }

    public static Predicate lambdaFactory$(ItineraryTableOpenHelper itineraryTableOpenHelper) {
        return new ItineraryTableOpenHelper$$Lambda$1(itineraryTableOpenHelper);
    }

    public boolean apply(Object obj) {
        return this.arg$1.insertTimelineTrip((TimelineTrip) obj);
    }
}
