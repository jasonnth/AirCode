package com.airbnb.android.host_referrals.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class SentHostReferralsEpoxyController_EpoxyHelper extends ControllerHelper<SentHostReferralsEpoxyController> {
    private final SentHostReferralsEpoxyController controller;

    public SentHostReferralsEpoxyController_EpoxyHelper(SentHostReferralsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.space = new ListSpacerEpoxyModel_();
        this.controller.space.m5050id(-1);
        setControllerToStageTo(this.controller.space, this.controller);
        this.controller.sectionHeaderEpoxyModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.sectionHeaderEpoxyModel.m5170id(-2);
        setControllerToStageTo(this.controller.sectionHeaderEpoxyModel, this.controller);
        this.controller.divider = new SubsectionDividerEpoxyModel_();
        this.controller.divider.m5650id(-3);
        setControllerToStageTo(this.controller.divider, this.controller);
    }
}
