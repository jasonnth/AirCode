package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class EarlyBirdDiscountsEpoxyController_EpoxyHelper extends ControllerHelper<EarlyBirdDiscountsEpoxyController> {
    private final EarlyBirdDiscountsEpoxyController controller;

    public EarlyBirdDiscountsEpoxyController_EpoxyHelper(EarlyBirdDiscountsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-1);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
