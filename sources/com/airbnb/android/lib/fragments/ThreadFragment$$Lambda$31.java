package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadFragment$$Lambda$31 implements OnClickListener {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$31(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$31(threadFragment);
    }

    public void onClick(View view) {
        this.arg$1.unflagPost();
    }
}
