package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class LastMinuteDiscountsEpoxyController_EpoxyHelper extends ControllerHelper<LastMinuteDiscountsEpoxyController> {
    private final LastMinuteDiscountsEpoxyController controller;

    public LastMinuteDiscountsEpoxyController_EpoxyHelper(LastMinuteDiscountsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-1);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
