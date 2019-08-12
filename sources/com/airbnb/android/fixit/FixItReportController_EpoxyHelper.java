package com.airbnb.android.fixit;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.ControllerHelper;

public class FixItReportController_EpoxyHelper extends ControllerHelper<FixItReportController> {
    private final FixItReportController controller;

    public FixItReportController_EpoxyHelper(FixItReportController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.header = new DocumentMarqueeEpoxyModel_();
        this.controller.header.m4534id(-1);
        setControllerToStageTo(this.controller.header, this.controller);
        this.controller.loader = new EpoxyControllerLoadingModel_();
        this.controller.loader.m4582id(-2);
        setControllerToStageTo(this.controller.loader, this.controller);
    }
}
