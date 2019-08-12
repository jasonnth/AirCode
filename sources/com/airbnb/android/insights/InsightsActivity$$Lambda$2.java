package com.airbnb.android.insights;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$2 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$2(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$2(insightsActivity);
    }

    public void call(Object obj) {
        InsightsActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
