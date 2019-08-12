package com.airbnb.android.thread.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class ThreadBlockInfoFragment$$Lambda$3 implements Action1 {
    private final ThreadBlockInfoFragment arg$1;

    private ThreadBlockInfoFragment$$Lambda$3(ThreadBlockInfoFragment threadBlockInfoFragment) {
        this.arg$1 = threadBlockInfoFragment;
    }

    public static Action1 lambdaFactory$(ThreadBlockInfoFragment threadBlockInfoFragment) {
        return new ThreadBlockInfoFragment$$Lambda$3(threadBlockInfoFragment);
    }

    public void call(Object obj) {
        this.arg$1.footer.setButtonLoading(false);
    }
}
