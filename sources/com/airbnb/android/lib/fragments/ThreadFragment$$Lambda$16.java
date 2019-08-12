package com.airbnb.android.lib.fragments;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final /* synthetic */ class ThreadFragment$$Lambda$16 implements OnGlobalLayoutListener {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$16(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static OnGlobalLayoutListener lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$16(threadFragment);
    }

    public void onGlobalLayout() {
        this.arg$1.setupGuestResponseButton();
    }
}
