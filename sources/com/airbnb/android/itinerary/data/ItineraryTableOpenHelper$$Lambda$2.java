package com.airbnb.android.itinerary.data;

import com.airbnb.android.itinerary.data.models.TripEvent;
import com.google.common.base.Predicate;

final /* synthetic */ class ItineraryTableOpenHelper$$Lambda$2 implements Predicate {
    private final ItineraryTableOpenHelper arg$1;

    private ItineraryTableOpenHelper$$Lambda$2(ItineraryTableOpenHelper itineraryTableOpenHelper) {
        this.arg$1 = itineraryTableOpenHelper;
    }

    public static Predicate lambdaFactory$(ItineraryTableOpenHelper itineraryTableOpenHelper) {
        return new ItineraryTableOpenHelper$$Lambda$2(itineraryTableOpenHelper);
    }

    public boolean apply(Object obj) {
        return this.arg$1.insertTripEvent((TripEvent) obj);
    }
}
