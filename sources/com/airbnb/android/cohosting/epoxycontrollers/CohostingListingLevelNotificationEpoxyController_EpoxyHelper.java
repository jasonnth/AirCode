package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingListingLevelNotificationEpoxyController_EpoxyHelper extends ControllerHelper<CohostingListingLevelNotificationEpoxyController> {
    private final CohostingListingLevelNotificationEpoxyController controller;

    public CohostingListingLevelNotificationEpoxyController_EpoxyHelper(CohostingListingLevelNotificationEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.titleRow = new DocumentMarqueeEpoxyModel_();
        this.controller.titleRow.m4534id(-1);
        setControllerToStageTo(this.controller.titleRow, this.controller);
        this.controller.detailedNotificationToggle = new ToggleActionRowEpoxyModel_();
        this.controller.detailedNotificationToggle.m5698id(-2);
        setControllerToStageTo(this.controller.detailedNotificationToggle, this.controller);
        this.controller.descriptionRow = new SimpleTextRowEpoxyModel_();
        this.controller.descriptionRow.m5578id(-3);
        setControllerToStageTo(this.controller.descriptionRow, this.controller);
        this.controller.listingNotificationSwitchRow = new SwitchRowEpoxyModel_();
        this.controller.listingNotificationSwitchRow.m5674id(-4);
        setControllerToStageTo(this.controller.listingNotificationSwitchRow, this.controller);
        this.controller.monthlyNotificationToggle = new ToggleActionRowEpoxyModel_();
        this.controller.monthlyNotificationToggle.m5698id(-5);
        setControllerToStageTo(this.controller.monthlyNotificationToggle, this.controller);
    }
}
