package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DlsManageListingActivity$$Lambda$2 implements Action1 {
    private final DlsManageListingActivity arg$1;

    private DlsManageListingActivity$$Lambda$2(DlsManageListingActivity dlsManageListingActivity) {
        this.arg$1 = dlsManageListingActivity;
    }

    public static Action1 lambdaFactory$(DlsManageListingActivity dlsManageListingActivity) {
        return new DlsManageListingActivity$$Lambda$2(dlsManageListingActivity);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
