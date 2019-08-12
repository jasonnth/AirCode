package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class AdvancedSettingsEpoxyController_EpoxyHelper extends ControllerHelper<AdvancedSettingsEpoxyController> {
    private final AdvancedSettingsEpoxyController controller;

    public AdvancedSettingsEpoxyController_EpoxyHelper(AdvancedSettingsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.fontOverrideSettingsRow = new SwitchRowEpoxyModel_();
        this.controller.fontOverrideSettingsRow.m5674id(-1);
        setControllerToStageTo(this.controller.fontOverrideSettingsRow, this.controller);
        this.controller.shakeToSendFeedbackClickedRow = new SwitchRowEpoxyModel_();
        this.controller.shakeToSendFeedbackClickedRow.m5674id(-2);
        setControllerToStageTo(this.controller.shakeToSendFeedbackClickedRow, this.controller);
        this.controller.spacerRow = new ToolbarSpacerEpoxyModel_();
        this.controller.spacerRow.m5710id(-3);
        setControllerToStageTo(this.controller.spacerRow, this.controller);
        this.controller.bandWidthModeChangedRow = new StandardRowEpoxyModel_();
        this.controller.bandWidthModeChangedRow.m5602id(-4);
        setControllerToStageTo(this.controller.bandWidthModeChangedRow, this.controller);
    }
}
