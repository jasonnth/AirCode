package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingDetailsEpoxyController$$Lambda$17 implements OnClickListener {
    private final ManageListingDetailsEpoxyController arg$1;
    private final boolean arg$2;

    private ManageListingDetailsEpoxyController$$Lambda$17(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController, boolean z) {
        this.arg$1 = manageListingDetailsEpoxyController;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController, boolean z) {
        return new ManageListingDetailsEpoxyController$$Lambda$17(manageListingDetailsEpoxyController, z);
    }

    public void onClick(View view) {
        ManageListingDetailsEpoxyController.lambda$addSelectSwitcherBanner$16(this.arg$1, this.arg$2, view);
    }
}
