package com.airbnb.android.thread.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class ThreadUnblockDialogFragment$$Lambda$1 implements OnClickListener {
    private final ThreadUnblockDialogFragment arg$1;

    private ThreadUnblockDialogFragment$$Lambda$1(ThreadUnblockDialogFragment threadUnblockDialogFragment) {
        this.arg$1 = threadUnblockDialogFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadUnblockDialogFragment threadUnblockDialogFragment) {
        return new ThreadUnblockDialogFragment$$Lambda$1(threadUnblockDialogFragment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ThreadUnblockDialogFragment.lambda$onCreateDialog$0(this.arg$1, dialogInterface, i);
    }
}
