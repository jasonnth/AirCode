package com.airbnb.android.guestrecovery.adapter;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.InfoRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;
import com.airbnb.p027n2.components.TagsCollectionRowModel_;

public class GuestRecoveryEpoxyController_EpoxyHelper extends ControllerHelper<GuestRecoveryEpoxyController> {
    private final GuestRecoveryEpoxyController controller;

    public GuestRecoveryEpoxyController_EpoxyHelper(GuestRecoveryEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.tagsCollectionRowModel = new TagsCollectionRowModel_();
        this.controller.tagsCollectionRowModel.mo11716id(-1);
        setControllerToStageTo(this.controller.tagsCollectionRowModel, this.controller);
        this.controller.loadingModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingModel.m4582id(-2);
        setControllerToStageTo(this.controller.loadingModel, this.controller);
        this.controller.similarListingsModel = new ListingsTrayEpoxyModel_();
        this.controller.similarListingsModel.m5098id(-3);
        setControllerToStageTo(this.controller.similarListingsModel, this.controller);
        this.controller.dividerModel = new SubsectionDividerEpoxyModel_();
        this.controller.dividerModel.m5650id(-4);
        setControllerToStageTo(this.controller.dividerModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-5);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
        this.controller.goToTripDetailsRowModel = new InfoRowEpoxyModel_();
        this.controller.goToTripDetailsRowModel.m4834id(-6);
        setControllerToStageTo(this.controller.goToTripDetailsRowModel, this.controller);
    }
}
