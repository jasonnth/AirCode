package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadFragment$$Lambda$30 implements OnClickListener {
    private final ThreadFragment arg$1;

    private ThreadFragment$$Lambda$30(ThreadFragment threadFragment) {
        this.arg$1 = threadFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadFragment threadFragment) {
        return new ThreadFragment$$Lambda$30(threadFragment);
    }

    public void onClick(View view) {
        this.arg$1.toggleArchived();
    }
}
