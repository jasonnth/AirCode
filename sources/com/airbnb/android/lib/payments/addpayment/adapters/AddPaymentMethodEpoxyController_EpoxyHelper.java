package com.airbnb.android.lib.payments.addpayment.adapters;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.epoxy.ControllerHelper;

public class AddPaymentMethodEpoxyController_EpoxyHelper extends ControllerHelper<AddPaymentMethodEpoxyController> {
    private final AddPaymentMethodEpoxyController controller;

    public AddPaymentMethodEpoxyController_EpoxyHelper(AddPaymentMethodEpoxyController controller2) {
        this.controller = controller2;
    }

    public void resetAutoModels() {
        this.controller.loadingRowModel = new EpoxyControllerLoadingModel_();
        this.controller.loadingRowModel.m4582id(-1);
        setControllerToStageTo(this.controller.loadingRowModel, this.controller);
        this.controller.marqueeModel = new DocumentMarqueeEpoxyModel_();
        this.controller.marqueeModel.m4534id(-2);
        setControllerToStageTo(this.controller.marqueeModel, this.controller);
    }
}
