package com.airbnb.android.core.adapters;

import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.NuxCoverCardModel_;

public class LottieNuxCoverPageController_EpoxyHelper extends ControllerHelper<LottieNuxCoverPageController> {
    private final LottieNuxCoverPageController controller;

    public LottieNuxCoverPageController_EpoxyHelper(LottieNuxCoverPageController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.coverCardModel = new NuxCoverCardModel_();
        this.controller.coverCardModel.mo11716id(-1);
        setControllerToStageTo(this.controller.coverCardModel, this.controller);
        this.controller.spacer = new ToolbarSpacerEpoxyModel_();
        this.controller.spacer.m5710id(-2);
        setControllerToStageTo(this.controller.spacer, this.controller);
    }
}
