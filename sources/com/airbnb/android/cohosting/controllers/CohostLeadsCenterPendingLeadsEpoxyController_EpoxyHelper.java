package com.airbnb.android.cohosting.controllers;

import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.EmptyStateCardModel_;

public class CohostLeadsCenterPendingLeadsEpoxyController_EpoxyHelper extends ControllerHelper<CohostLeadsCenterPendingLeadsEpoxyController> {
    private final CohostLeadsCenterPendingLeadsEpoxyController controller;

    public CohostLeadsCenterPendingLeadsEpoxyController_EpoxyHelper(CohostLeadsCenterPendingLeadsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.emptyCardModel = new EmptyStateCardModel_();
        this.controller.emptyCardModel.mo11716id(-1);
        setControllerToStageTo(this.controller.emptyCardModel, this.controller);
    }
}
