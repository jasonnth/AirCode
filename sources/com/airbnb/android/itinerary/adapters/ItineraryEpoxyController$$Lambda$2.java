package com.airbnb.android.itinerary.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.TripEvent;

final /* synthetic */ class ItineraryEpoxyController$$Lambda$2 implements OnClickListener {
    private final ItineraryEpoxyController arg$1;
    private final ItineraryNavigationController arg$2;
    private final TripEvent arg$3;

    private ItineraryEpoxyController$$Lambda$2(ItineraryEpoxyController itineraryEpoxyController, ItineraryNavigationController itineraryNavigationController, TripEvent tripEvent) {
        this.arg$1 = itineraryEpoxyController;
        this.arg$2 = itineraryNavigationController;
        this.arg$3 = tripEvent;
    }

    public static OnClickListener lambdaFactory$(ItineraryEpoxyController itineraryEpoxyController, ItineraryNavigationController itineraryNavigationController, TripEvent tripEvent) {
        return new ItineraryEpoxyController$$Lambda$2(itineraryEpoxyController, itineraryNavigationController, tripEvent);
    }

    public void onClick(View view) {
        this.arg$2.navigateToTripEventCard(this.arg$3, this.arg$1.isTimeline);
    }
}
