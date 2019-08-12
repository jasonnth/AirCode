package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingCheckinTypeTextSettingFragment$$Lambda$6 implements OnClickListener {
    private final ManageListingCheckinTypeTextSettingFragment arg$1;

    private ManageListingCheckinTypeTextSettingFragment$$Lambda$6(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        this.arg$1 = manageListingCheckinTypeTextSettingFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        return new ManageListingCheckinTypeTextSettingFragment$$Lambda$6(manageListingCheckinTypeTextSettingFragment);
    }

    public void onClick(View view) {
        this.arg$1.refreshCheckinInformation();
    }
}
