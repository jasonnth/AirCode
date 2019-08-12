package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class ManageListingWirelessInfoAdapter$$Lambda$1 implements OnFocusChangeListener {
    private final ManageListingWirelessInfoAdapter arg$1;

    private ManageListingWirelessInfoAdapter$$Lambda$1(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        this.arg$1 = manageListingWirelessInfoAdapter;
    }

    public static OnFocusChangeListener lambdaFactory$(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        return new ManageListingWirelessInfoAdapter$$Lambda$1(manageListingWirelessInfoAdapter);
    }

    public void onFocusChange(View view, boolean z) {
        this.arg$1.handleOnWifiNameFocusChanged(z);
    }
}
