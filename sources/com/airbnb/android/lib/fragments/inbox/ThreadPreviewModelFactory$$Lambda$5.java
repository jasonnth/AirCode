package com.airbnb.android.lib.fragments.inbox;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.Thread;

final /* synthetic */ class ThreadPreviewModelFactory$$Lambda$5 implements OnLongClickListener {
    private final ThreadClickListener arg$1;
    private final Thread arg$2;

    private ThreadPreviewModelFactory$$Lambda$5(ThreadClickListener threadClickListener, Thread thread) {
        this.arg$1 = threadClickListener;
        this.arg$2 = thread;
    }

    public static OnLongClickListener lambdaFactory$(ThreadClickListener threadClickListener, Thread thread) {
        return new ThreadPreviewModelFactory$$Lambda$5(threadClickListener, thread);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.onLongClick(this.arg$2);
    }
}
