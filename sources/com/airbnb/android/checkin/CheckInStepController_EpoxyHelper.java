package com.airbnb.android.checkin;

import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GuideImageMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CheckInStepController_EpoxyHelper extends ControllerHelper<CheckInStepController> {
    private final CheckInStepController controller;

    public CheckInStepController_EpoxyHelper(CheckInStepController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.translateRow = new BasicRowEpoxyModel_();
        this.controller.translateRow.m4351id(-1);
        setControllerToStageTo(this.controller.translateRow, this.controller);
        this.controller.stepInstructionNote = new SimpleTextRowEpoxyModel_();
        this.controller.stepInstructionNote.m5578id(-2);
        setControllerToStageTo(this.controller.stepInstructionNote, this.controller);
        this.controller.noteTopSpaceRow = new ListSpacerEpoxyModel_();
        this.controller.noteTopSpaceRow.m5050id(-3);
        setControllerToStageTo(this.controller.noteTopSpaceRow, this.controller);
        this.controller.toolbarSpace = new ToolbarSpacerEpoxyModel_();
        this.controller.toolbarSpace.m5710id(-4);
        setControllerToStageTo(this.controller.toolbarSpace, this.controller);
        this.controller.imageMarquee = new GuideImageMarqueeEpoxyModel_();
        this.controller.imageMarquee.m4666id(-5);
        setControllerToStageTo(this.controller.imageMarquee, this.controller);
    }
}
