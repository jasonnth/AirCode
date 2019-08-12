package com.airbnb.android.thread.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadBlockReasonFragment$$Lambda$10 implements OnClickListener {
    private final ThreadBlockReasonFragment arg$1;

    private ThreadBlockReasonFragment$$Lambda$10(ThreadBlockReasonFragment threadBlockReasonFragment) {
        this.arg$1 = threadBlockReasonFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadBlockReasonFragment threadBlockReasonFragment) {
        return new ThreadBlockReasonFragment$$Lambda$10(threadBlockReasonFragment);
    }

    public void onClick(View view) {
        this.arg$1.threadBlockController.onBackPressed();
    }
}
