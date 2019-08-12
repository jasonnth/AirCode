package com.airbnb.android.contentframework.adapters;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class StoryCreationPlaceSearchEpoxyController_EpoxyHelper extends ControllerHelper<StoryCreationPlaceSearchEpoxyController> {
    private final StoryCreationPlaceSearchEpoxyController controller;

    public StoryCreationPlaceSearchEpoxyController_EpoxyHelper(StoryCreationPlaceSearchEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.emptyResultEpoxyModel = new SimpleTextRowEpoxyModel_();
        this.controller.emptyResultEpoxyModel.m5578id(-1);
        setControllerToStageTo(this.controller.emptyResultEpoxyModel, this.controller);
        this.controller.loadingRowEpoxyModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingRowEpoxyModel.m4582id(-2);
        setControllerToStageTo(this.controller.loadingRowEpoxyModel, this.controller);
    }
}
