package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingBookingsAdapter$$Lambda$18 implements OnClickListener {
    private final ManageListingBookingsAdapter arg$1;

    private ManageListingBookingsAdapter$$Lambda$18(ManageListingBookingsAdapter manageListingBookingsAdapter) {
        this.arg$1 = manageListingBookingsAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingBookingsAdapter manageListingBookingsAdapter) {
        return new ManageListingBookingsAdapter$$Lambda$18(manageListingBookingsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.cityRegistration();
    }
}