package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideController$$Lambda$2 implements OnClickListener {
    private final ManageListingCheckInGuideController arg$1;

    private ManageListingCheckInGuideController$$Lambda$2(ManageListingCheckInGuideController manageListingCheckInGuideController) {
        this.arg$1 = manageListingCheckInGuideController;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideController manageListingCheckInGuideController) {
        return new ManageListingCheckInGuideController$$Lambda$2(manageListingCheckInGuideController);
    }

    public void onClick(View view) {
        this.arg$1.listener.addEmptyStepCard();
    }
}
