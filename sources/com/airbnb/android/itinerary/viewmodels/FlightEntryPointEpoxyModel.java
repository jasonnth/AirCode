package com.airbnb.android.itinerary.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FlightEntryPointEpoxyModel extends AirEpoxyModel<ItineraryItemView> {
    OnClickListener acceptClickListener;
    OnClickListener dismissClickListener;
    FlightEntryPointItem flightEntryPointItem;

    public void bind(ItineraryItemView view) {
        super.bind(view);
        view.setFlightEntryPointItem(this.flightEntryPointItem, this.acceptClickListener, this.dismissClickListener);
    }
}
