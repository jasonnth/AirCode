package com.airbnb.android.itinerary.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ItineraryEpoxyController$$Lambda$4 implements OnClickListener {
    private final ItineraryEpoxyController arg$1;

    private ItineraryEpoxyController$$Lambda$4(ItineraryEpoxyController itineraryEpoxyController) {
        this.arg$1 = itineraryEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ItineraryEpoxyController itineraryEpoxyController) {
        return new ItineraryEpoxyController$$Lambda$4(itineraryEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.navigationController.navigateToFlightLandingPage();
    }
}
