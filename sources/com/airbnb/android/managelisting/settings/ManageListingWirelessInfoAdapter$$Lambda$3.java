package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class ManageListingWirelessInfoAdapter$$Lambda$3 implements OnInputChangedListener {
    private final ManageListingWirelessInfoAdapter arg$1;

    private ManageListingWirelessInfoAdapter$$Lambda$3(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        this.arg$1 = manageListingWirelessInfoAdapter;
    }

    public static OnInputChangedListener lambdaFactory$(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        return new ManageListingWirelessInfoAdapter$$Lambda$3(manageListingWirelessInfoAdapter);
    }

    public void onInputChanged(String str) {
        ManageListingWirelessInfoAdapter.lambda$new$2(this.arg$1, str);
    }
}
