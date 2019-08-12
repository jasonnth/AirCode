package com.airbnb.android.payout.manage.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class EditPayoutEpoxyController_EpoxyHelper extends ControllerHelper<EditPayoutEpoxyController> {
    private final EditPayoutEpoxyController controller;

    public EditPayoutEpoxyController_EpoxyHelper(EditPayoutEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarqueeModel.m4534id(-1);
        setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
        this.controller.linkActionRowModel = new LinkActionRowEpoxyModel_();
        this.controller.linkActionRowModel.m5038id(-2);
        setControllerToStageTo(this.controller.linkActionRowModel, this.controller);
        this.controller.loaderModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderModel.m4582id(-3);
        setControllerToStageTo(this.controller.loaderModel, this.controller);
    }
}
