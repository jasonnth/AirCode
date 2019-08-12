package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ChoosePayoutMethodEpoxyController_EpoxyHelper extends ControllerHelper<ChoosePayoutMethodEpoxyController> {
    private final ChoosePayoutMethodEpoxyController controller;

    public ChoosePayoutMethodEpoxyController_EpoxyHelper(ChoosePayoutMethodEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.timingAndFeeRowModel = new SimpleTextRowEpoxyModel_();
        this.controller.timingAndFeeRowModel.m5578id(-1);
        setControllerToStageTo(this.controller.timingAndFeeRowModel, this.controller);
        this.controller.payoutMethodFeeRowModel = new BasicRowEpoxyModel_();
        this.controller.payoutMethodFeeRowModel.m4351id(-2);
        setControllerToStageTo(this.controller.payoutMethodFeeRowModel, this.controller);
        this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarqueeModel.m4534id(-3);
        setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
        this.controller.loaderModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderModel.m4582id(-4);
        setControllerToStageTo(this.controller.loaderModel, this.controller);
    }
}
