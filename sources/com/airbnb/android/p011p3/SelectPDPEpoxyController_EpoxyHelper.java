package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.P3ReviewsRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.decide.select.SelectBulletListModel_;
import com.airbnb.p027n2.components.decide.select.SelectHomeSummaryRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectHostRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectIconBulletRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectMapInterstitialModel_;
import com.airbnb.p027n2.components.decide.select.SelectMarqueeModel_;

/* renamed from: com.airbnb.android.p3.SelectPDPEpoxyController_EpoxyHelper */
public class SelectPDPEpoxyController_EpoxyHelper extends ControllerHelper<SelectPDPEpoxyController> {
    private final SelectPDPEpoxyController controller;

    public SelectPDPEpoxyController_EpoxyHelper(SelectPDPEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.flexibleCancellationTextModel = new TextRowEpoxyModel_();
        this.controller.flexibleCancellationTextModel.m5686id(-1);
        setControllerToStageTo(this.controller.flexibleCancellationTextModel, this.controller);
        this.controller.houseRulesTextModel = new TextRowEpoxyModel_();
        this.controller.houseRulesTextModel.m5686id(-2);
        setControllerToStageTo(this.controller.houseRulesTextModel, this.controller);
        this.controller.selectDescriptionBulletsModel = new SelectBulletListModel_();
        this.controller.selectDescriptionBulletsModel.mo11716id(-3);
        setControllerToStageTo(this.controller.selectDescriptionBulletsModel, this.controller);
        this.controller.hostDetailsLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.hostDetailsLinkModel.m5038id(-4);
        setControllerToStageTo(this.controller.hostDetailsLinkModel, this.controller);
        this.controller.policiesHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.policiesHeaderModel.m5554id(-5);
        setControllerToStageTo(this.controller.policiesHeaderModel, this.controller);
        this.controller.mapHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.mapHeaderModel.m5554id(-6);
        setControllerToStageTo(this.controller.mapHeaderModel, this.controller);
        this.controller.flexibleCancellationLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.flexibleCancellationLinkModel.m5038id(-7);
        setControllerToStageTo(this.controller.flexibleCancellationLinkModel, this.controller);
        this.controller.amenitiesCarouselModel = new CarouselModel_();
        this.controller.amenitiesCarouselModel.m4423id(-8);
        setControllerToStageTo(this.controller.amenitiesCarouselModel, this.controller);
        this.controller.locationBulletRowModel = new SelectIconBulletRowModel_();
        this.controller.locationBulletRowModel.mo11716id(-9);
        setControllerToStageTo(this.controller.locationBulletRowModel, this.controller);
        this.controller.flexibleCancellationHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.flexibleCancellationHeaderModel.m5170id(-10);
        setControllerToStageTo(this.controller.flexibleCancellationHeaderModel, this.controller);
        this.controller.roomsCarouselModel = new CarouselModel_();
        this.controller.roomsCarouselModel.m4423id(-11);
        setControllerToStageTo(this.controller.roomsCarouselModel, this.controller);
        this.controller.hostHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.hostHeaderModel.m5554id(-12);
        setControllerToStageTo(this.controller.hostHeaderModel, this.controller);
        this.controller.reviewsRowEpoxyModel = new P3ReviewsRowEpoxyModel_();
        this.controller.reviewsRowEpoxyModel.m5254id(-13);
        setControllerToStageTo(this.controller.reviewsRowEpoxyModel, this.controller);
        this.controller.selectDescriptionLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.selectDescriptionLinkModel.m5038id(-14);
        setControllerToStageTo(this.controller.selectDescriptionLinkModel, this.controller);
        this.controller.houseRulesHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.houseRulesHeaderModel.m5170id(-15);
        setControllerToStageTo(this.controller.houseRulesHeaderModel, this.controller);
        this.controller.exploreRoomsLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.exploreRoomsLinkModel.m5038id(-16);
        setControllerToStageTo(this.controller.exploreRoomsLinkModel, this.controller);
        this.controller.summaryRowModel = new SelectHomeSummaryRowModel_();
        this.controller.summaryRowModel.mo11716id(-17);
        setControllerToStageTo(this.controller.summaryRowModel, this.controller);
        this.controller.selectDescriptionHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.selectDescriptionHeaderModel.m5554id(-18);
        setControllerToStageTo(this.controller.selectDescriptionHeaderModel, this.controller);
        this.controller.descriptionModel = new TextRowEpoxyModel_();
        this.controller.descriptionModel.m5686id(-19);
        setControllerToStageTo(this.controller.descriptionModel, this.controller);
        this.controller.amenitiesHeaderModel = new SectionHeaderEpoxyModel_();
        this.controller.amenitiesHeaderModel.m5554id(-20);
        setControllerToStageTo(this.controller.amenitiesHeaderModel, this.controller);
        this.controller.marqueeModel = new SelectMarqueeModel_();
        this.controller.marqueeModel.mo11716id(-21);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.mapEpoxyModel = new SelectMapInterstitialModel_();
        this.controller.mapEpoxyModel.mo11716id(-22);
        setControllerToStageTo(this.controller.mapEpoxyModel, this.controller);
        this.controller.tagsRowModel = new TextRowEpoxyModel_();
        this.controller.tagsRowModel.m5686id(-23);
        setControllerToStageTo(this.controller.tagsRowModel, this.controller);
        this.controller.airportLocationBulletRowModel = new SelectIconBulletRowModel_();
        this.controller.airportLocationBulletRowModel.mo11716id(-24);
        setControllerToStageTo(this.controller.airportLocationBulletRowModel, this.controller);
        this.controller.hostRowModel = new SelectHostRowModel_();
        this.controller.hostRowModel.mo11716id(-25);
        setControllerToStageTo(this.controller.hostRowModel, this.controller);
        this.controller.houseRulesLinkModel = new LinkActionRowEpoxyModel_();
        this.controller.houseRulesLinkModel.m5038id(-26);
        setControllerToStageTo(this.controller.houseRulesLinkModel, this.controller);
    }
}
