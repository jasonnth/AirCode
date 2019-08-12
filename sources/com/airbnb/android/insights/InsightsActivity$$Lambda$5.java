package com.airbnb.android.insights;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$5 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$5(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$5(insightsActivity);
    }

    public void call(Object obj) {
        InsightsActivity.lambda$new$4(this.arg$1, (ListingResponse) obj);
    }
}
