package com.airbnb.android.insights;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$3 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$3(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$3(insightsActivity);
    }

    public void call(Object obj) {
        InsightsActivity.lambda$new$2(this.arg$1, (SimpleListingResponse) obj);
    }
}
