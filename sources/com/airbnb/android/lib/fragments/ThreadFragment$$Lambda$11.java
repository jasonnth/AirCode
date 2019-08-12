package com.airbnb.android.lib.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$11 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$11(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$11(threadFragment);
    }

    public void call(Object obj) {
        this.arg$1.forceFullThreadReload();
    }
}
