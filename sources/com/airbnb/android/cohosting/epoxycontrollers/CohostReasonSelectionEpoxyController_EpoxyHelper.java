package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostReasonSelectionEpoxyController_EpoxyHelper extends ControllerHelper<CohostReasonSelectionEpoxyController> {
    private final CohostReasonSelectionEpoxyController controller;

    public CohostReasonSelectionEpoxyController_EpoxyHelper(CohostReasonSelectionEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marquee = new DocumentMarqueeEpoxyModel_();
        this.controller.marquee.m4534id(-1);
        setControllerToStageTo(this.controller.marquee, this.controller);
    }
}
