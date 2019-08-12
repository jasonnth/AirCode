package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImageWithButtonRowExpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostUpsellEpoxyController_EpoxyHelper extends ControllerHelper<CohostUpsellEpoxyController> {
    private final CohostUpsellEpoxyController controller;

    public CohostUpsellEpoxyController_EpoxyHelper(CohostUpsellEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.textRowEpoxyModel = new TextRowEpoxyModel_();
        this.controller.textRowEpoxyModel.m5686id(-1);
        setControllerToStageTo(this.controller.textRowEpoxyModel, this.controller);
        this.controller.sectionHeaderEpoxyModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.sectionHeaderEpoxyModel.m5170id(-2);
        setControllerToStageTo(this.controller.sectionHeaderEpoxyModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-3);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.imageWithButtonRowExpoxyModel = new ImageWithButtonRowExpoxyModel_();
        this.controller.imageWithButtonRowExpoxyModel.m4786id(-4);
        setControllerToStageTo(this.controller.imageWithButtonRowExpoxyModel, this.controller);
        this.controller.learnMoreLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.learnMoreLinkModel.m5038id(-5);
        setControllerToStageTo(this.controller.learnMoreLinkModel, this.controller);
    }
}
