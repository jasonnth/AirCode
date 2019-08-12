package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingStatusAdapter$$Lambda$2 implements OnClickListener {
    private final ManageListingStatusAdapter arg$1;

    private ManageListingStatusAdapter$$Lambda$2(ManageListingStatusAdapter manageListingStatusAdapter) {
        this.arg$1 = manageListingStatusAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingStatusAdapter manageListingStatusAdapter) {
        return new ManageListingStatusAdapter$$Lambda$2(manageListingStatusAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.snooze();
    }
}
