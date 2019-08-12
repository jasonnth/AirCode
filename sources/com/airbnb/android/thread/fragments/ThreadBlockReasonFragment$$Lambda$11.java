package com.airbnb.android.thread.fragments;

import com.airbnb.android.thread.controllers.ThreadBlockReasonEpoxyController.ThreadBlockReasonListener;

final /* synthetic */ class ThreadBlockReasonFragment$$Lambda$11 implements ThreadBlockReasonListener {
    private final ThreadBlockReasonFragment arg$1;

    private ThreadBlockReasonFragment$$Lambda$11(ThreadBlockReasonFragment threadBlockReasonFragment) {
        this.arg$1 = threadBlockReasonFragment;
    }

    public static ThreadBlockReasonListener lambdaFactory$(ThreadBlockReasonFragment threadBlockReasonFragment) {
        return new ThreadBlockReasonFragment$$Lambda$11(threadBlockReasonFragment);
    }

    public void enableSubmit(String str) {
        ThreadBlockReasonFragment.lambda$setUserFlagDetails$10(this.arg$1, str);
    }
}
