package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DlsManageListingActivity$$Lambda$3 implements OnClickListener {
    private final DlsManageListingActivity arg$1;

    private DlsManageListingActivity$$Lambda$3(DlsManageListingActivity dlsManageListingActivity) {
        this.arg$1 = dlsManageListingActivity;
    }

    public static OnClickListener lambdaFactory$(DlsManageListingActivity dlsManageListingActivity) {
        return new DlsManageListingActivity$$Lambda$3(dlsManageListingActivity);
    }

    public void onClick(View view) {
        this.arg$1.fetchData();
    }
}
