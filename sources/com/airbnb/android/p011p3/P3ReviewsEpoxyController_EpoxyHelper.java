package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeStarRatingBreakdownEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ReviewMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

/* renamed from: com.airbnb.android.p3.P3ReviewsEpoxyController_EpoxyHelper */
public class P3ReviewsEpoxyController_EpoxyHelper extends ControllerHelper<P3ReviewsEpoxyController> {
    private final P3ReviewsEpoxyController controller;

    public P3ReviewsEpoxyController_EpoxyHelper(P3ReviewsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loaderEpoxyModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderEpoxyModel.m4582id(-1);
        setControllerToStageTo(this.controller.loaderEpoxyModel, this.controller);
        this.controller.reviewMarqueeEpoxyModel = new ReviewMarqueeEpoxyModel_();
        this.controller.reviewMarqueeEpoxyModel.m5470id(-2);
        setControllerToStageTo(this.controller.reviewMarqueeEpoxyModel, this.controller);
        this.controller.starBreakdownEpoxyModel = new HomeStarRatingBreakdownEpoxyModel_();
        this.controller.starBreakdownEpoxyModel.m4774id(-3);
        setControllerToStageTo(this.controller.starBreakdownEpoxyModel, this.controller);
    }
}
