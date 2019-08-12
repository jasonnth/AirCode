package com.airbnb.android.insights;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$6 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$6(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$6(insightsActivity);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.findViewById(C6552R.C6554id.content_container), (AirRequestNetworkException) obj);
    }
}
