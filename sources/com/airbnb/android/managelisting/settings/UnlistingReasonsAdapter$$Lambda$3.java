package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class UnlistingReasonsAdapter$$Lambda$3 implements OnClickListener {
    private final ManageListingDataController arg$1;
    private final Integer arg$2;

    private UnlistingReasonsAdapter$$Lambda$3(ManageListingDataController manageListingDataController, Integer num) {
        this.arg$1 = manageListingDataController;
        this.arg$2 = num;
    }

    public static OnClickListener lambdaFactory$(ManageListingDataController manageListingDataController, Integer num) {
        return new UnlistingReasonsAdapter$$Lambda$3(manageListingDataController, num);
    }

    public void onClick(View view) {
        UnlistingReasonsAdapter.lambda$null$1(this.arg$1, this.arg$2, view);
    }
}
