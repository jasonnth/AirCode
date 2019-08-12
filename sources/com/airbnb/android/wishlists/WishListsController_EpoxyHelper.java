package com.airbnb.android.wishlists;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class WishListsController_EpoxyHelper extends ControllerHelper<WishListsController> {
    private final WishListsController controller;

    public WishListsController_EpoxyHelper(WishListsController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-1);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.emptyMessageModel = new SimpleTextRowEpoxyModel_();
        this.controller.emptyMessageModel.m5578id(-2);
        setControllerToStageTo(this.controller.emptyMessageModel, this.controller);
        this.controller.startExploringLinkRow = new LinkActionRowEpoxyModel_();
        this.controller.startExploringLinkRow.m5038id(-3);
        setControllerToStageTo(this.controller.startExploringLinkRow, this.controller);
        this.controller.marqueeEpoxyModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeEpoxyModel.m4534id(-4);
        setControllerToStageTo(this.controller.marqueeEpoxyModel, this.controller);
    }
}
