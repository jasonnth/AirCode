package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingGuestRequirementsAdapter$$Lambda$2 implements OnClickListener {
    private final ManageListingGuestRequirementsAdapter arg$1;

    private ManageListingGuestRequirementsAdapter$$Lambda$2(ManageListingGuestRequirementsAdapter manageListingGuestRequirementsAdapter) {
        this.arg$1 = manageListingGuestRequirementsAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingGuestRequirementsAdapter manageListingGuestRequirementsAdapter) {
        return new ManageListingGuestRequirementsAdapter$$Lambda$2(manageListingGuestRequirementsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.additionalGuestRequirements();
    }
}
