package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.MultiLineSplitRowModel_;

public class CohostLeadsCenterWhatYouCanEarnEpoxyController_EpoxyHelper extends ControllerHelper<CohostLeadsCenterWhatYouCanEarnEpoxyController> {
    private final CohostLeadsCenterWhatYouCanEarnEpoxyController controller;

    public CohostLeadsCenterWhatYouCanEarnEpoxyController_EpoxyHelper(CohostLeadsCenterWhatYouCanEarnEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.earningSummaryModel = new SectionHeaderEpoxyModel_();
        this.controller.earningSummaryModel.m5554id(-1);
        setControllerToStageTo(this.controller.earningSummaryModel, this.controller);
        this.controller.locationModel = new MultiLineSplitRowModel_();
        this.controller.locationModel.mo11716id(-2);
        setControllerToStageTo(this.controller.locationModel, this.controller);
        this.controller.disclaimerModel = new SimpleTextRowEpoxyModel_();
        this.controller.disclaimerModel.m5578id(-3);
        setControllerToStageTo(this.controller.disclaimerModel, this.controller);
        this.controller.roomCapacityModel = new MultiLineSplitRowModel_();
        this.controller.roomCapacityModel.mo11716id(-4);
        setControllerToStageTo(this.controller.roomCapacityModel, this.controller);
        this.controller.roomTypeModel = new MultiLineSplitRowModel_();
        this.controller.roomTypeModel.mo11716id(-5);
        setControllerToStageTo(this.controller.roomTypeModel, this.controller);
        this.controller.lengthOfHostingModel = new MultiLineSplitRowModel_();
        this.controller.lengthOfHostingModel.mo11716id(-6);
        setControllerToStageTo(this.controller.lengthOfHostingModel, this.controller);
        this.controller.hostingFeeModel = new MultiLineSplitRowModel_();
        this.controller.hostingFeeModel.mo11716id(-7);
        setControllerToStageTo(this.controller.hostingFeeModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-8);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
