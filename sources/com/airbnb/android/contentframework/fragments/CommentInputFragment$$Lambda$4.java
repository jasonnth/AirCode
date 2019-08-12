package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class CommentInputFragment$$Lambda$4 implements StringTextWatcher {
    private final CommentInputFragment arg$1;

    private CommentInputFragment$$Lambda$4(CommentInputFragment commentInputFragment) {
        this.arg$1 = commentInputFragment;
    }

    public static StringTextWatcher lambdaFactory$(CommentInputFragment commentInputFragment) {
        return new CommentInputFragment$$Lambda$4(commentInputFragment);
    }

    public void textUpdated(String str) {
        this.arg$1.getActivity().invalidateOptionsMenu();
    }
}
