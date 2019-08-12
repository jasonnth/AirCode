package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.HeroMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingInvitationErrorEpoxyController_EpoxyHelper extends ControllerHelper<CohostingInvitationErrorEpoxyController> {
    private final CohostingInvitationErrorEpoxyController controller;

    public CohostingInvitationErrorEpoxyController_EpoxyHelper(CohostingInvitationErrorEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.marquee = new HeroMarqueeEpoxyModel_();
        this.controller.marquee.m4690id(-1);
        setControllerToStageTo(this.controller.marquee, this.controller);
    }
}
