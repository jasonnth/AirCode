package com.airbnb.android.lib.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.airbnb.android.core.models.Post;

final /* synthetic */ class ThreadFragment$$Lambda$24 implements OnMenuItemClickListener {
    private final ThreadFragment arg$1;
    private final Post arg$2;

    private ThreadFragment$$Lambda$24(ThreadFragment threadFragment, Post post) {
        this.arg$1 = threadFragment;
        this.arg$2 = post;
    }

    public static OnMenuItemClickListener lambdaFactory$(ThreadFragment threadFragment, Post post) {
        return new ThreadFragment$$Lambda$24(threadFragment, post);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ThreadFragment.lambda$onCreateContextMenu$25(this.arg$1, this.arg$2, menuItem);
    }
}
