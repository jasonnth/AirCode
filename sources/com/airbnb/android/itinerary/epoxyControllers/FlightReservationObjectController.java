package com.airbnb.android.itinerary.epoxyControllers;

import com.airbnb.android.core.viewcomponents.models.EditorialMarqueeEpoxyModel_;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.FlightReservation;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class FlightReservationObjectController extends AirEpoxyController {
    EditorialMarqueeEpoxyModel_ marquee;
    private FlightReservation reservation;

    public void setReservation(FlightReservation reservation2) {
        this.reservation = reservation2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marquee.titleRes(C5755R.string.itinerary_mock_flight_name);
    }
}
