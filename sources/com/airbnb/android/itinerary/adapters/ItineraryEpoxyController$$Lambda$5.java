package com.airbnb.android.itinerary.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;

final /* synthetic */ class ItineraryEpoxyController$$Lambda$5 implements OnClickListener {
    private final ItineraryEpoxyController arg$1;
    private final FlightEntryPointItem arg$2;

    private ItineraryEpoxyController$$Lambda$5(ItineraryEpoxyController itineraryEpoxyController, FlightEntryPointItem flightEntryPointItem) {
        this.arg$1 = itineraryEpoxyController;
        this.arg$2 = flightEntryPointItem;
    }

    public static OnClickListener lambdaFactory$(ItineraryEpoxyController itineraryEpoxyController, FlightEntryPointItem flightEntryPointItem) {
        return new ItineraryEpoxyController$$Lambda$5(itineraryEpoxyController, flightEntryPointItem);
    }

    public void onClick(View view) {
        this.arg$1.dataController.removeFlightEntryPoint(this.arg$2);
    }
}
