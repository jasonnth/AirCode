package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPreBookingPreviewFragment$$Lambda$4 implements OnClickListener {
    private final ManageListingPreBookingPreviewFragment arg$1;

    private ManageListingPreBookingPreviewFragment$$Lambda$4(ManageListingPreBookingPreviewFragment manageListingPreBookingPreviewFragment) {
        this.arg$1 = manageListingPreBookingPreviewFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingPreBookingPreviewFragment manageListingPreBookingPreviewFragment) {
        return new ManageListingPreBookingPreviewFragment$$Lambda$4(manageListingPreBookingPreviewFragment);
    }

    public void onClick(View view) {
        this.arg$1.getFragmentManager().popBackStack();
    }
}
