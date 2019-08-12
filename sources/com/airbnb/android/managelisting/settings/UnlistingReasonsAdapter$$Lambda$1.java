package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class UnlistingReasonsAdapter$$Lambda$1 implements OnClickListener {
    private final ManageListingDataController arg$1;

    private UnlistingReasonsAdapter$$Lambda$1(ManageListingDataController manageListingDataController) {
        this.arg$1 = manageListingDataController;
    }

    public static OnClickListener lambdaFactory$(ManageListingDataController manageListingDataController) {
        return new UnlistingReasonsAdapter$$Lambda$1(manageListingDataController);
    }

    public void onClick(View view) {
        UnlistingReasonsAdapter.lambda$new$0(this.arg$1, view);
    }
}
