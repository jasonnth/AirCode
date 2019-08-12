package com.airbnb.android.nestedlistings.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class NestedListingsOverviewEpoxyController_EpoxyHelper extends ControllerHelper<NestedListingsOverviewEpoxyController> {
    private final NestedListingsOverviewEpoxyController controller;

    public NestedListingsOverviewEpoxyController_EpoxyHelper(NestedListingsOverviewEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.captionModel = new StandardRowEpoxyModel_();
        this.controller.captionModel.m5602id(-1);
        setControllerToStageTo(this.controller.captionModel, this.controller);
        this.controller.headerModel = new DocumentMarqueeEpoxyModel_();
        this.controller.headerModel.m4534id(-2);
        setControllerToStageTo(this.controller.headerModel, this.controller);
        this.controller.learnMoreModel = new LinkActionRowEpoxyModel_();
        this.controller.learnMoreModel.m5038id(-3);
        setControllerToStageTo(this.controller.learnMoreModel, this.controller);
        this.controller.loadingModel_ = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel_.m4582id(-4);
        setControllerToStageTo(this.controller.loadingModel_, this.controller);
        this.controller.linkMoreModel = new StandardRowEpoxyModel_();
        this.controller.linkMoreModel.m5602id(-5);
        setControllerToStageTo(this.controller.linkMoreModel, this.controller);
    }
}
