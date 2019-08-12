package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ChoosePayoutAddressEpoxyController_EpoxyHelper extends ControllerHelper<ChoosePayoutAddressEpoxyController> {
    private final ChoosePayoutAddressEpoxyController controller;

    public ChoosePayoutAddressEpoxyController_EpoxyHelper(ChoosePayoutAddressEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-1);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarqueeModel.m4534id(-2);
        setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
        this.controller.linkActionRowModel = new LinkActionRowEpoxyModel_();
        this.controller.linkActionRowModel.m5038id(-3);
        setControllerToStageTo(this.controller.linkActionRowModel, this.controller);
    }
}
