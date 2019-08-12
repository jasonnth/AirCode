package com.airbnb.android.contentframework.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class StoryCreationPickTripController_EpoxyHelper extends ControllerHelper<StoryCreationPickTripController> {
    private final StoryCreationPickTripController controller;

    public StoryCreationPickTripController_EpoxyHelper(StoryCreationPickTripController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.showMoreModel = new LinkActionRowEpoxyModel_();
        this.controller.showMoreModel.m5038id(-1);
        setControllerToStageTo(this.controller.showMoreModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-2);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.loaderModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderModel.m4582id(-3);
        setControllerToStageTo(this.controller.loaderModel, this.controller);
    }
}
