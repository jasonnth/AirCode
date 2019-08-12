package com.airbnb.android.places.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StepperRowEpoxyModel_;
import com.airbnb.android.places.viewmodels.ResyRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ResyFragmentController_EpoxyHelper extends ControllerHelper<ResyFragmentController> {
    private final ResyFragmentController controller;

    public ResyFragmentController_EpoxyHelper(ResyFragmentController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.guestsRow = new StepperRowEpoxyModel_();
        this.controller.guestsRow.m5626id(-1);
        setControllerToStageTo(this.controller.guestsRow, this.controller);
        this.controller.resyRow = new ResyRowEpoxyModel_();
        this.controller.resyRow.m6463id(-2);
        setControllerToStageTo(this.controller.resyRow, this.controller);
        this.controller.dateRow = new StandardRowEpoxyModel_();
        this.controller.dateRow.m5602id(-3);
        setControllerToStageTo(this.controller.dateRow, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-4);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
