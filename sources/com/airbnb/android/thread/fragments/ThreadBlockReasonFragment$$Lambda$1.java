package com.airbnb.android.thread.fragments;

import com.airbnb.android.core.responses.GetUserFlagDetailsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadBlockReasonFragment$$Lambda$1 implements Action1 {
    private final ThreadBlockReasonFragment arg$1;

    private ThreadBlockReasonFragment$$Lambda$1(ThreadBlockReasonFragment threadBlockReasonFragment) {
        this.arg$1 = threadBlockReasonFragment;
    }

    public static Action1 lambdaFactory$(ThreadBlockReasonFragment threadBlockReasonFragment) {
        return new ThreadBlockReasonFragment$$Lambda$1(threadBlockReasonFragment);
    }

    public void call(Object obj) {
        this.arg$1.setUserFlagDetails(((GetUserFlagDetailsResponse) obj).userFlagDetails);
    }
}
