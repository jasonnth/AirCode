package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$6 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$6(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$6(threadFragment);
    }

    public void call(Object obj) {
        ThreadFragment.lambda$new$4(this.arg$1, (ReservationResponse) obj);
    }
}
