package com.airbnb.android.fixit.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class FixItReportActivity$$Lambda$2 implements Action1 {
    private final FixItReportActivity arg$1;

    private FixItReportActivity$$Lambda$2(FixItReportActivity fixItReportActivity) {
        this.arg$1 = fixItReportActivity;
    }

    public static Action1 lambdaFactory$(FixItReportActivity fixItReportActivity) {
        return new FixItReportActivity$$Lambda$2(fixItReportActivity);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.rootContainer, (AirRequestNetworkException) obj);
    }
}
