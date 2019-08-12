package com.airbnb.android.contentframework.adapters;

import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCreationAddPhotoEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class StoryCreationImageCarouselController_EpoxyHelper extends ControllerHelper<StoryCreationImageCarouselController> {
    private final StoryCreationImageCarouselController controller;

    public StoryCreationImageCarouselController_EpoxyHelper(StoryCreationImageCarouselController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.addPhotoEpoxyModel = new StoryCreationAddPhotoEpoxyModel_();
        this.controller.addPhotoEpoxyModel.m4152id(-1);
        setControllerToStageTo(this.controller.addPhotoEpoxyModel, this.controller);
    }
}
