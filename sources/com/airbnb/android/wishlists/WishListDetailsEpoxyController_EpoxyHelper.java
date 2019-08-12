package com.airbnb.android.wishlists;

import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.CollaboratorsRowModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class WishListDetailsEpoxyController_EpoxyHelper extends ControllerHelper<WishListDetailsEpoxyController> {
    private final WishListDetailsEpoxyController controller;

    public WishListDetailsEpoxyController_EpoxyHelper(WishListDetailsEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.unavailableHomesCarouselModel = new CarouselModel_();
        this.controller.unavailableHomesCarouselModel.m4423id(-1);
        setControllerToStageTo(this.controller.unavailableHomesCarouselModel, this.controller);
        this.controller.tabBarModel = new WLDetailsTabBarModel_();
        this.controller.tabBarModel.m1458id(-2);
        setControllerToStageTo(this.controller.tabBarModel, this.controller);
        this.controller.collaboratorModel = new CollaboratorsRowModel_();
        this.controller.collaboratorModel.m4495id(-3);
        setControllerToStageTo(this.controller.collaboratorModel, this.controller);
        this.controller.secondSectionHeader = new SectionHeaderEpoxyModel_();
        this.controller.secondSectionHeader.m5554id(-4);
        setControllerToStageTo(this.controller.secondSectionHeader, this.controller);
        this.controller.unavailableItemsHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.unavailableItemsHeaderModel.m5170id(-5);
        setControllerToStageTo(this.controller.unavailableItemsHeaderModel, this.controller);
        this.controller.unavailableTripsCarouselModel = new CarouselModel_();
        this.controller.unavailableTripsCarouselModel.m4423id(-6);
        setControllerToStageTo(this.controller.unavailableTripsCarouselModel, this.controller);
        this.controller.linkRow = new LinkActionRowEpoxyModel_();
        this.controller.linkRow.m5038id(-7);
        setControllerToStageTo(this.controller.linkRow, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-8);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.pillSpacerModel = new MapPillSpacerModel();
        this.controller.pillSpacerModel.mo11716id(-9);
        setControllerToStageTo(this.controller.pillSpacerModel, this.controller);
        this.controller.firstSectionHeader = new SectionHeaderEpoxyModel_();
        this.controller.firstSectionHeader.m5554id(-10);
        setControllerToStageTo(this.controller.firstSectionHeader, this.controller);
        this.controller.loaderModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderModel.m4582id(-11);
        setControllerToStageTo(this.controller.loaderModel, this.controller);
    }
}
