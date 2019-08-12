package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DlsManageListingActivity$$Lambda$1 implements Action1 {
    private final DlsManageListingActivity arg$1;

    private DlsManageListingActivity$$Lambda$1(DlsManageListingActivity dlsManageListingActivity) {
        this.arg$1 = dlsManageListingActivity;
    }

    public static Action1 lambdaFactory$(DlsManageListingActivity dlsManageListingActivity) {
        return new DlsManageListingActivity$$Lambda$1(dlsManageListingActivity);
    }

    public void call(Object obj) {
        this.arg$1.responseFinished((AirBatchResponse) obj);
    }
}
