package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingSnoozeSettingAdapter$$Lambda$1 implements OnClickListener {
    private final ManageListingSnoozeSettingAdapter arg$1;

    private ManageListingSnoozeSettingAdapter$$Lambda$1(ManageListingSnoozeSettingAdapter manageListingSnoozeSettingAdapter) {
        this.arg$1 = manageListingSnoozeSettingAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingSnoozeSettingAdapter manageListingSnoozeSettingAdapter) {
        return new ManageListingSnoozeSettingAdapter$$Lambda$1(manageListingSnoozeSettingAdapter);
    }

    public void onClick(View view) {
        this.arg$1.notifyListenerForDateSelected();
    }
}
