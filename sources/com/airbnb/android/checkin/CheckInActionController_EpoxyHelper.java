package com.airbnb.android.checkin;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CheckInActionController_EpoxyHelper extends ControllerHelper<CheckInActionController> {
    private final CheckInActionController controller;

    public CheckInActionController_EpoxyHelper(CheckInActionController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.wifiRow = new StandardRowEpoxyModel_();
        this.controller.wifiRow.m5602id(-1);
        setControllerToStageTo(this.controller.wifiRow, this.controller);
        this.controller.header = new DocumentMarqueeEpoxyModel_();
        this.controller.header.m4534id(-2);
        setControllerToStageTo(this.controller.header, this.controller);
        this.controller.contactHostRow = new StandardRowEpoxyModel_();
        this.controller.contactHostRow.m5602id(-3);
        setControllerToStageTo(this.controller.contactHostRow, this.controller);
    }
}
