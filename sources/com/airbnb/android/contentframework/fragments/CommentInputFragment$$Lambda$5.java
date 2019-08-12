package com.airbnb.android.contentframework.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

final /* synthetic */ class CommentInputFragment$$Lambda$5 implements OnMenuItemClickListener {
    private final CommentInputFragment arg$1;

    private CommentInputFragment$$Lambda$5(CommentInputFragment commentInputFragment) {
        this.arg$1 = commentInputFragment;
    }

    public static OnMenuItemClickListener lambdaFactory$(CommentInputFragment commentInputFragment) {
        return new CommentInputFragment$$Lambda$5(commentInputFragment);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return CommentInputFragment.lambda$onPrepareOptionsMenu$4(this.arg$1, menuItem);
    }
}
