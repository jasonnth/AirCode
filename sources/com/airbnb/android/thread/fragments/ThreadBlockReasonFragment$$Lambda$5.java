package com.airbnb.android.thread.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class ThreadBlockReasonFragment$$Lambda$5 implements Action1 {
    private final ThreadBlockReasonFragment arg$1;

    private ThreadBlockReasonFragment$$Lambda$5(ThreadBlockReasonFragment threadBlockReasonFragment) {
        this.arg$1 = threadBlockReasonFragment;
    }

    public static Action1 lambdaFactory$(ThreadBlockReasonFragment threadBlockReasonFragment) {
        return new ThreadBlockReasonFragment$$Lambda$5(threadBlockReasonFragment);
    }

    public void call(Object obj) {
        ThreadBlockReasonFragment.lambda$new$3(this.arg$1, (Boolean) obj);
    }
}
