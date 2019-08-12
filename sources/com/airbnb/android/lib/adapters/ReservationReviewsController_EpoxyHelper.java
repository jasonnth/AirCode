package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ReservationReviewsController_EpoxyHelper extends ControllerHelper<ReservationReviewsController> {
    private final ReservationReviewsController controller;

    public ReservationReviewsController_EpoxyHelper(ReservationReviewsController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-1);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.headerModel = new DocumentMarqueeEpoxyModel_();
        this.controller.headerModel.m4534id(-2);
        setControllerToStageTo(this.controller.headerModel, this.controller);
    }
}
