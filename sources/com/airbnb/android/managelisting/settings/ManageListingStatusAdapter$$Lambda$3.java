package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingStatusAdapter$$Lambda$3 implements OnClickListener {
    private final ManageListingStatusAdapter arg$1;

    private ManageListingStatusAdapter$$Lambda$3(ManageListingStatusAdapter manageListingStatusAdapter) {
        this.arg$1 = manageListingStatusAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingStatusAdapter manageListingStatusAdapter) {
        return new ManageListingStatusAdapter$$Lambda$3(manageListingStatusAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.unlist();
    }
}
