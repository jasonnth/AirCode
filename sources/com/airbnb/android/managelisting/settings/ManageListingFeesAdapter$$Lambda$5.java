package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingFeesAdapter$$Lambda$5 implements OnClickListener {
    private final ManageListingFeesAdapter arg$1;

    private ManageListingFeesAdapter$$Lambda$5(ManageListingFeesAdapter manageListingFeesAdapter) {
        this.arg$1 = manageListingFeesAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingFeesAdapter manageListingFeesAdapter) {
        return new ManageListingFeesAdapter$$Lambda$5(manageListingFeesAdapter);
    }

    public void onClick(View view) {
        this.arg$1.showGuestOptionsMenu();
    }
}
