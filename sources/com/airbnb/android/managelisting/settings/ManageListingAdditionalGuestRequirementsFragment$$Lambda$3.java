package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingAdditionalGuestRequirementsFragment$$Lambda$3 implements OnClickListener {
    private final ManageListingAdditionalGuestRequirementsFragment arg$1;

    private ManageListingAdditionalGuestRequirementsFragment$$Lambda$3(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        this.arg$1 = manageListingAdditionalGuestRequirementsFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        return new ManageListingAdditionalGuestRequirementsFragment$$Lambda$3(manageListingAdditionalGuestRequirementsFragment);
    }

    public void onClick(View view) {
        this.arg$1.onSave();
    }
}
