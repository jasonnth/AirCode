package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class MultiDayPriceTipsEpoxyController_EpoxyHelper extends ControllerHelper<MultiDayPriceTipsEpoxyController> {
    private final MultiDayPriceTipsEpoxyController controller;

    public MultiDayPriceTipsEpoxyController_EpoxyHelper(MultiDayPriceTipsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.documentMarquee = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarquee.m4534id(-1);
        setControllerToStageTo(this.controller.documentMarquee, this.controller);
    }
}
