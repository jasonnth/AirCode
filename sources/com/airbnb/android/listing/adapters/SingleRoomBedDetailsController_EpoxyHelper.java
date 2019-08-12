package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class SingleRoomBedDetailsController_EpoxyHelper extends ControllerHelper<SingleRoomBedDetailsController> {
    private final SingleRoomBedDetailsController controller;

    public SingleRoomBedDetailsController_EpoxyHelper(SingleRoomBedDetailsController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.moreOptionsRow = new LinkActionRowEpoxyModel_();
        this.controller.moreOptionsRow.m5038id(-1);
        setControllerToStageTo(this.controller.moreOptionsRow, this.controller);
        this.controller.header = new DocumentMarqueeEpoxyModel_();
        this.controller.header.m4534id(-2);
        setControllerToStageTo(this.controller.header, this.controller);
    }
}
