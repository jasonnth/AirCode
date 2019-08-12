package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class AboutEpoxyController_EpoxyHelper extends ControllerHelper<AboutEpoxyController> {
    private final AboutEpoxyController controller;

    public AboutEpoxyController_EpoxyHelper(AboutEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.versionRow = new StandardRowEpoxyModel_();
        this.controller.versionRow.m5602id(-1);
        setControllerToStageTo(this.controller.versionRow, this.controller);
        this.controller.spacerRow = new ToolbarSpacerEpoxyModel_();
        this.controller.spacerRow.m5710id(-2);
        setControllerToStageTo(this.controller.spacerRow, this.controller);
        this.controller.paymentTermsOfServiceRow = new StandardRowEpoxyModel_();
        this.controller.paymentTermsOfServiceRow.m5602id(-3);
        setControllerToStageTo(this.controller.paymentTermsOfServiceRow, this.controller);
        this.controller.termsOfServiceRow = new StandardRowEpoxyModel_();
        this.controller.termsOfServiceRow.m5602id(-4);
        setControllerToStageTo(this.controller.termsOfServiceRow, this.controller);
        this.controller.whyHostRow = new StandardRowEpoxyModel_();
        this.controller.whyHostRow.m5602id(-5);
        setControllerToStageTo(this.controller.whyHostRow, this.controller);
        this.controller.privacyPolicyRow = new StandardRowEpoxyModel_();
        this.controller.privacyPolicyRow.m5602id(-6);
        setControllerToStageTo(this.controller.privacyPolicyRow, this.controller);
        this.controller.nonDiscriminationPolicyRow = new StandardRowEpoxyModel_();
        this.controller.nonDiscriminationPolicyRow.m5602id(-7);
        setControllerToStageTo(this.controller.nonDiscriminationPolicyRow, this.controller);
        this.controller.howItWorksRow = new StandardRowEpoxyModel_();
        this.controller.howItWorksRow.m5602id(-8);
        setControllerToStageTo(this.controller.howItWorksRow, this.controller);
    }
}
