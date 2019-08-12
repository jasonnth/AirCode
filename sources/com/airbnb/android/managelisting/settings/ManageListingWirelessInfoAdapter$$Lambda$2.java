package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class ManageListingWirelessInfoAdapter$$Lambda$2 implements OnInputChangedListener {
    private final ManageListingWirelessInfoAdapter arg$1;

    private ManageListingWirelessInfoAdapter$$Lambda$2(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        this.arg$1 = manageListingWirelessInfoAdapter;
    }

    public static OnInputChangedListener lambdaFactory$(ManageListingWirelessInfoAdapter manageListingWirelessInfoAdapter) {
        return new ManageListingWirelessInfoAdapter$$Lambda$2(manageListingWirelessInfoAdapter);
    }

    public void onInputChanged(String str) {
        ManageListingWirelessInfoAdapter.lambda$new$1(this.arg$1, str);
    }
}
