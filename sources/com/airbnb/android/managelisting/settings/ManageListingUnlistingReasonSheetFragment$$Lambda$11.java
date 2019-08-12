package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.HelpCenterArticle;

final /* synthetic */ class ManageListingUnlistingReasonSheetFragment$$Lambda$11 implements OnClickListener {
    private final ManageListingUnlistingReasonSheetFragment arg$1;

    private ManageListingUnlistingReasonSheetFragment$$Lambda$11(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        this.arg$1 = manageListingUnlistingReasonSheetFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        return new ManageListingUnlistingReasonSheetFragment$$Lambda$11(manageListingUnlistingReasonSheetFragment);
    }

    public void onClick(View view) {
        this.arg$1.logAndNavToHelpCenterArticle(this.arg$1.getContext(), HelpCenterArticle.HOST_GUARANTEE);
    }
}
