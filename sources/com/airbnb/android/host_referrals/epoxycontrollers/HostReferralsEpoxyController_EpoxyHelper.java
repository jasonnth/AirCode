package com.airbnb.android.host_referrals.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.referrals.views.SingleButtonRow_;
import com.airbnb.epoxy.ControllerHelper;

public class HostReferralsEpoxyController_EpoxyHelper extends ControllerHelper<HostReferralsEpoxyController> {
    private final HostReferralsEpoxyController controller;

    public HostReferralsEpoxyController_EpoxyHelper(HostReferralsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.button = new SingleButtonRow_();
        this.controller.button.m1359id(-1);
        setControllerToStageTo(this.controller.button, this.controller);
        this.controller.terms = new BasicRowEpoxyModel_();
        this.controller.terms.m4351id(-2);
        setControllerToStageTo(this.controller.terms, this.controller);
        this.controller.credit = new BasicRowEpoxyModel_();
        this.controller.credit.m4351id(-3);
        setControllerToStageTo(this.controller.credit, this.controller);
        this.controller.title = new DocumentMarqueeEpoxyModel_();
        this.controller.title.m4534id(-4);
        setControllerToStageTo(this.controller.title, this.controller);
        this.controller.divider = new SubsectionDividerEpoxyModel_();
        this.controller.divider.m5650id(-5);
        setControllerToStageTo(this.controller.divider, this.controller);
    }
}
