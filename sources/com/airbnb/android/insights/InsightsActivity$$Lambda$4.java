package com.airbnb.android.insights;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$4 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$4(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$4(insightsActivity);
    }

    public void call(Object obj) {
        InsightsActivity.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
