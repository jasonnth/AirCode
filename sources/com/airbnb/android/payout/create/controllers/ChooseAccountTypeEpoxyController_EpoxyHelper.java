package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ChooseAccountTypeEpoxyController_EpoxyHelper extends ControllerHelper<ChooseAccountTypeEpoxyController> {
    private final ChooseAccountTypeEpoxyController controller;

    public ChooseAccountTypeEpoxyController_EpoxyHelper(ChooseAccountTypeEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.checkingAccountRowModel = new ToggleActionRowEpoxyModel_();
        this.controller.checkingAccountRowModel.m5698id(-1);
        setControllerToStageTo(this.controller.checkingAccountRowModel, this.controller);
        this.controller.savingsAccountRowModel = new ToggleActionRowEpoxyModel_();
        this.controller.savingsAccountRowModel.m5698id(-2);
        setControllerToStageTo(this.controller.savingsAccountRowModel, this.controller);
        this.controller.documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarqueeModel.m4534id(-3);
        setControllerToStageTo(this.controller.documentMarqueeModel, this.controller);
    }
}
