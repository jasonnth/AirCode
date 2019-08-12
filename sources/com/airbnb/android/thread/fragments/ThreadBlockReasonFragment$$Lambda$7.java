package com.airbnb.android.thread.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadBlockReasonFragment$$Lambda$7 implements Action1 {
    private final ThreadBlockReasonFragment arg$1;

    private ThreadBlockReasonFragment$$Lambda$7(ThreadBlockReasonFragment threadBlockReasonFragment) {
        this.arg$1 = threadBlockReasonFragment;
    }

    public static Action1 lambdaFactory$(ThreadBlockReasonFragment threadBlockReasonFragment) {
        return new ThreadBlockReasonFragment$$Lambda$7(threadBlockReasonFragment);
    }

    public void call(Object obj) {
        ThreadBlockReasonFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
