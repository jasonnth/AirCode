package com.airbnb.android.lib.fragments.inbox.threads;

import com.airbnb.android.lib.fragments.inbox.threads.ThreadActionButtonController.Listener;

final /* synthetic */ class ThreadActionButtonController$$Lambda$5 implements Runnable {
    private final Listener arg$1;

    private ThreadActionButtonController$$Lambda$5(Listener listener) {
        this.arg$1 = listener;
    }

    public static Runnable lambdaFactory$(Listener listener) {
        return new ThreadActionButtonController$$Lambda$5(listener);
    }

    public void run() {
        this.arg$1.writeReview();
    }
}
