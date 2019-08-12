package com.airbnb.android.lib.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$5 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$5(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$5(threadFragment);
    }

    public void call(Object obj) {
        this.arg$1.actionButton.setNormal();
    }
}
