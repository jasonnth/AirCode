package com.airbnb.android.places.adapters;

import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.places.viewmodels.LocalPerspectiveEpoxyModel_;
import com.airbnb.android.places.viewmodels.PhotoCarouselMarqueeEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceActivityDetailsEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceActivityHeaderEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceInfoEpoxyModel_;
import com.airbnb.android.places.viewmodels.ResyPartnershipViewEpoxyModel_;
import com.airbnb.android.places.viewmodels.ResyRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

public class PlaceActivityPDPController_EpoxyHelper extends ControllerHelper<PlaceActivityPDPController> {
    private final PlaceActivityPDPController controller;

    public PlaceActivityPDPController_EpoxyHelper(PlaceActivityPDPController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.resyPartnershipModel = new ResyPartnershipViewEpoxyModel_();
        this.controller.resyPartnershipModel.m6451id(-1);
        setControllerToStageTo(this.controller.resyPartnershipModel, this.controller);
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-2);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.recommendedHeaderModel = new MicroSectionHeaderEpoxyModel_();
        this.controller.recommendedHeaderModel.m5170id(-3);
        setControllerToStageTo(this.controller.recommendedHeaderModel, this.controller);
        this.controller.placeInfoModel = new PlaceInfoEpoxyModel_();
        this.controller.placeInfoModel.m6439id(-4);
        setControllerToStageTo(this.controller.placeInfoModel, this.controller);
        this.controller.placeActivityDetailsModel = new PlaceActivityDetailsEpoxyModel_();
        this.controller.placeActivityDetailsModel.m6415id(-5);
        setControllerToStageTo(this.controller.placeActivityDetailsModel, this.controller);
        this.controller.localPerspectiveEpoxyModel = new LocalPerspectiveEpoxyModel_();
        this.controller.localPerspectiveEpoxyModel.m6391id(-6);
        setControllerToStageTo(this.controller.localPerspectiveEpoxyModel, this.controller);
        this.controller.placeActivityHeaderModel = new PlaceActivityHeaderEpoxyModel_();
        this.controller.placeActivityHeaderModel.m6427id(-7);
        setControllerToStageTo(this.controller.placeActivityHeaderModel, this.controller);
        this.controller.resyRowModel = new ResyRowEpoxyModel_();
        this.controller.resyRowModel.m6463id(-8);
        setControllerToStageTo(this.controller.resyRowModel, this.controller);
        this.controller.restaurantInfoModel = new LocalPerspectiveEpoxyModel_();
        this.controller.restaurantInfoModel.m6391id(-9);
        setControllerToStageTo(this.controller.restaurantInfoModel, this.controller);
        this.controller.recommendedCarouselModel = new DeprecatedCarouselEpoxyModel_<>();
        this.controller.recommendedCarouselModel.m4510id(-10);
        setControllerToStageTo(this.controller.recommendedCarouselModel, this.controller);
        this.controller.photoCarouselModel = new PhotoCarouselMarqueeEpoxyModel_();
        this.controller.photoCarouselModel.m6403id(-11);
        setControllerToStageTo(this.controller.photoCarouselModel, this.controller);
        this.controller.feedbackModel = new BasicRowEpoxyModel_();
        this.controller.feedbackModel.m4351id(-12);
        setControllerToStageTo(this.controller.feedbackModel, this.controller);
    }
}
