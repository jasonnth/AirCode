package com.airbnb.android.fixit;

import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImpactDisplayCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.InfoActionRowModel_;
import com.airbnb.p027n2.components.LabelDocumentMarqueeModel_;

public class FixItItemController_EpoxyHelper extends ControllerHelper<FixItItemController> {
    private final FixItItemController controller;

    public FixItItemController_EpoxyHelper(FixItItemController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.photoProofBasicRow = new BasicRowEpoxyModel_();
        this.controller.photoProofBasicRow.m4351id(-1);
        setControllerToStageTo(this.controller.photoProofBasicRow, this.controller);
        this.controller.commentActionRow = new InfoActionRowModel_();
        this.controller.commentActionRow.mo11716id(-2);
        setControllerToStageTo(this.controller.commentActionRow, this.controller);
        this.controller.itemDetail = new LabelDocumentMarqueeModel_();
        this.controller.itemDetail.mo11716id(-3);
        setControllerToStageTo(this.controller.itemDetail, this.controller);
        this.controller.descriptionRow = new SimpleTextRowEpoxyModel_();
        this.controller.descriptionRow.m5578id(-4);
        setControllerToStageTo(this.controller.descriptionRow, this.controller);
        this.controller.photosRow = new ImpactDisplayCardEpoxyModel_();
        this.controller.photosRow.m4798id(-5);
        setControllerToStageTo(this.controller.photosRow, this.controller);
        this.controller.photoProofActionRow = new InfoActionRowModel_();
        this.controller.photoProofActionRow.mo11716id(-6);
        setControllerToStageTo(this.controller.photoProofActionRow, this.controller);
        this.controller.commentBasicRow = new BasicRowEpoxyModel_();
        this.controller.commentBasicRow.m4351id(-7);
        setControllerToStageTo(this.controller.commentBasicRow, this.controller);
    }
}
