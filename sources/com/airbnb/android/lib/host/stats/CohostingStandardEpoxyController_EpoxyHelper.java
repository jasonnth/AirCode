package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.viewcomponents.models.BarRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingStandardEpoxyController_EpoxyHelper extends ControllerHelper<CohostingStandardEpoxyController> {
    private final CohostingStandardEpoxyController controller;

    public CohostingStandardEpoxyController_EpoxyHelper(CohostingStandardEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-1);
        setControllerToStageTo(this.controller.headerRow, this.controller);
        this.controller.noStatsDescriptionRow = new SimpleTextRowEpoxyModel_();
        this.controller.noStatsDescriptionRow.m5578id(-2);
        setControllerToStageTo(this.controller.noStatsDescriptionRow, this.controller);
        this.controller.responseRateRow = new BarRowEpoxyModel_();
        this.controller.responseRateRow.m4339id(-3);
        setControllerToStageTo(this.controller.responseRateRow, this.controller);
        this.controller.statsExplanationRow = new SimpleTextRowEpoxyModel_();
        this.controller.statsExplanationRow.m5578id(-4);
        setControllerToStageTo(this.controller.statsExplanationRow, this.controller);
        this.controller.learnMoreRow = new LinkActionRowEpoxyModel_();
        this.controller.learnMoreRow.m5038id(-5);
        setControllerToStageTo(this.controller.learnMoreRow, this.controller);
    }
}
