package com.airbnb.android.thread.fragments;

import com.airbnb.android.core.responses.UserBlockResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadBlockInfoFragment$$Lambda$1 implements Action1 {
    private final ThreadBlockInfoFragment arg$1;

    private ThreadBlockInfoFragment$$Lambda$1(ThreadBlockInfoFragment threadBlockInfoFragment) {
        this.arg$1 = threadBlockInfoFragment;
    }

    public static Action1 lambdaFactory$(ThreadBlockInfoFragment threadBlockInfoFragment) {
        return new ThreadBlockInfoFragment$$Lambda$1(threadBlockInfoFragment);
    }

    public void call(Object obj) {
        this.arg$1.threadBlockController.showThreadBlockConfirmFragment(((UserBlockResponse) obj).userBlock);
    }
}
