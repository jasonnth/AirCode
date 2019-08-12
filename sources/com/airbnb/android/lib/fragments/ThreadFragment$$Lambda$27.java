package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadFragment$$Lambda$27 implements OnClickListener {
    private final ThreadFragment arg$1;
    private final Intent arg$2;

    private ThreadFragment$$Lambda$27(ThreadFragment threadFragment, Intent intent) {
        this.arg$1 = threadFragment;
        this.arg$2 = intent;
    }

    public static OnClickListener lambdaFactory$(ThreadFragment threadFragment, Intent intent) {
        return new ThreadFragment$$Lambda$27(threadFragment, intent);
    }

    public void onClick(View view) {
        ThreadFragment.lambda$configureGuestBottomActionButton$28(this.arg$1, this.arg$2, view);
    }
}
