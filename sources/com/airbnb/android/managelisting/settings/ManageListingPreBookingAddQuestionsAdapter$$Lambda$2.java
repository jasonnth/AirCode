package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.utils.TextSetting;

final /* synthetic */ class ManageListingPreBookingAddQuestionsAdapter$$Lambda$2 implements OnClickListener {
    private final ManageListingDataController arg$1;

    private ManageListingPreBookingAddQuestionsAdapter$$Lambda$2(ManageListingDataController manageListingDataController) {
        this.arg$1 = manageListingDataController;
    }

    public static OnClickListener lambdaFactory$(ManageListingDataController manageListingDataController) {
        return new ManageListingPreBookingAddQuestionsAdapter$$Lambda$2(manageListingDataController);
    }

    public void onClick(View view) {
        this.arg$1.actionExecutor.textSetting(TextSetting.ManageListingPreBookingCustomQuestion);
    }
}
