package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadFragment$$Lambda$26 implements OnClickListener {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$26(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$26(threadFragment);
    }

    public void onClick(View view) {
        ThreadFragment.lambda$setupBottomActionButton$27(this.arg$1, view);
    }
}
