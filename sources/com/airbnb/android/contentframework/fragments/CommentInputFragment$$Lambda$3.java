package com.airbnb.android.contentframework.fragments;

final /* synthetic */ class CommentInputFragment$$Lambda$3 implements Runnable {
    private final CommentInputFragment arg$1;

    private CommentInputFragment$$Lambda$3(CommentInputFragment commentInputFragment) {
        this.arg$1 = commentInputFragment;
    }

    public static Runnable lambdaFactory$(CommentInputFragment commentInputFragment) {
        return new CommentInputFragment$$Lambda$3(commentInputFragment);
    }

    public void run() {
        CommentInputFragment.lambda$onCreateView$2(this.arg$1);
    }
}
