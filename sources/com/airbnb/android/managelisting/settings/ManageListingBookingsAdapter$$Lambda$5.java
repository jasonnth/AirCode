package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.listing.utils.TextSetting;

final /* synthetic */ class ManageListingBookingsAdapter$$Lambda$5 implements OnClickListener {
    private final ManageListingBookingsAdapter arg$1;

    private ManageListingBookingsAdapter$$Lambda$5(ManageListingBookingsAdapter manageListingBookingsAdapter) {
        this.arg$1 = manageListingBookingsAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingBookingsAdapter manageListingBookingsAdapter) {
        return new ManageListingBookingsAdapter$$Lambda$5(manageListingBookingsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.textSetting(TextSetting.PrebookingMessage);
    }
}
