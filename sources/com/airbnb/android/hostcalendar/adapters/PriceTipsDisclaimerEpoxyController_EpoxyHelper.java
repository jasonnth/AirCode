package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class PriceTipsDisclaimerEpoxyController_EpoxyHelper extends ControllerHelper<PriceTipsDisclaimerEpoxyController> {
    private final PriceTipsDisclaimerEpoxyController controller;

    public PriceTipsDisclaimerEpoxyController_EpoxyHelper(PriceTipsDisclaimerEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerModel = new DocumentMarqueeEpoxyModel_();
        this.controller.headerModel.m4534id(-1);
        setControllerToStageTo(this.controller.headerModel, this.controller);
        this.controller.availabilityModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.availabilityModel.m5170id(-2);
        setControllerToStageTo(this.controller.availabilityModel, this.controller);
        this.controller.qualityModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.qualityModel.m5170id(-3);
        setControllerToStageTo(this.controller.qualityModel, this.controller);
        this.controller.timeLeftModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.timeLeftModel.m5170id(-4);
        setControllerToStageTo(this.controller.timeLeftModel, this.controller);
        this.controller.legalDisclaimerModel = new SimpleTextRowEpoxyModel_();
        this.controller.legalDisclaimerModel.m5578id(-5);
        setControllerToStageTo(this.controller.legalDisclaimerModel, this.controller);
        this.controller.searchesModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.searchesModel.m5170id(-6);
        setControllerToStageTo(this.controller.searchesModel, this.controller);
    }
}
