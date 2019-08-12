package com.airbnb.android.itinerary.adapters;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ItineraryEpoxyController_EpoxyHelper extends ControllerHelper<ItineraryEpoxyController> {
    private final ItineraryEpoxyController controller;

    public ItineraryEpoxyController_EpoxyHelper(ItineraryEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.paginationLoadingModel = new EpoxyControllerLoadingModel_();
        this.controller.paginationLoadingModel.m4582id(-1);
        setControllerToStageTo(this.controller.paginationLoadingModel, this.controller);
    }
}
