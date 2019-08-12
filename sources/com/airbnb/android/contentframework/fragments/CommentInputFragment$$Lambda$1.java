package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.core.responses.PostCommentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CommentInputFragment$$Lambda$1 implements Action1 {
    private final CommentInputFragment arg$1;

    private CommentInputFragment$$Lambda$1(CommentInputFragment commentInputFragment) {
        this.arg$1 = commentInputFragment;
    }

    public static Action1 lambdaFactory$(CommentInputFragment commentInputFragment) {
        return new CommentInputFragment$$Lambda$1(commentInputFragment);
    }

    public void call(Object obj) {
        CommentInputFragment.lambda$new$0(this.arg$1, (PostCommentResponse) obj);
    }
}
