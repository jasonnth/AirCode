package com.airbnb.android.cohosting.controllers;

import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.EmptyStateCardModel_;

public class CohostLeadsCenterBrowseLeadsEpoxyController_EpoxyHelper extends ControllerHelper<CohostLeadsCenterBrowseLeadsEpoxyController> {
    private final CohostLeadsCenterBrowseLeadsEpoxyController controller;

    public CohostLeadsCenterBrowseLeadsEpoxyController_EpoxyHelper(CohostLeadsCenterBrowseLeadsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.emptyCardModel = new EmptyStateCardModel_();
        this.controller.emptyCardModel.mo11716id(-1);
        setControllerToStageTo(this.controller.emptyCardModel, this.controller);
    }
}
