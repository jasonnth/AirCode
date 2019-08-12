package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

/* renamed from: com.airbnb.android.p3.HomeTourGalleryEpoxyController_EpoxyHelper */
public class HomeTourGalleryEpoxyController_EpoxyHelper extends ControllerHelper<HomeTourGalleryEpoxyController> {
    private final HomeTourGalleryEpoxyController controller;

    public HomeTourGalleryEpoxyController_EpoxyHelper(HomeTourGalleryEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.homeTitleHeaderModel = new DocumentMarqueeEpoxyModel_();
        this.controller.homeTitleHeaderModel.m4534id(-1);
        setControllerToStageTo(this.controller.homeTitleHeaderModel, this.controller);
        this.controller.homeDescriptionModel = new TextRowEpoxyModel_();
        this.controller.homeDescriptionModel.m5686id(-2);
        setControllerToStageTo(this.controller.homeDescriptionModel, this.controller);
    }
}
