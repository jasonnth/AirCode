package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingPaymentTypeEpoxyController_EpoxyHelper extends ControllerHelper<CohostingPaymentTypeEpoxyController> {
    private final CohostingPaymentTypeEpoxyController controller;

    public CohostingPaymentTypeEpoxyController_EpoxyHelper(CohostingPaymentTypeEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-1);
        setControllerToStageTo(this.controller.headerRow, this.controller);
        this.controller.percentageRow = new ToggleActionRowEpoxyModel_();
        this.controller.percentageRow.m5698id(-2);
        setControllerToStageTo(this.controller.percentageRow, this.controller);
        this.controller.fixedFeeRow = new ToggleActionRowEpoxyModel_();
        this.controller.fixedFeeRow.m5698id(-3);
        setControllerToStageTo(this.controller.fixedFeeRow, this.controller);
    }
}
