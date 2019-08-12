package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$20 implements OnClickListener {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$20(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$20(manageListingCheckInGuideFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.publishGuide();
    }
}
