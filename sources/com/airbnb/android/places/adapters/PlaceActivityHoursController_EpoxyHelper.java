package com.airbnb.android.places.adapters;

import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class PlaceActivityHoursController_EpoxyHelper extends ControllerHelper<PlaceActivityHoursController> {
    private final PlaceActivityHoursController controller;

    public PlaceActivityHoursController_EpoxyHelper(PlaceActivityHoursController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.toolbarSpacerModel = new ToolbarSpacerEpoxyModel_();
        this.controller.toolbarSpacerModel.m5710id(-1);
        setControllerToStageTo(this.controller.toolbarSpacerModel, this.controller);
        this.controller.sectionHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.sectionHeaderModel.m5554id(-2);
        setControllerToStageTo(this.controller.sectionHeaderModel, this.controller);
    }
}
