package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPublishGuideConfirmationSheetFragment$$Lambda$1 implements OnClickListener {
    private final ManageListingPublishGuideConfirmationSheetFragment arg$1;

    private ManageListingPublishGuideConfirmationSheetFragment$$Lambda$1(ManageListingPublishGuideConfirmationSheetFragment manageListingPublishGuideConfirmationSheetFragment) {
        this.arg$1 = manageListingPublishGuideConfirmationSheetFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingPublishGuideConfirmationSheetFragment manageListingPublishGuideConfirmationSheetFragment) {
        return new ManageListingPublishGuideConfirmationSheetFragment$$Lambda$1(manageListingPublishGuideConfirmationSheetFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.popToHome();
    }
}
