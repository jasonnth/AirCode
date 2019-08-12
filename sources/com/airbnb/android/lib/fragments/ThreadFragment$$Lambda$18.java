package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$18 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$18(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$18(threadFragment);
    }

    public void call(Object obj) {
        ThreadFragment.lambda$new$17(this.arg$1, (AirRequestNetworkException) obj);
    }
}
