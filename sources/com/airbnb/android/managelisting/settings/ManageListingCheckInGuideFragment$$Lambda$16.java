package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$16 implements OnClickListener {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$16(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$16(manageListingCheckInGuideFragment);
    }

    public void onClick(View view) {
        this.arg$1.preview();
    }
}