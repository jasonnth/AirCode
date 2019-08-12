package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostLeadsCenterSendQuoteEpoxyController_EpoxyHelper extends ControllerHelper<CohostLeadsCenterSendQuoteEpoxyController> {
    private final CohostLeadsCenterSendQuoteEpoxyController controller;

    public CohostLeadsCenterSendQuoteEpoxyController_EpoxyHelper(CohostLeadsCenterSendQuoteEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.earningsRow = new StandardRowEpoxyModel_();
        this.controller.earningsRow.m5602id(-1);
        setControllerToStageTo(this.controller.earningsRow, this.controller);
        this.controller.notesRow = new StandardRowEpoxyModel_();
        this.controller.notesRow.m5602id(-2);
        setControllerToStageTo(this.controller.notesRow, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-3);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.packageRow = new StandardRowEpoxyModel_();
        this.controller.packageRow.m5602id(-4);
        setControllerToStageTo(this.controller.packageRow, this.controller);
    }
}
