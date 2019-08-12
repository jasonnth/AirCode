package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ThreadResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$8 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$8(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$8(threadFragment);
    }

    public void call(Object obj) {
        ThreadFragment.lambda$new$6(this.arg$1, (ThreadResponse) obj);
    }
}
