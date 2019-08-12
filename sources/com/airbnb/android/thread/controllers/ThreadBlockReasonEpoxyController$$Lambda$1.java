package com.airbnb.android.thread.controllers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.UserFlagDetail;

final /* synthetic */ class ThreadBlockReasonEpoxyController$$Lambda$1 implements OnClickListener {
    private final ThreadBlockReasonEpoxyController arg$1;
    private final UserFlagDetail arg$2;

    private ThreadBlockReasonEpoxyController$$Lambda$1(ThreadBlockReasonEpoxyController threadBlockReasonEpoxyController, UserFlagDetail userFlagDetail) {
        this.arg$1 = threadBlockReasonEpoxyController;
        this.arg$2 = userFlagDetail;
    }

    public static OnClickListener lambdaFactory$(ThreadBlockReasonEpoxyController threadBlockReasonEpoxyController, UserFlagDetail userFlagDetail) {
        return new ThreadBlockReasonEpoxyController$$Lambda$1(threadBlockReasonEpoxyController, userFlagDetail);
    }

    public void onClick(View view) {
        ThreadBlockReasonEpoxyController.lambda$addReasonToggle$0(this.arg$1, this.arg$2, view);
    }
}
