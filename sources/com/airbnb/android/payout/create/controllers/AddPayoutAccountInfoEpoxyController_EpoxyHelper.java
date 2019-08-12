package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class AddPayoutAccountInfoEpoxyController_EpoxyHelper extends ControllerHelper<AddPayoutAccountInfoEpoxyController> {
    private final AddPayoutAccountInfoEpoxyController controller;

    public AddPayoutAccountInfoEpoxyController_EpoxyHelper(AddPayoutAccountInfoEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarqueeModel.m4534id(-1);
        setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
    }
}
