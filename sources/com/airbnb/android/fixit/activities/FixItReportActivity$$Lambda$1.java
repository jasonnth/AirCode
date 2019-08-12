package com.airbnb.android.fixit.activities;

import com.airbnb.android.core.responses.FixItReportResponse;
import p032rx.functions.Action1;

final /* synthetic */ class FixItReportActivity$$Lambda$1 implements Action1 {
    private final FixItReportActivity arg$1;

    private FixItReportActivity$$Lambda$1(FixItReportActivity fixItReportActivity) {
        this.arg$1 = fixItReportActivity;
    }

    public static Action1 lambdaFactory$(FixItReportActivity fixItReportActivity) {
        return new FixItReportActivity$$Lambda$1(fixItReportActivity);
    }

    public void call(Object obj) {
        FixItReportActivity.lambda$new$0(this.arg$1, (FixItReportResponse) obj);
    }
}
