package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.utils.TextSetting;

final /* synthetic */ class ManageListingDetailsEpoxyController$$Lambda$3 implements OnClickListener {
    private final ManageListingDetailsEpoxyController arg$1;

    private ManageListingDetailsEpoxyController$$Lambda$3(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController) {
        this.arg$1 = manageListingDetailsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController) {
        return new ManageListingDetailsEpoxyController$$Lambda$3(manageListingDetailsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.textSetting(TextSetting.SelectSummary);
    }
}