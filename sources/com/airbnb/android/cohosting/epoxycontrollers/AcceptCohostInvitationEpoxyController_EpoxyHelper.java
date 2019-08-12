package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.FixedActionFooterEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTitleContentRowModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UserDetailsActionRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.ListingInfoRowModel_;

public class AcceptCohostInvitationEpoxyController_EpoxyHelper extends ControllerHelper<AcceptCohostInvitationEpoxyController> {
    private final AcceptCohostInvitationEpoxyController controller;

    public AcceptCohostInvitationEpoxyController_EpoxyHelper(AcceptCohostInvitationEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.maxFeeRow = new StandardRowEpoxyModel_();
        this.controller.maxFeeRow.m5602id(-1);
        setControllerToStageTo(this.controller.maxFeeRow, this.controller);
        this.controller.headerRow = new DocumentMarqueeEpoxyModel_();
        this.controller.headerRow.m4534id(-2);
        setControllerToStageTo(this.controller.headerRow, this.controller);
        this.controller.listingHeaderRow = new SectionHeaderEpoxyModel_();
        this.controller.listingHeaderRow.m5554id(-3);
        setControllerToStageTo(this.controller.listingHeaderRow, this.controller);
        this.controller.cohostFirstFunctionRow = new SimpleTitleContentRowModel_();
        this.controller.cohostFirstFunctionRow.m5590id(-4);
        setControllerToStageTo(this.controller.cohostFirstFunctionRow, this.controller);
        this.controller.cleaningFeeRow = new StandardRowEpoxyModel_();
        this.controller.cleaningFeeRow.m5602id(-5);
        setControllerToStageTo(this.controller.cleaningFeeRow, this.controller);
        this.controller.userRow = new UserDetailsActionRowEpoxyModel_();
        this.controller.userRow.m5758id(-6);
        setControllerToStageTo(this.controller.userRow, this.controller);
        this.controller.cohostSecondFunctionRow = new SimpleTitleContentRowModel_();
        this.controller.cohostSecondFunctionRow.m5590id(-7);
        setControllerToStageTo(this.controller.cohostSecondFunctionRow, this.controller);
        this.controller.minFeeRow = new StandardRowEpoxyModel_();
        this.controller.minFeeRow.m5602id(-8);
        setControllerToStageTo(this.controller.minFeeRow, this.controller);
        this.controller.cohostFourthFunctionRow = new SimpleTitleContentRowModel_();
        this.controller.cohostFourthFunctionRow.m5590id(-9);
        setControllerToStageTo(this.controller.cohostFourthFunctionRow, this.controller);
        this.controller.cohostThirdFunctionRow = new SimpleTitleContentRowModel_();
        this.controller.cohostThirdFunctionRow.m5590id(-10);
        setControllerToStageTo(this.controller.cohostThirdFunctionRow, this.controller);
        this.controller.feesHeaderRow = new SectionHeaderEpoxyModel_();
        this.controller.feesHeaderRow.m5554id(-11);
        setControllerToStageTo(this.controller.feesHeaderRow, this.controller);
        this.controller.hostingFeeRow = new StandardRowEpoxyModel_();
        this.controller.hostingFeeRow.m5602id(-12);
        setControllerToStageTo(this.controller.hostingFeeRow, this.controller);
        this.controller.feeExplanationRow = new SimpleTextRowEpoxyModel_();
        this.controller.feeExplanationRow.m5578id(-13);
        setControllerToStageTo(this.controller.feeExplanationRow, this.controller);
        this.controller.acceptButton = new FixedActionFooterEpoxyModel_();
        this.controller.acceptButton.m4606id(-14);
        setControllerToStageTo(this.controller.acceptButton, this.controller);
        this.controller.listingMap = new MapInterstitialEpoxyModel_();
        this.controller.listingMap.m5122id(-15);
        setControllerToStageTo(this.controller.listingMap, this.controller);
        this.controller.cohostFunctionIntroHeaderSection = new SectionHeaderEpoxyModel_();
        this.controller.cohostFunctionIntroHeaderSection.m5554id(-16);
        setControllerToStageTo(this.controller.cohostFunctionIntroHeaderSection, this.controller);
        this.controller.listingInfoRow = new ListingInfoRowModel_();
        this.controller.listingInfoRow.mo11716id(-17);
        setControllerToStageTo(this.controller.listingInfoRow, this.controller);
    }
}
