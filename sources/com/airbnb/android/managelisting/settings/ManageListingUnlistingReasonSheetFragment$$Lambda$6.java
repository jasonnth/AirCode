package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingUnlistingReasonSheetFragment$$Lambda$6 implements OnClickListener {
    private final ManageListingUnlistingReasonSheetFragment arg$1;

    private ManageListingUnlistingReasonSheetFragment$$Lambda$6(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        this.arg$1 = manageListingUnlistingReasonSheetFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        return new ManageListingUnlistingReasonSheetFragment$$Lambda$6(manageListingUnlistingReasonSheetFragment);
    }

    public void onClick(View view) {
        this.arg$1.unlist(view);
    }
}
