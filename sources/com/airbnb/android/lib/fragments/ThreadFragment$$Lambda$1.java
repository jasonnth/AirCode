package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$1 implements Action1 {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$1(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static Action1 lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$1(threadFragment);
    }

    public void call(Object obj) {
        this.arg$1.onFetchCohostedListingsSuccess((AirBatchResponse) obj);
    }
}
