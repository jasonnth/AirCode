package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class ManageListingFeesAdapter$$Lambda$1 implements Listener {
    private final ManageListingFeesAdapter arg$1;

    private ManageListingFeesAdapter$$Lambda$1(ManageListingFeesAdapter manageListingFeesAdapter) {
        this.arg$1 = manageListingFeesAdapter;
    }

    public static Listener lambdaFactory$(ManageListingFeesAdapter manageListingFeesAdapter) {
        return new ManageListingFeesAdapter$$Lambda$1(manageListingFeesAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.securityDeposit = num;
    }
}