package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ManageListingAmenityListController_EpoxyHelper extends ControllerHelper<ManageListingAmenityListController> {
    private final ManageListingAmenityListController controller;

    public ManageListingAmenityListController_EpoxyHelper(ManageListingAmenityListController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marquee = new DocumentMarqueeEpoxyModel_();
        this.controller.marquee.m4534id(-1);
        setControllerToStageTo(this.controller.marquee, this.controller);
        this.controller.selectRequiredHeader = new SectionHeaderEpoxyModel_();
        this.controller.selectRequiredHeader.m5554id(-2);
        setControllerToStageTo(this.controller.selectRequiredHeader, this.controller);
        this.controller.otherCategoriesHeader = new SectionHeaderEpoxyModel_();
        this.controller.otherCategoriesHeader.m5554id(-3);
        setControllerToStageTo(this.controller.otherCategoriesHeader, this.controller);
    }
}
