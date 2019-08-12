package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingTripLengthAdapter$$Lambda$1 implements OnClickListener {
    private final ManageListingTripLengthAdapter arg$1;

    private ManageListingTripLengthAdapter$$Lambda$1(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        this.arg$1 = manageListingTripLengthAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        return new ManageListingTripLengthAdapter$$Lambda$1(manageListingTripLengthAdapter);
    }

    public void onClick(View view) {
        this.arg$1.notifyListener();
    }
}
