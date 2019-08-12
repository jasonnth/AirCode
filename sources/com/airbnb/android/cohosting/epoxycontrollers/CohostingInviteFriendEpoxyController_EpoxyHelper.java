package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputWithContactPickerRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineMultilineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class CohostingInviteFriendEpoxyController_EpoxyHelper extends ControllerHelper<CohostingInviteFriendEpoxyController> {
    private final CohostingInviteFriendEpoxyController controller;

    public CohostingInviteFriendEpoxyController_EpoxyHelper(CohostingInviteFriendEpoxyController controller2) {
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
        this.controller.messageRow = new InlineMultilineInputRowEpoxyModel_();
        this.controller.messageRow.m4906id(-4);
        setControllerToStageTo(this.controller.messageRow, this.controller);
        this.controller.emailRow = new InlineInputWithContactPickerRowEpoxyModel_();
        this.controller.emailRow.m4894id(-5);
        setControllerToStageTo(this.controller.emailRow, this.controller);
        this.controller.shareEarningsHeader = new SectionHeaderEpoxyModel_();
        this.controller.shareEarningsHeader.m5554id(-6);
        setControllerToStageTo(this.controller.shareEarningsHeader, this.controller);
        this.controller.shareEarningsIntro = new SimpleTextRowEpoxyModel_();
        this.controller.shareEarningsIntro.m5578id(-7);
        setControllerToStageTo(this.controller.shareEarningsIntro, this.controller);
    }
}
