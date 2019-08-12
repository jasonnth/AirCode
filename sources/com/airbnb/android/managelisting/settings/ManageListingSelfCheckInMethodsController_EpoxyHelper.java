package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ManageListingSelfCheckInMethodsController_EpoxyHelper extends ControllerHelper<ManageListingSelfCheckInMethodsController> {
    private final ManageListingSelfCheckInMethodsController controller;

    public ManageListingSelfCheckInMethodsController_EpoxyHelper(ManageListingSelfCheckInMethodsController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-1);
        setControllerToStageTo(this.controller.headerRow, this.controller);
    }
}
