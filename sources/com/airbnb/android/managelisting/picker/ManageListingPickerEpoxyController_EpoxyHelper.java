package com.airbnb.android.managelisting.picker;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ManageListingPickerEpoxyController_EpoxyHelper extends ControllerHelper<ManageListingPickerEpoxyController> {
    private final ManageListingPickerEpoxyController controller;

    public ManageListingPickerEpoxyController_EpoxyHelper(ManageListingPickerEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-1);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-2);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.createListingModel = new StandardRowEpoxyModel_();
        this.controller.createListingModel.m5602id(-3);
        setControllerToStageTo(this.controller.createListingModel, this.controller);
    }
}
