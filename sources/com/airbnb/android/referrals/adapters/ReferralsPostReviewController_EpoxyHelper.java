package com.airbnb.android.referrals.adapters;

import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ReferralsPostReviewController_EpoxyHelper extends ControllerHelper<ReferralsPostReviewController> {
    private final ReferralsPostReviewController controller;

    public ReferralsPostReviewController_EpoxyHelper(ReferralsPostReviewController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.divider = new SubsectionDividerEpoxyModel_();
        this.controller.divider.m5650id(-1);
        setControllerToStageTo(this.controller.divider, this.controller);
        this.controller.header = new InviteMarqueeEpoxyModel_();
        this.controller.header.m1311id(-2);
        setControllerToStageTo(this.controller.header, this.controller);
    }
}
