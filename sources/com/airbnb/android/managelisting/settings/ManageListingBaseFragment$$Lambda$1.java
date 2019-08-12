package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class ManageListingBaseFragment$$Lambda$1 implements OnBackListener {
    private final ManageListingBaseFragment arg$1;

    private ManageListingBaseFragment$$Lambda$1(ManageListingBaseFragment manageListingBaseFragment) {
        this.arg$1 = manageListingBaseFragment;
    }

    public static OnBackListener lambdaFactory$(ManageListingBaseFragment manageListingBaseFragment) {
        return new ManageListingBaseFragment$$Lambda$1(manageListingBaseFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
