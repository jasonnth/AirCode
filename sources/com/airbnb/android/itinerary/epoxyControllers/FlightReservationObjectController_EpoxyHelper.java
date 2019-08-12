package com.airbnb.android.itinerary.epoxyControllers;

import com.airbnb.android.core.viewcomponents.models.EditorialMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class FlightReservationObjectController_EpoxyHelper extends ControllerHelper<FlightReservationObjectController> {
    private final FlightReservationObjectController controller;

    public FlightReservationObjectController_EpoxyHelper(FlightReservationObjectController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marquee = new EditorialMarqueeEpoxyModel_();
        this.controller.marquee.m4546id(-1);
        setControllerToStageTo(this.controller.marquee, this.controller);
    }
}
