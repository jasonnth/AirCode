package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Post;

final /* synthetic */ class ThreadAdapter$$Lambda$10 implements OnClickListener {
    private final ThreadAdapter arg$1;
    private final Post arg$2;

    private ThreadAdapter$$Lambda$10(ThreadAdapter threadAdapter, Post post) {
        this.arg$1 = threadAdapter;
        this.arg$2 = post;
    }

    public static OnClickListener lambdaFactory$(ThreadAdapter threadAdapter, Post post) {
        return new ThreadAdapter$$Lambda$10(threadAdapter, post);
    }

    public void onClick(View view) {
        this.arg$1.onMessageItemClickListener.onMessageItemClicked(this.arg$2);
    }
}
