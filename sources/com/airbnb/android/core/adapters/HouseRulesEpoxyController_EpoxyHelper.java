package com.airbnb.android.core.adapters;

import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class HouseRulesEpoxyController_EpoxyHelper extends ControllerHelper<HouseRulesEpoxyController> {
    private final HouseRulesEpoxyController controller;

    public HouseRulesEpoxyController_EpoxyHelper(HouseRulesEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.homesNotHotelsHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.homesNotHotelsHeaderModel.m5170id(-1);
        setControllerToStageTo(this.controller.homesNotHotelsHeaderModel, this.controller);
        this.controller.houseRulesModel = new TextRowEpoxyModel_();
        this.controller.houseRulesModel.m5686id(-2);
        setControllerToStageTo(this.controller.houseRulesModel, this.controller);
        this.controller.marqueeEpoxyModel = new KickerMarqueeEpoxyModel_();
        this.controller.marqueeEpoxyModel.m4978id(-3);
        setControllerToStageTo(this.controller.marqueeEpoxyModel, this.controller);
        this.controller.expectationsHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.expectationsHeaderModel.m5170id(-4);
        setControllerToStageTo(this.controller.expectationsHeaderModel, this.controller);
        this.controller.houseRulesDividerModel = new SubsectionDividerEpoxyModel_();
        this.controller.houseRulesDividerModel.m5650id(-5);
        setControllerToStageTo(this.controller.houseRulesDividerModel, this.controller);
        this.controller.translateLinkModel = new MicroRowEpoxyModel_();
        this.controller.translateLinkModel.m5158id(-6);
        setControllerToStageTo(this.controller.translateLinkModel, this.controller);
    }
}
