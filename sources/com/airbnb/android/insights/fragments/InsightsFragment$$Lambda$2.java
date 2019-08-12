package com.airbnb.android.insights.fragments;

import android.content.Context;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsFragment$$Lambda$2 implements Action1 {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$2(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static Action1 lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$2(insightsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastNetworkError((Context) this.arg$1.getActivity(), (NetworkException) (AirRequestNetworkException) obj);
    }
}
