package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ManageListingCheckInGuideController_EpoxyHelper extends ControllerHelper<ManageListingCheckInGuideController> {
    private final ManageListingCheckInGuideController controller;

    public ManageListingCheckInGuideController_EpoxyHelper(ManageListingCheckInGuideController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-1);
        setControllerToStageTo(this.controller.headerRow, this.controller);
        this.controller.stepsCarousel = new CarouselModel_();
        this.controller.stepsCarousel.m4423id(-2);
        setControllerToStageTo(this.controller.stepsCarousel, this.controller);
        this.controller.loader = new EpoxyControllerLoadingModel_();
        this.controller.loader.m4582id(-3);
        setControllerToStageTo(this.controller.loader, this.controller);
    }
}
