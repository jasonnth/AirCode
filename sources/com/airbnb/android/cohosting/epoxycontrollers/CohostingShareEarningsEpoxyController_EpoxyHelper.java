package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingShareEarningsEpoxyController_EpoxyHelper extends ControllerHelper<CohostingShareEarningsEpoxyController> {
    private final CohostingShareEarningsEpoxyController controller;

    public CohostingShareEarningsEpoxyController_EpoxyHelper(CohostingShareEarningsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-1);
        setControllerToStageTo(this.controller.headerRow, this.controller);
        this.controller.warningRow = new SimpleTextRowEpoxyModel_();
        this.controller.warningRow.m5578id(-2);
        setControllerToStageTo(this.controller.warningRow, this.controller);
        this.controller.terms = new SimpleTextRowEpoxyModel_();
        this.controller.terms.m5578id(-3);
        setControllerToStageTo(this.controller.terms, this.controller);
        this.controller.shareEarningsIntro = new SimpleTextRowEpoxyModel_();
        this.controller.shareEarningsIntro.m5578id(-4);
        setControllerToStageTo(this.controller.shareEarningsIntro, this.controller);
    }
}
