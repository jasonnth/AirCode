package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.viewcomponents.models.GuestRatingsMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GuestStarRatingBreakdownEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class GuestStarRatingsEpoxyController_EpoxyHelper extends ControllerHelper<GuestStarRatingsEpoxyController> {
    private final GuestStarRatingsEpoxyController controller;

    public GuestStarRatingsEpoxyController_EpoxyHelper(GuestStarRatingsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.guestRatingsMarquee = new GuestRatingsMarqueeEpoxyModel_();
        this.controller.guestRatingsMarquee.m4642id(-1);
        setControllerToStageTo(this.controller.guestRatingsMarquee, this.controller);
        this.controller.linkRow = new LinkActionRowEpoxyModel_();
        this.controller.linkRow.m5038id(-2);
        setControllerToStageTo(this.controller.linkRow, this.controller);
        this.controller.guestStarRatingBreakdown = new GuestStarRatingBreakdownEpoxyModel_();
        this.controller.guestStarRatingBreakdown.m4654id(-3);
        setControllerToStageTo(this.controller.guestStarRatingBreakdown, this.controller);
    }
}
