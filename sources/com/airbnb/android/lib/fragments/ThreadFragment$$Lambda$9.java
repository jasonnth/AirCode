package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$9 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$9(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$9(threadFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, ThreadFragment$$Lambda$32.lambdaFactory$(this.arg$1));
    }
}
