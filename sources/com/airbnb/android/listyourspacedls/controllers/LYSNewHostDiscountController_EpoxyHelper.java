package com.airbnb.android.listyourspacedls.controllers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class LYSNewHostDiscountController_EpoxyHelper extends ControllerHelper<LYSNewHostDiscountController> {
    private final LYSNewHostDiscountController controller;

    public LYSNewHostDiscountController_EpoxyHelper(LYSNewHostDiscountController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.discountRow = new ToggleActionRowEpoxyModel_();
        this.controller.discountRow.m5698id(-1);
        setControllerToStageTo(this.controller.discountRow, this.controller);
        this.controller.noDiscountRow = new ToggleActionRowEpoxyModel_();
        this.controller.noDiscountRow.m5698id(-2);
        setControllerToStageTo(this.controller.noDiscountRow, this.controller);
        this.controller.header = new DocumentMarqueeEpoxyModel_();
        this.controller.header.m4534id(-3);
        setControllerToStageTo(this.controller.header, this.controller);
    }
}
