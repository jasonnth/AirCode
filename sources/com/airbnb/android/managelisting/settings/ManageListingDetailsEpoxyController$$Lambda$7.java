package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingDetailsEpoxyController$$Lambda$7 implements OnClickListener {
    private final ManageListingDetailsEpoxyController arg$1;

    private ManageListingDetailsEpoxyController$$Lambda$7(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController) {
        this.arg$1 = manageListingDetailsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController) {
        return new ManageListingDetailsEpoxyController$$Lambda$7(manageListingDetailsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.extraService();
    }
}
