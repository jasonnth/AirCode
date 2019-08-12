package com.airbnb.android.checkin;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LargeIconRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CheckInIntroController_EpoxyHelper extends ControllerHelper<CheckInIntroController> {
    private final CheckInIntroController controller;

    public CheckInIntroController_EpoxyHelper(CheckInIntroController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.visibleSoonTextRow = new SimpleTextRowEpoxyModel_();
        this.controller.visibleSoonTextRow.m5578id(-1);
        setControllerToStageTo(this.controller.visibleSoonTextRow, this.controller);
        this.controller.toolbarSpacer = new ToolbarSpacerEpoxyModel_();
        this.controller.toolbarSpacer.m5710id(-2);
        setControllerToStageTo(this.controller.toolbarSpacer, this.controller);
        this.controller.topPadding = new ListSpacerEpoxyModel_();
        this.controller.topPadding.m5050id(-3);
        setControllerToStageTo(this.controller.topPadding, this.controller);
        this.controller.addressRow = new StandardRowEpoxyModel_();
        this.controller.addressRow.m5602id(-4);
        setControllerToStageTo(this.controller.addressRow, this.controller);
        this.controller.header = new DocumentMarqueeEpoxyModel_();
        this.controller.header.m4534id(-5);
        setControllerToStageTo(this.controller.header, this.controller);
        this.controller.iconRow = new LargeIconRowEpoxyModel_();
        this.controller.iconRow.m5014id(-6);
        setControllerToStageTo(this.controller.iconRow, this.controller);
    }
}
