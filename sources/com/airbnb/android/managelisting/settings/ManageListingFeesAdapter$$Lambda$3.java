package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class ManageListingFeesAdapter$$Lambda$3 implements Listener {
    private final ManageListingFeesAdapter arg$1;

    private ManageListingFeesAdapter$$Lambda$3(ManageListingFeesAdapter manageListingFeesAdapter) {
        this.arg$1 = manageListingFeesAdapter;
    }

    public static Listener lambdaFactory$(ManageListingFeesAdapter manageListingFeesAdapter) {
        return new ManageListingFeesAdapter$$Lambda$3(manageListingFeesAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.weekendPrice = num;
    }
}