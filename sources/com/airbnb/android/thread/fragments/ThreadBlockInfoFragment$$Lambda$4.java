package com.airbnb.android.thread.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ThreadBlockInfoFragment$$Lambda$4 implements OnClickListener {
    private final ThreadBlockInfoFragment arg$1;

    private ThreadBlockInfoFragment$$Lambda$4(ThreadBlockInfoFragment threadBlockInfoFragment) {
        this.arg$1 = threadBlockInfoFragment;
    }

    public static OnClickListener lambdaFactory$(ThreadBlockInfoFragment threadBlockInfoFragment) {
        return new ThreadBlockInfoFragment$$Lambda$4(threadBlockInfoFragment);
    }

    public void onClick(View view) {
        this.arg$1.handlePrimaryButtonClick();
    }
}