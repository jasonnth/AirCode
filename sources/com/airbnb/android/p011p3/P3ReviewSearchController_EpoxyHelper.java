package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.epoxy.ControllerHelper;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchController_EpoxyHelper */
public class P3ReviewSearchController_EpoxyHelper extends ControllerHelper<P3ReviewSearchController> {
    private final P3ReviewSearchController controller;

    public P3ReviewSearchController_EpoxyHelper(P3ReviewSearchController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.noResultsModel = new StandardRowEpoxyModel_();
        this.controller.noResultsModel.m5602id(-1);
        setControllerToStageTo(this.controller.noResultsModel, this.controller);
        this.controller.filterHeaderModel = new StandardRowEpoxyModel_();
        this.controller.filterHeaderModel.m5602id(-2);
        setControllerToStageTo(this.controller.filterHeaderModel, this.controller);
        this.controller.loaderModel = new EpoxyControllerLoadingModel_();
        this.controller.loaderModel.m4582id(-3);
        setControllerToStageTo(this.controller.loaderModel, this.controller);
    }
}
