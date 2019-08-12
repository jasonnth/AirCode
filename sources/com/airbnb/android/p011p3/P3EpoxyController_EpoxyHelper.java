package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.BedDetailsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeAmenitiesEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeHighlightsEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingDescriptionEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.P3ReviewsRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UrgencyEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

/* renamed from: com.airbnb.android.p3.P3EpoxyController_EpoxyHelper */
public class P3EpoxyController_EpoxyHelper extends ControllerHelper<P3EpoxyController> {
    private final P3EpoxyController controller;

    public P3EpoxyController_EpoxyHelper(P3EpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.reportListingModel = new StandardRowEpoxyModel_();
        this.controller.reportListingModel.m5602id(-1);
        setControllerToStageTo(this.controller.reportListingModel, this.controller);
        this.controller.superHostModel = new StandardRowEpoxyModel_();
        this.controller.superHostModel.m5602id(-2);
        setControllerToStageTo(this.controller.superHostModel, this.controller);
        this.controller.checkInTimeModel = new StandardRowEpoxyModel_();
        this.controller.checkInTimeModel.m5602id(-3);
        setControllerToStageTo(this.controller.checkInTimeModel, this.controller);
        this.controller.cancellationModel = new StandardRowEpoxyModel_();
        this.controller.cancellationModel.m5602id(-4);
        setControllerToStageTo(this.controller.cancellationModel, this.controller);
        this.controller.marqueeModel = new HomeMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4750id(-5);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.additionalPricesModel = new StandardRowEpoxyModel_();
        this.controller.additionalPricesModel.m5602id(-6);
        setControllerToStageTo(this.controller.additionalPricesModel, this.controller);
        this.controller.refundPolicyModel = new StandardRowEpoxyModel_();
        this.controller.refundPolicyModel.m5602id(-7);
        setControllerToStageTo(this.controller.refundPolicyModel, this.controller);
        this.controller.checkOutTimeModel = new StandardRowEpoxyModel_();
        this.controller.checkOutTimeModel.m5602id(-8);
        setControllerToStageTo(this.controller.checkOutTimeModel, this.controller);
        this.controller.availabilityCalendarModel = new StandardRowEpoxyModel_();
        this.controller.availabilityCalendarModel.m5602id(-9);
        setControllerToStageTo(this.controller.availabilityCalendarModel, this.controller);
        this.controller.similarListingsModel = new ListingsTrayEpoxyModel_();
        this.controller.similarListingsModel.m5098id(-10);
        setControllerToStageTo(this.controller.similarListingsModel, this.controller);
        this.controller.minNightsAndPetsModel = new MicroRowEpoxyModel_();
        this.controller.minNightsAndPetsModel.m5158id(-11);
        setControllerToStageTo(this.controller.minNightsAndPetsModel, this.controller);
        this.controller.reportListingLoader = new EpoxyControllerLoadingModel_();
        this.controller.reportListingLoader.m4582id(-12);
        setControllerToStageTo(this.controller.reportListingLoader, this.controller);
        this.controller.listingAmenitiesModel = new HomeAmenitiesEpoxyModel_();
        this.controller.listingAmenitiesModel.m4702id(-13);
        setControllerToStageTo(this.controller.listingAmenitiesModel, this.controller);
        this.controller.listingHighlightsModel = new HomeHighlightsEpoxyModel_();
        this.controller.listingHighlightsModel.m4738id(-14);
        setControllerToStageTo(this.controller.listingHighlightsModel, this.controller);
        this.controller.largeReviewRowModel = new P3ReviewsRowEpoxyModel_();
        this.controller.largeReviewRowModel.m5254id(-15);
        setControllerToStageTo(this.controller.largeReviewRowModel, this.controller);
        this.controller.viewGuidebookModel = new StandardRowEpoxyModel_();
        this.controller.viewGuidebookModel.m5602id(-16);
        setControllerToStageTo(this.controller.viewGuidebookModel, this.controller);
        this.controller.contactHostModel = new StandardRowEpoxyModel_();
        this.controller.contactHostModel.m5602id(-17);
        setControllerToStageTo(this.controller.contactHostModel, this.controller);
        this.controller.mapInterstitialModel = new P3MapInterstitialModel_();
        this.controller.mapInterstitialModel.m6311id(-18);
        setControllerToStageTo(this.controller.mapInterstitialModel, this.controller);
        this.controller.listingDescriptionModel = new ListingDescriptionEpoxyModel_();
        this.controller.listingDescriptionModel.m5062id(-19);
        setControllerToStageTo(this.controller.listingDescriptionModel, this.controller);
        this.controller.bedDetailsModel = new BedDetailsTrayEpoxyModel_();
        this.controller.bedDetailsModel.m4375id(-20);
        setControllerToStageTo(this.controller.bedDetailsModel, this.controller);
        this.controller.businessDetailsModel = new StandardRowEpoxyModel_();
        this.controller.businessDetailsModel.m5602id(-21);
        setControllerToStageTo(this.controller.businessDetailsModel, this.controller);
        this.controller.urgencyModel = new UrgencyEpoxyModel_();
        this.controller.urgencyModel.m5746id(-22);
        setControllerToStageTo(this.controller.urgencyModel, this.controller);
        this.controller.houseRulesModel = new StandardRowEpoxyModel_();
        this.controller.houseRulesModel.m5602id(-23);
        setControllerToStageTo(this.controller.houseRulesModel, this.controller);
        this.controller.listingDetailsSummaryModel = new ListingDetailsSummaryEpoxyModel_();
        this.controller.listingDetailsSummaryModel.m5074id(-24);
        setControllerToStageTo(this.controller.listingDetailsSummaryModel, this.controller);
    }
}
