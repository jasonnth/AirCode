package com.airbnb.android.collections.adapters;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PromotionMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class SelectInvitationListingPickerController_EpoxyHelper extends ControllerHelper<SelectInvitationListingPickerController> {
    private final SelectInvitationListingPickerController controller;

    public SelectInvitationListingPickerController_EpoxyHelper(SelectInvitationListingPickerController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.spacer = new ToolbarSpacerEpoxyModel_();
        this.controller.spacer.m5710id(-1);
        setControllerToStageTo(this.controller.spacer, this.controller);
        this.controller.marquee = new PromotionMarqueeEpoxyModel_();
        this.controller.marquee.m5338id(-2);
        setControllerToStageTo(this.controller.marquee, this.controller);
        this.controller.optionalSpace = new ListSpacerEpoxyModel_();
        this.controller.optionalSpace.m5050id(-3);
        setControllerToStageTo(this.controller.optionalSpace, this.controller);
        this.controller.loaderRow = new EpoxyControllerLoadingModel_();
        this.controller.loaderRow.m4582id(-4);
        setControllerToStageTo(this.controller.loaderRow, this.controller);
        this.controller.actionRow = new SimpleTextRowEpoxyModel_();
        this.controller.actionRow.m5578id(-5);
        setControllerToStageTo(this.controller.actionRow, this.controller);
    }
}
