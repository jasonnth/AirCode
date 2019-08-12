package com.airbnb.android.itinerary.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.TimelineTrip;

final /* synthetic */ class ItineraryEpoxyController$$Lambda$1 implements OnClickListener {
    private final ItineraryNavigationController arg$1;
    private final TimelineTrip arg$2;
    private final boolean arg$3;

    private ItineraryEpoxyController$$Lambda$1(ItineraryNavigationController itineraryNavigationController, TimelineTrip timelineTrip, boolean z) {
        this.arg$1 = itineraryNavigationController;
        this.arg$2 = timelineTrip;
        this.arg$3 = z;
    }

    public static OnClickListener lambdaFactory$(ItineraryNavigationController itineraryNavigationController, TimelineTrip timelineTrip, boolean z) {
        return new ItineraryEpoxyController$$Lambda$1(itineraryNavigationController, timelineTrip, z);
    }

    public void onClick(View view) {
        this.arg$1.navigateToTripSchedule(view, this.arg$2, this.arg$3);
    }
}
