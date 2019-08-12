package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class LengthOfStayDiscountsEpoxyController_EpoxyHelper extends ControllerHelper<LengthOfStayDiscountsEpoxyController> {
    private final LengthOfStayDiscountsEpoxyController controller;

    public LengthOfStayDiscountsEpoxyController_EpoxyHelper(LengthOfStayDiscountsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-1);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
