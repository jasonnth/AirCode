package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$22 implements OnClickListener {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$22(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$22(manageListingCheckInGuideFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.unpublish();
    }
}
