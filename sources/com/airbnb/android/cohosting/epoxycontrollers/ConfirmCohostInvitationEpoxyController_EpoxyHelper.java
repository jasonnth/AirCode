package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.HeroMarqueeEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ConfirmCohostInvitationEpoxyController_EpoxyHelper extends ControllerHelper<ConfirmCohostInvitationEpoxyController> {
    private final ConfirmCohostInvitationEpoxyController controller;

    public ConfirmCohostInvitationEpoxyController_EpoxyHelper(ConfirmCohostInvitationEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.confirmationSection = new HeroMarqueeEpoxyModel_();
        this.controller.confirmationSection.m4690id(-1);
        setControllerToStageTo(this.controller.confirmationSection, this.controller);
    }
}
