package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$14 implements OnClickListener {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$14(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$14(manageListingCheckInGuideFragment);
    }

    public void onClick(View view) {
        this.arg$1.preview();
    }
}
