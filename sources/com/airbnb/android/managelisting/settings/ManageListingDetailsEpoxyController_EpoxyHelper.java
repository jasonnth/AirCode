package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.EventScheduleInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImpactDisplayCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowWithLabelEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class ManageListingDetailsEpoxyController_EpoxyHelper extends ControllerHelper<ManageListingDetailsEpoxyController> {
    private final ManageListingDetailsEpoxyController controller;

    public ManageListingDetailsEpoxyController_EpoxyHelper(ManageListingDetailsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.locationRow = new StandardRowEpoxyModel_();
        this.controller.locationRow.m5602id(-1);
        setControllerToStageTo(this.controller.locationRow, this.controller);
        this.controller.selfCheckInRow = new StandardRowEpoxyModel_();
        this.controller.selfCheckInRow.m5602id(-2);
        setControllerToStageTo(this.controller.selfCheckInRow, this.controller);
        this.controller.directionsRow = new StandardRowEpoxyModel_();
        this.controller.directionsRow.m5602id(-3);
        setControllerToStageTo(this.controller.directionsRow, this.controller);
        this.controller.imageRow = new ImpactDisplayCardEpoxyModel_();
        this.controller.imageRow.m4798id(-4);
        setControllerToStageTo(this.controller.imageRow, this.controller);
        this.controller.extraServicesRow = new StandardRowEpoxyModel_();
        this.controller.extraServicesRow.m5602id(-5);
        setControllerToStageTo(this.controller.extraServicesRow, this.controller);
        this.controller.wifiRow = new StandardRowEpoxyModel_();
        this.controller.wifiRow.m5602id(-6);
        setControllerToStageTo(this.controller.wifiRow, this.controller);
        this.controller.roomsAndGuestsRow = new StandardRowEpoxyModel_();
        this.controller.roomsAndGuestsRow.m5602id(-7);
        setControllerToStageTo(this.controller.roomsAndGuestsRow, this.controller);
        this.controller.header = new SectionHeaderEpoxyModel_();
        this.controller.header.m5554id(-8);
        setControllerToStageTo(this.controller.header, this.controller);
        this.controller.descriptionSettingsRow = new StandardRowEpoxyModel_();
        this.controller.descriptionSettingsRow.m5602id(-9);
        setControllerToStageTo(this.controller.descriptionSettingsRow, this.controller);
        this.controller.selectSummaryRow = new StandardRowEpoxyModel_();
        this.controller.selectSummaryRow.m5602id(-10);
        setControllerToStageTo(this.controller.selectSummaryRow, this.controller);
        this.controller.checkInGuideRow = new StandardRowWithLabelEpoxyModel_();
        this.controller.checkInGuideRow.m5614id(-11);
        setControllerToStageTo(this.controller.checkInGuideRow, this.controller);
        this.controller.selectScheduleBanner = new EventScheduleInterstitialEpoxyModel_();
        this.controller.selectScheduleBanner.m4594id(-12);
        setControllerToStageTo(this.controller.selectScheduleBanner, this.controller);
        this.controller.titleRow = new StandardRowWithLabelEpoxyModel_();
        this.controller.titleRow.m5614id(-13);
        setControllerToStageTo(this.controller.titleRow, this.controller);
        this.controller.amenitiesRow = new StandardRowEpoxyModel_();
        this.controller.amenitiesRow.m5602id(-14);
        setControllerToStageTo(this.controller.amenitiesRow, this.controller);
        this.controller.guestResourcesTitleRow = new SectionHeaderEpoxyModel_();
        this.controller.guestResourcesTitleRow.m5554id(-15);
        setControllerToStageTo(this.controller.guestResourcesTitleRow, this.controller);
        this.controller.selectBanner = new InterstitialEpoxyModel_();
        this.controller.selectBanner.m4966id(-16);
        setControllerToStageTo(this.controller.selectBanner, this.controller);
        this.controller.houseManualRow = new StandardRowEpoxyModel_();
        this.controller.houseManualRow.m5602id(-17);
        setControllerToStageTo(this.controller.houseManualRow, this.controller);
        this.controller.loadingRow = new EpoxyControllerLoadingModel_();
        this.controller.loadingRow.m4582id(-18);
        setControllerToStageTo(this.controller.loadingRow, this.controller);
    }
}
