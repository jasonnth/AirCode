package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostLeadsCenterChoosePackageEpoxyController_EpoxyHelper extends ControllerHelper<CohostLeadsCenterChoosePackageEpoxyController> {
    private final CohostLeadsCenterChoosePackageEpoxyController controller;

    public CohostLeadsCenterChoosePackageEpoxyController_EpoxyHelper(CohostLeadsCenterChoosePackageEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.fullServiceModel = new ToggleActionRowEpoxyModel_();
        this.controller.fullServiceModel.m5698id(-1);
        setControllerToStageTo(this.controller.fullServiceModel, this.controller);
        this.controller.onlineModel = new ToggleActionRowEpoxyModel_();
        this.controller.onlineModel.m5698id(-2);
        setControllerToStageTo(this.controller.onlineModel, this.controller);
        this.controller.onsiteModel = new ToggleActionRowEpoxyModel_();
        this.controller.onsiteModel.m5698id(-3);
        setControllerToStageTo(this.controller.onsiteModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-4);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.linkModel = new LinkActionRowEpoxyModel_();
        this.controller.linkModel.m5038id(-5);
        setControllerToStageTo(this.controller.linkModel, this.controller);
    }
}
