package com.airbnb.android.listing.controllers;

import com.airbnb.android.core.viewcomponents.models.ImpactMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class WhatsMyPlaceWorthEpoxyController_EpoxyHelper extends ControllerHelper<WhatsMyPlaceWorthEpoxyController> {
    private final WhatsMyPlaceWorthEpoxyController controller;

    public WhatsMyPlaceWorthEpoxyController_EpoxyHelper(WhatsMyPlaceWorthEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.titleModel = new ImpactMarqueeEpoxyModel_();
        this.controller.titleModel.m4810id(-1);
        setControllerToStageTo(this.controller.titleModel, this.controller);
        this.controller.capacityModel = new InlineInputRowEpoxyModel_();
        this.controller.capacityModel.m4882id(-2);
        setControllerToStageTo(this.controller.capacityModel, this.controller);
        this.controller.addressModel = new InlineInputRowEpoxyModel_();
        this.controller.addressModel.m4882id(-3);
        setControllerToStageTo(this.controller.addressModel, this.controller);
        this.controller.placeTypeModel = new InlineInputRowEpoxyModel_();
        this.controller.placeTypeModel.m4882id(-4);
        setControllerToStageTo(this.controller.placeTypeModel, this.controller);
    }
}
