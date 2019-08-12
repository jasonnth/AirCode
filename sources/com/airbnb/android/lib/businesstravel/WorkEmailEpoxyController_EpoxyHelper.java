package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class WorkEmailEpoxyController_EpoxyHelper extends ControllerHelper<WorkEmailEpoxyController> {
    private final WorkEmailEpoxyController controller;

    public WorkEmailEpoxyController_EpoxyHelper(WorkEmailEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.documentMarquee = new DocumentMarqueeEpoxyModel_();
        this.controller.documentMarquee.m4534id(-1);
        setControllerToStageTo(this.controller.documentMarquee, this.controller);
        this.controller.inlineInputRow = new InlineInputRowEpoxyModel_();
        this.controller.inlineInputRow.m4882id(-2);
        setControllerToStageTo(this.controller.inlineInputRow, this.controller);
    }
}
