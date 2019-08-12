package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class AccountSettingsEpoxyController_EpoxyHelper extends ControllerHelper<AccountSettingsEpoxyController> {
    private final AccountSettingsEpoxyController controller;

    public AccountSettingsEpoxyController_EpoxyHelper(AccountSettingsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.sendFeedbackRow = new StandardRowEpoxyModel_();
        this.controller.sendFeedbackRow.m5602id(-1);
        setControllerToStageTo(this.controller.sendFeedbackRow, this.controller);
        this.controller.spacerRow = new ToolbarSpacerEpoxyModel_();
        this.controller.spacerRow.m5710id(-2);
        setControllerToStageTo(this.controller.spacerRow, this.controller);
        this.controller.switchAccountRow = new StandardRowEpoxyModel_();
        this.controller.switchAccountRow.m5602id(-3);
        setControllerToStageTo(this.controller.switchAccountRow, this.controller);
        this.controller.notificationSettingsRow = new StandardRowEpoxyModel_();
        this.controller.notificationSettingsRow.m5602id(-4);
        setControllerToStageTo(this.controller.notificationSettingsRow, this.controller);
        this.controller.currencySettingsRow = new StandardRowEpoxyModel_();
        this.controller.currencySettingsRow.m5602id(-5);
        setControllerToStageTo(this.controller.currencySettingsRow, this.controller);
        this.controller.logoutRow = new StandardRowEpoxyModel_();
        this.controller.logoutRow.m5602id(-6);
        setControllerToStageTo(this.controller.logoutRow, this.controller);
        this.controller.paymentInfoRow = new StandardRowEpoxyModel_();
        this.controller.paymentInfoRow.m5602id(-7);
        setControllerToStageTo(this.controller.paymentInfoRow, this.controller);
        this.controller.searchSettingsRow = new StandardRowEpoxyModel_();
        this.controller.searchSettingsRow.m5602id(-8);
        setControllerToStageTo(this.controller.searchSettingsRow, this.controller);
        this.controller.payoutSettingsRow = new StandardRowEpoxyModel_();
        this.controller.payoutSettingsRow.m5602id(-9);
        setControllerToStageTo(this.controller.payoutSettingsRow, this.controller);
        this.controller.advancedSettingsRow = new StandardRowEpoxyModel_();
        this.controller.advancedSettingsRow.m5602id(-10);
        setControllerToStageTo(this.controller.advancedSettingsRow, this.controller);
        this.controller.aboutRow = new StandardRowEpoxyModel_();
        this.controller.aboutRow.m5602id(-11);
        setControllerToStageTo(this.controller.aboutRow, this.controller);
    }
}
