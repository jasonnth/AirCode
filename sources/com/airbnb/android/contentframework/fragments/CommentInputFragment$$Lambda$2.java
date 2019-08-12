package com.airbnb.android.contentframework.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CommentInputFragment$$Lambda$2 implements Action1 {
    private final CommentInputFragment arg$1;

    private CommentInputFragment$$Lambda$2(CommentInputFragment commentInputFragment) {
        this.arg$1 = commentInputFragment;
    }

    public static Action1 lambdaFactory$(CommentInputFragment commentInputFragment) {
        return new CommentInputFragment$$Lambda$2(commentInputFragment);
    }

    public void call(Object obj) {
        CommentInputFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
