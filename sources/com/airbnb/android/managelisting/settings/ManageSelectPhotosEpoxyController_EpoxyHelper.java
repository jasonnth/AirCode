package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCellModel_;

public class ManageSelectPhotosEpoxyController_EpoxyHelper extends ControllerHelper<ManageSelectPhotosEpoxyController> {
    private final ManageSelectPhotosEpoxyController controller;

    public ManageSelectPhotosEpoxyController_EpoxyHelper(ManageSelectPhotosEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.coverPhotoRow = new RearrangableLabeledPhotoCellModel_();
        this.controller.coverPhotoRow.mo11716id(-1);
        setControllerToStageTo(this.controller.coverPhotoRow, this.controller);
        this.controller.spacer = new ToolbarSpacerEpoxyModel_();
        this.controller.spacer.m5710id(-2);
        setControllerToStageTo(this.controller.spacer, this.controller);
    }
}
