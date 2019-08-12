package com.airbnb.android.thread.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ThreadNotSentFragment$$Lambda$1 implements OnClickListener {
    private final ThreadNotSentFragment arg$1;

    private ThreadNotSentFragment$$Lambda$1(ThreadNotSentFragment threadNotSentFragment) {
        this.arg$1 = threadNotSentFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadNotSentFragment threadNotSentFragment) {
        return new ThreadNotSentFragment$$Lambda$1(threadNotSentFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ThreadNotSentFragment.lambda$onCreateDialog$0(this.arg$1, dialogInterface, i);
    }
}
